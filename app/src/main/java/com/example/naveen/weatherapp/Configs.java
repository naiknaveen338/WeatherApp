package com.example.naveen.weatherapp;

/**
 * Created by Naveen on 5/5/2017.
 */
public class Configs {

    public String getQuery(String arg) {
        final String URL = "http://api.openweathermap.org/data/2.5/forecast/daily?q="+arg+"&units=metric&cnt=5&appid=3436d3c739ef80190563b8edf9827c25";
        return URL;
    }

}
