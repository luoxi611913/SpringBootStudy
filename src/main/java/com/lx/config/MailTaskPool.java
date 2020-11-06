package com.lx.config;

import com.lx.service.impl.MailServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableScheduling
@EnableAsync
public class MailTaskPool {

    private final Log log = LogFactory.getLog(MailTaskPool.class);

    @Autowired
    MailServiceImpl mailService;

    @Async
    @Scheduled(cron = "0 15 7 ? * *")
    public void first() throws InterruptedException {
        System.out.println("邮箱定时任务运行中 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
        mailService.sendSimpleMail("729697363@qq.com","新的一天，要好好加油哦O(∩_∩)O哈哈~","每天进步亿点点，每天都是好心情。");
        System.out.println();
    }

    @Async
    @Scheduled(cron = "0/5 * * * * *")
    public void second() throws InterruptedException {
        System.out.println("线程监控中 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
        log.info("Luoxi's task is running !");
    }



}
