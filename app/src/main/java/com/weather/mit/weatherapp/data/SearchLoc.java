package com.weather.mit.weatherapp.data;

/**
 * Created by Mit on 11/23/2015.
 */
public class SearchLoc {
    //private String woeid;
    private String country;
    private String name;
    public SearchLoc() {}

    public SearchLoc( String name,String country) {
        this.country = country;
        this.name = name;
    }



    public void setCountry(String country) {
        this.country = country;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "," + country;
    }
}
