package com.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    private Map<String, Object> main;
    private Map<String, Object>[] weather;
    private Map<String, Object> wind;
    private String name;

}
