package com.lx.service.impl;

import com.lx.model.Forecast;
import com.lx.model.WeatherData;
import com.lx.model.Yesterday;
import com.lx.service.WeatherService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("weatherService")
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public WeatherData getWeather(String city) {
        String apiURL = "http://wthrcdn.etouch.cn/weather_mini?city=" + city;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiURL, String.class);
        //获得所有回传的消息
        String jsonsting=responseEntity.getBody();
        WeatherData wed=new WeatherData();
        JSONObject str =JSONObject.fromObject(jsonsting);
        //	第一步解析
        String status="";
        if(str.has("status")){
            status=	str.get("status").toString();
            //如果输入的城市不对回传的状态码不同
        }
        if(status.equals("1000")){

            if(str.has("data")){
                //获取正确的数据
                JSONObject str1 =JSONObject.fromObject(str.get("data"));
                if(str1.has("yesterday")){
                    //获取昨天数据
                    JSONObject yy =JSONObject.fromObject(str1.get("yesterday"));
                    //set到昨天实体类

                    Yesterday y=new Yesterday();
                    y.setDate(yy.getString("date"));
                    y.setFl(yy.getString("fl"));
                    y.setFx(yy.getString("fx"));
                    y.setHigh(yy.getString("high"));
                    y.setLow(yy.getString("low"));
                    y.setType(yy.getString("type"));

                    wed.setYesterday(y);
                }
                //预报
                List<Forecast> fc=new ArrayList<Forecast>();
                if(str1.has("forecast")){
                    //System.err.println(str1.get("forecast"));
                    JSONArray tr=str1.getJSONArray("forecast");
                    for (int i = 0; i < tr.size(); i++) {
                        JSONObject yy1=JSONObject.fromObject(tr.get(i));
                        Forecast f=new Forecast();
                        f.setDate(yy1.getString("date"));
                        f.setFengli(yy1.getString("fengli"));
                        f.setFengxiang(yy1.getString("fengxiang"));
                        f.setHigh(yy1.getString("high"));
                        f.setLow(yy1.getString("low"));
                        f.setType(yy1.getString("type"));
                        //	System.err.println(f+"f");
                        fc.add(f);
                    }
                    wed.setForecast(fc);
                }
                //感冒指数
                if(str1.has("ganmao")){
                    //System.err.println(str1.get("ganmao"));
                    wed.setGanmao(str1.get("ganmao").toString());
                }
                //城市
                if(str1.has("city")){
                    //System.err.println(str1.get("city"));
                    wed.setCity(str1.get("city").toString());
                }
                //温度
                if(str1.has("wendu")){
                    //	System.err.println(str1.get("wendu"));
                    wed.setWendu(str1.get("wendu").toString());
                }

                System.out.println("222222"+wed);
            }


        }else{
            throw new RuntimeException("请输入正确的城市名称！");
        }

        return wed;
    }
}
