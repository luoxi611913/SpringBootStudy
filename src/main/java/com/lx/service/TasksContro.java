package com.lx.service;

import com.lx.model.MailModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Component
@EnableScheduling
@EnableAsync
public class TasksContro {

    private final Log log = LogFactory.getLog(TasksContro.class);

    @Autowired
    TaskService taskService;

    @Autowired
    MailService mailService;

    private List<MailModel> tasks = new ArrayList<MailModel>();

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {

        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(5);
        threadPoolTaskScheduler.setThreadNamePrefix("task-Thread-");
        return threadPoolTaskScheduler;
    }

    /**
     * 在ScheduledFuture中有一个cancel可以停止定时任务。
     */
    private Map<String,ScheduledFuture<?>> futures  = new ConcurrentHashMap<>();

    @Async
    @Scheduled(cron = "0/5 * * * * *")
    public void first() throws InterruptedException {
        //调用数据库进行查询
        this.tasks = taskService.getMailTasks();

        for(MailModel mail:tasks){
            futures.put(mail.getTid().toString(),threadPoolTaskScheduler.schedule(new Runnable() {

                @Override
                public void run() {
                     mailService.sendSimpleMail(mail.getToUser(),mail.getMailTitle(),mail.getMailContents());
                    for(Map.Entry<String,ScheduledFuture<?>> scheduledFutureEntry:futures.entrySet()){
                        if(scheduledFutureEntry.getKey().equals(mail.getTid().toString())){
                            System.out.println("找到了匹配的这个任务，执行关闭，并在map中清除");
                            scheduledFutureEntry.getValue().cancel(true);
                            futures.remove(scheduledFutureEntry.getKey());
                        }
                    }
                }
            }, new CronTrigger(mail.getTchron())));


        }

        log.info("本次调度结束。。。目前已有             --->"+futures.size()+"个计划");
        log.info("heartBeat Thread end。。。there are  --->"+futures.size()+" schedules");
    }
}
