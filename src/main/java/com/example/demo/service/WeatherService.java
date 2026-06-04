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

    @Autowired
    private RedisService redisService;

public WheatherResponse getWeather(String city)
{
    WheatherResponse wheatherResponse = redisService.get("weather_of_" + city, WheatherResponse.class);
    if (wheatherResponse!=null)
    {
        return wheatherResponse;
    }
    else {
        String finalApi=appCache.APP_CACHE.get("weather_api").replace("<city>",city).replace("<apiKey>",apiKey);
        ResponseEntity<WheatherResponse>response=restTemplate.exchange(finalApi, HttpMethod.POST,null, WheatherResponse.class);
        WheatherResponse body=response.getBody();

        if(body!=null)
        {
            redisService.set("weather_of_" + city,body,300l);
            return body;
        }
    }


}

}
