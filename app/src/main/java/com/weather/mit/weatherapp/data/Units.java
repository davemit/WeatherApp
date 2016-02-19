package com.weather.mit.weatherapp.data;

import org.json.JSONObject;


/**
 * Created by Mit on 11/12/2015.
 */
public class Units implements JSONPopulator {
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data){
        temperature = data.optString("temperature");
    }
}
