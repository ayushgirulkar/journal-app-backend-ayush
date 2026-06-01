package com.example.demo.service;

import com.example.demo.api.response.WheatherResponse;
import com.example.demo.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    @Value("${weather.api.key}")
    private  String apiKey;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

public WheatherResponse getWeather(String city)
{
    String finalApi=appCache.APP_CACHE.get("weather_api").replace("CITY",city).replace("API_KEY",apiKey);
    ResponseEntity<WheatherResponse>response=restTemplate.exchange(finalApi, HttpMethod.POST,null, WheatherResponse.class);
    WheatherResponse body=response.getBody();
    return  body;
}

}
