package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class WeatherService {

    private static final String apiKey="e214ce574dd38cfd8e4a9d4e14c93b9f";
    private static final String API="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;
public String getWeather(String city)
{
    String finalApi=API.replace("CITY",city).replace("API_KEY",apiKey);
    restTemplate.exchange(finalApi, HttpMethod.GET,)
}

}
