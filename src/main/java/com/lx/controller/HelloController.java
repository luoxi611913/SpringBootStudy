package com.lx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/helloTest")
    @ResponseBody
    public String helloTest(){



        return "Hellow World!";
    }
}
