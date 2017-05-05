package com.example.naveen.weatherapp;

/**
 * Created by Naveen on 5/5/2017.
 */
public class WeatherHelper {
    private String day;
    private String min;
    private String max;
    private String night;
    private String eve;
    private String morn;
    private String description;
    private String dt;
    private String name;

    public String getDay() {
        return day;
    }

    public String getMin() {
        return min;
    }

    public String getMax() {
        return max;
    }

    public String getNight() {
        return night;
    }

    public String getEve() {
        return eve;
    }

    public String getMorn() {
        return morn;
    }

    public String getDescription() {
        return description;
    }

    public String getDt() {
        return dt;
    }

    public String getName() {
        return name;
    }

    public WeatherHelper(String day, String min, String max, String night, String eve,
                         String morn, String description, String dt, String name) {
        this.day = day;
        this.min = min;
        this.max = max;
        this.night = night;
        this.eve = eve;
        this.morn = morn;
        this.description = description;
        this.dt = dt;
        this.name = name;
    }
}
