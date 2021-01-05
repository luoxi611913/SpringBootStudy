package com.lx.config;

import com.lx.model.Forecast;
import com.lx.model.WeatherData;
import com.lx.service.WeatherService;
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

//@Component
//@EnableScheduling
//@EnableAsync
public class MailTaskPool {

//    private final Log log = LogFactory.getLog(MailTaskPool.class);
//
//    @Autowired
//    MailServiceImpl mailService;
//
//    @Autowired
//    WeatherService weatherService;
//
//    @Async
//    @Scheduled(cron = "0 15 7 ? * *")
//    public void first() throws InterruptedException {
//        System.out.println("邮箱定时任务运行中 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
//        mailService.sendSimpleMail("729697363@qq.com","新的一天，要好好加油哦O(∩_∩)O哈哈~","每天进步亿点点，每天都是好心情。");
//        System.out.println();
//    }
//
//    @Async
//    @Scheduled(cron = "0 1 0 ? * *")
//    public void third() throws InterruptedException {
//        System.out.println("邮箱定时任务运行中 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
//        mailService.sendSimpleMail("806984139@qq.com","钉钉早晚卡，学习强国，安培云","记得打卡，答题，发四张图，顺便看看钉钉有没有消息，爱你宝（づ￣3￣）づ╭❤～。");
//        System.out.println();
//    }
//
//    @Async
//    @Scheduled(cron = "0 0 20 ? * *")
//    public void second() throws InterruptedException {
//        WeatherData weatherData = weatherService.getWeather("西安");
//
//        StringBuffer stringBuffer = new StringBuffer();
//        for(Forecast forecast:weatherData.getForecast()){
//            stringBuffer.append("日期  "+forecast.getDate())
//                    .append("  最高温度:  "+forecast.getHigh() + "最低温度  "+forecast.getLow())
//                    .append("  风力  " + forecast.getFengli().substring(forecast.getFengli().indexOf("级")-1,forecast.getFengli().indexOf("级")+1)).append("\n\r");
//        }
//
//        mailService.sendSimpleMail("729697363@qq.com","每日天气播报--By你的玺","明天天气:" +
//                "城市:  " +weatherData.getCity()+
//                "   气温:  "+ weatherData.getWendu() +
//                "   感冒指数:" +weatherData.getGanmao() +"\n\r"+
//                "未来七天预测: \n\r\n\r" + stringBuffer.toString());
//
//        mailService.sendSimpleMail("806984139@qq.com","每日天气播报--By你的玺","明天天气:" +
//                "城市:  " +weatherData.getCity()+
//                "   气温:  "+ weatherData.getWendu() +
//                "   感冒指数:" +weatherData.getGanmao() +"\n\r"+
//                "未来七天预测: \n\r\n\r" + stringBuffer.toString());
//
//        mailService.sendSimpleMail("2826748474@qq.com","每日天气播报--By你的玺","明天天气:" +
//                "城市:  " +weatherData.getCity()+
//                "   气温:  "+ weatherData.getWendu() +
//                "   感冒指数:" +weatherData.getGanmao() +"\n\r"+
//                "未来七天预测: \n\r\n\r" + stringBuffer.toString());
//
//        mailService.sendSimpleMail("1378496758@qq.com","每日天气播报--By你的玺","明天天气:" +
//                "城市:  " +weatherData.getCity()+
//                "   气温:  "+ weatherData.getWendu() +
//                "   感冒指数:" +weatherData.getGanmao() +"\n\r"+
//                "未来七天预测: \n\r\n\r" + stringBuffer.toString());
//
//
//        weatherData = weatherService.getWeather("宝鸡");
//
//        stringBuffer = new StringBuffer();
//        for(Forecast forecast:weatherData.getForecast()){
//            stringBuffer.append("日期  "+forecast.getDate())
//                    .append("  最高温度:  "+forecast.getHigh() + "最低温度  "+forecast.getLow())
//                    .append("  风力  " + forecast.getFengli().substring(forecast.getFengli().indexOf("级")-1,forecast.getFengli().indexOf("级")+1)).append("\n\r");
//        }
//
//        mailService.sendSimpleMail("1872042729@qq.com","每日天气播报--By你的玺","明天天气:" +
//                "城市:  " +weatherData.getCity()+
//                "   气温:  "+ weatherData.getWendu() +
//                "   感冒指数:" +weatherData.getGanmao() +"\n\r"+
//                "未来七天预测: \n\r\n\r" + stringBuffer.toString());
//    }
//


}
