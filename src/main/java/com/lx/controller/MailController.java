package com.lx.controller;

import com.lx.model.MailModel;
import com.lx.service.MailService;
import com.lx.service.TaskService;
import com.lx.service.impl.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class MailController {

    @Autowired
    MailServiceImpl mailService;

    @Autowired
    TaskService taskService;

    /**
     * 邮件测试
     */
    @GetMapping("/mailtest")
    @ResponseBody
    public String mailTest(){
        mailService.sendSimpleMail("729697363@qq.com","这是主题","这是内容");

        return "已发送";
    }

    /**
     * 查询所有mail任务
     */
    @GetMapping("/getTasks")
    @ResponseBody
    public String getTasks(){

        System.out.println(taskService.getMailTasks().toString());

        return "sss";
    }

    /**
     * 添加任务
     */
    @PostMapping("/addTask")
    public String addTask(MailModel mailModel, Map<String,String> map) {

        System.out.println(mailModel);

        //获取当前时间
        try {
            String[] scheduStrs = mailModel.getDoDate().split("-");

            //chron表达式生成: * * * * * *
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("* * ")/*拼接秒分*/
                    .append(scheduStrs[2]).append(" ")
                    .append(scheduStrs[1]).append(" ")
                    .append(scheduStrs[0]).append(" ?");
                    mailModel.setTchron(stringBuffer.toString());
        }catch (Exception e){
            map.put("error","日期转换异常，目前程序脆弱尽量正规输入谢谢");
            return "error";
        }
        mailService.insertNewSchedu(mailModel);


        return "mailsuccess";
    }
}
