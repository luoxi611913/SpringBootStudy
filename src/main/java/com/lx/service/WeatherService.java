package com.lx.service;

import com.lx.model.WeatherData;

public interface WeatherService {

    public WeatherData getWeather(String city);
}
