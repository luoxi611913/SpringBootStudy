package com.lx.controller;

import com.lx.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping("/getCounts")
    @ResponseBody
    public String getCounts(){

        return String.valueOf(indexService.selectWatchedCount());
    }

    @RequestMapping("/addCounts")
    @ResponseBody
    public String addCounts(){
        String flg = "0";

        try {
            indexService.addWatchedCount();
            flg = "1";
        }catch (Exception e){
            flg = "-1";
        }

        return flg;
    }
}
