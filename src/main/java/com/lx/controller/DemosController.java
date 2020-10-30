package com.lx.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
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

@Controller
@RequestMapping("/demo")
public class DemosController {

    @RequestMapping("/pdfconcat")
    public String turnToPdfConcat(){

        System.out.println("哈哈哈");
        return "pdfdemo";
    }

    @RequestMapping("/pdfdo")
    @ResponseBody
    public String pdfdo(HttpServletRequest request, HttpServletResponse response){

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
                    return "第" + (i+1) + " 个文件解析失败了";

                }
            } else {
                return "保存失败第  " + (i+1)+ " 个文件，因为这个文件不合法";
            }

        }

        mergePdfFiles(filePaths,"mergedNew.pdf");

        OutputStream outputStream = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        byte[] buffer = new byte[1024];

        //获得浏览器代理信息
        final String userAgent = request.getHeader("USER-AGENT");
        //判断浏览器代理并分别设置响应给浏览器的编码格式
        String finalFileName = null;
        if(StringUtils.contains(userAgent, "MSIE")||StringUtils.contains(userAgent,"Trident")){//IE浏览器

            try{
                finalFileName = URLEncoder.encode("mergedNew.pdf","UTF8");
            }catch (Exception e){
                return "读文件失败了..";
            }

            System.out.println("IE浏览器");
        }else if(StringUtils.contains(userAgent, "Mozilla")){//google,火狐浏览器
            try{
                finalFileName = new String("mergedNew.pdf".getBytes(), "ISO8859-1");
            }catch (Exception e){
                return "火狐失败";
            }

        }else{

            try{
                finalFileName = URLEncoder.encode("mergedNew.pdf","UTF8");
            }catch (Exception e){
                return "读文件失败了..";
            }
        }
        //设置HTTP响应头
        response.reset();//重置 响应头
        response.setContentType("application/x-download");//告知浏览器下载文件，而不是直接打开，浏览器默认为打开
        response.addHeader("Content-Disposition" ,"attachment;filename=\"" +finalFileName+ "\"");//下载文件的名称

        try{
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

        }catch (Exception e){
            return "响应过程中失败了哟";
        }finally {

            if(outputStream!=null){
                try {
                    outputStream.close();
                }catch (IOException e){
                    return "关闭响应失败!";
                }
            }



            if(bis!=null){
                try {
                    bis.close();
                }catch (IOException e){
                    return "关闭缓冲失败!";
                }
            }

            if(fis!=null){
                try {
                    fis.close();
                }catch (IOException e){
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
}
