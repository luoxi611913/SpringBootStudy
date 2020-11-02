package com.lx.config;

import com.lx.intercepters.LoginInterCepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/ddd").setViewName("index");
        registry.addViewController("/demo/excelupload").setViewName("exceldemo");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //registry.addInterceptor(new LoginInterCepter()).addPathPatterns("/**").excludePathPatterns("/");
    }
}
