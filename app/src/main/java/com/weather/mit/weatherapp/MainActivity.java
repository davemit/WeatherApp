package com.weather.mit.weatherapp;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.weather.mit.weatherapp.data.Channel;
import com.weather.mit.weatherapp.data.Item;
import com.weather.mit.weatherapp.service.SuggestionAdapter;
import com.weather.mit.weatherapp.service.WeatherServiceCallback;
import com.weather.mit.weatherapp.service.YahooWeatherService;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallback {

    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;

    private YahooWeatherService service;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherIconImageView = (ImageView)findViewById(R.id.weatherIconImageView);
        temperatureTextView = (TextView)findViewById(R.id.temperatureTextView);
        conditionTextView = (TextView)findViewById(R.id.conditionTextView);
        locationTextView = (TextView)findViewById(R.id.locationTextView);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);

        dialog.setMessage("Loading...");
        service.refreshWeather("Austin, TX");



            AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.edtCity);
            acTextView.setAdapter(new SuggestionAdapter(this,acTextView.getText().toString()));




    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();
        Item item = channel.getItem();
        int resourceId = getResources().getIdentifier("drawable/w_"+ item.getCondition().getCode(), null, getPackageName());
        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawable = getResources().getDrawable(resourceId);

        weatherIconImageView.setImageDrawable(weatherIconDrawable);
        temperatureTextView.setText(item.getCondition().getTemperature()+"\u00B0 "+channel.getUnits().getTemperature());
        //Log.v("weatherapp", "Here");
        conditionTextView.setText(item.getCondition().getDescription());
        locationTextView.setText(service.getLocation());


    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(),Toast.LENGTH_LONG).show();
    }
}
