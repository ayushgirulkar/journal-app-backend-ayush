package com.example.demo.cache;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AppCache
{
    private Map<String,String> appCache;

    public  void init()
    {
        appCache=null;
    }

}
