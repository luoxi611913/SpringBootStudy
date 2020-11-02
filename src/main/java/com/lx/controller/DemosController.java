package com.lx.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.lx.model.ExcelDemoModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/demo")
public class DemosController {

    @RequestMapping("/pdfconcat")
    public String turnToPdfConcat() {

        System.out.println("哈哈哈");
        return "pdfdemo";
    }

    @RequestMapping("/pdfdo")
    @ResponseBody
    public String pdfdo(HttpServletRequest request, HttpServletResponse response) {

        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        List<String> filePaths = new ArrayList<String>();
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + file.getOriginalFilename());
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(file.getOriginalFilename())));

                    filePaths.add(file.getOriginalFilename());
                    stream.write(bytes);
                    stream.close();

                } catch (Exception e) {
                    stream = null;
                    return "第" + (i + 1) + " 个文件解析失败了";

                }
            } else {
                return "保存失败第  " + (i + 1) + " 个文件，因为这个文件不合法";
            }

        }

        mergePdfFiles(filePaths, "mergedNew.pdf");

        OutputStream outputStream = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        byte[] buffer = new byte[1024];

        //获得浏览器代理信息
        final String userAgent = request.getHeader("USER-AGENT");
        //判断浏览器代理并分别设置响应给浏览器的编码格式
        String finalFileName = null;
        if (StringUtils.contains(userAgent, "MSIE") || StringUtils.contains(userAgent, "Trident")) {//IE浏览器

            try {
                finalFileName = URLEncoder.encode("mergedNew.pdf", "UTF8");
            } catch (Exception e) {
                return "读文件失败了..";
            }

            System.out.println("IE浏览器");
        } else if (StringUtils.contains(userAgent, "Mozilla")) {//google,火狐浏览器
            try {
                finalFileName = new String("mergedNew.pdf".getBytes(), "ISO8859-1");
            } catch (Exception e) {
                return "火狐失败";
            }

        } else {

            try {
                finalFileName = URLEncoder.encode("mergedNew.pdf", "UTF8");
            } catch (Exception e) {
                return "读文件失败了..";
            }
        }
        //设置HTTP响应头
        response.reset();//重置 响应头
        response.setContentType("application/x-download");//告知浏览器下载文件，而不是直接打开，浏览器默认为打开
        response.addHeader("Content-Disposition", "attachment;filename=\"" + finalFileName + "\"");//下载文件的名称

        try {
            outputStream = response.getOutputStream();

            fis = new FileInputStream(new File("mergedNew.pdf"));
            bis = new BufferedInputStream(fis);
            outputStream = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                outputStream.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            outputStream.flush();

        } catch (Exception e) {
            return "响应过程中失败了哟";
        } finally {

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    return "关闭响应失败!";
                }
            }


            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    return "关闭缓冲失败!";
                }
            }

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    return "关闭文件流失败!";
                }
            }

        }


        return "upload successful";
    }


    private boolean mergePdfFiles(List<String> files, String newfile) {
        boolean retValue = false;
        Document document = null;
        try {
            document = new Document(new PdfReader(files.get(0)).getPageSize(1));
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(newfile));
            document.open();
            for (int i = 0; i < files.size(); i++) {
                PdfReader reader = new PdfReader(files.get(i));
                int n = reader.getNumberOfPages();
                for (int j = 1; j <= n; j++) {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);
                    copy.addPage(page);
                }
            }
            retValue = true;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("执行结束");
            document.close();
        }
        return retValue;
    }


    /**
     * Excel读取
     */
    @RequestMapping("/exceldemo")
    public String ExcelRead(MultipartFile file, Map<String,Object> map) {

        System.out.println(file.getOriginalFilename());

        if (null == file || file.getSize() < 1) {
            System.out.println("文件无用");
        }

        InputStream fileInputStream = null;

        XSSFWorkbook xssfWorkbook = null;

        //创建list
        List<ExcelDemoModel> list = new ArrayList<ExcelDemoModel>();

        try {
            //获取文件流
            fileInputStream = file.getInputStream();

            //封入表格对象
            xssfWorkbook = new XSSFWorkbook(fileInputStream);



            //循环遍历sheet
            for (int i = 0; i < xssfWorkbook.getNumberOfSheets(); i++) {

                //获得sheet
                XSSFSheet sheet = xssfWorkbook.getSheetAt(i);

                //循环遍历行
                for (int j = 0; j < sheet.getLastRowNum(); j++) {
                    System.out.println("有几行？" + sheet.getLastRowNum());

                    int count = 0;

                    //获得row
                    XSSFRow row = sheet.getRow(j);

                    ExcelDemoModel excelDemoModel = new ExcelDemoModel();
                    excelDemoModel.setUname(row.getCell(count++).toString());
                    excelDemoModel.setUport(row.getCell(count++).toString());
                    excelDemoModel.setJumpposition(row.getCell(count++).toString());
                    excelDemoModel.setTarger1(row.getCell(count++).toString());
                    excelDemoModel.setTarger2(row.getCell(count++).toString());
                    excelDemoModel.setChengduan(row.getCell(count++).toString());
                    excelDemoModel.setGoForward(row.getCell(count++).toString());
                    excelDemoModel.setXxx(row.getCell(count++).toString());
                    excelDemoModel.setMochinePort(row.getCell(count++).toString());
                    excelDemoModel.setFinaluser(row.getCell(count++).toString());
                    excelDemoModel.setFollowRouter(row.getCell(count++).toString());
                    excelDemoModel.setPs(row.getCell(count++).toString());
                    list.add(excelDemoModel);
                    //遍历excel
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println("处理Excel遇到错误，请重试");
        } finally {

            try {
                if (xssfWorkbook != null) {
                    xssfWorkbook.close();
                }

                if (fileInputStream != null) {
                    fileInputStream.close();
                }

            } catch (Exception e) {
                System.out.println("关闭异常"+e.getMessage());
            }

        }

//        //获取第一个sheet
//        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
//
//        System.out.println(xssfSheet);
//
//        //获取第一行
//        XSSFRow row = xssfSheet.getRow(0);
//        System.out.println(row);
//
//        //获取第一个单元格
//        XSSFCell cell = row.getCell(0);
//        System.out.println(cell);
        map.put("uname","luoxi");
        map.put("ulist",list);
        return "excelconfirm";
    }
}
