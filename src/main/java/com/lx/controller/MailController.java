package com.lx.controller;

import com.lx.service.MailService;
import com.lx.service.impl.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {

    @Autowired
    MailServiceImpl mailService;

    /**
     * 邮件测试
     */
    @GetMapping("/mailtest")
    @ResponseBody
    public String mailTest(){
        mailService.sendSimpleMail("729697363@qq.com","这是主题","这是内容");

        return "已发送";
    }
}
