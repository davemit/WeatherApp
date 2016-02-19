package com.weather.mit.weatherapp.service;

import com.weather.mit.weatherapp.data.Channel;

/**
 * Created by Mit on 11/12/2015.
 */
public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);

}
