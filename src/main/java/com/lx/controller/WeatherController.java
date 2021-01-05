package com.lx.controller;

import com.lx.model.Forecast;
import com.lx.model.WeatherData;
import com.lx.model.Yesterday;
import com.lx.service.MailService;
import com.lx.service.WeatherService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WeatherController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WeatherService weatherService;

    @Autowired
    MailService mailService;

    @GetMapping("/weather")
    public String charts7(){
        WeatherData weatherData = weatherService.getWeather("西安");



            StringBuffer stringBuffer = new StringBuffer();
            for(Forecast forecast:weatherData.getForecast()){
                stringBuffer.append("日期  "+forecast.getDate())
                        .append("  最高温度:  "+forecast.getHigh() + "最低温度  "+forecast.getLow())
                        .append("  风力  " + forecast.getFengli().substring(forecast.getFengli().indexOf("级")-1,forecast.getFengli().indexOf("级")+1)).append("\n\r");
            }

        mailService.sendSimpleMail("729697363@qq.com","每日天气播报","明天天气:" +
                "城市:  " +weatherData.getCity()+
                "   气温:  "+ weatherData.getWendu() +
                "   感冒指数:" +weatherData.getGanmao() +"\n\r"+
                "未来七天预测: \n\r\n\r" + stringBuffer.toString());
        return "hello";
    }

}
