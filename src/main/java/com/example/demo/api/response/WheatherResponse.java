package com.example.demo.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WheatherResponse
{

    public Current current;

    public class Current{
        public int temperature;
        @JsonProperty("weather_description")
        public List<String> weatherDescriptions;

    }


}






