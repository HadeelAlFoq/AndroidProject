package com.example.hadeel.samples;

/**
 * Created by hadeel on 9/6/2017.
 */

public class CountryModel {
    String country;
    public CountryModel(){}
    public CountryModel(String country){
        this.country=country;
    }
    public String getCountry(){
        return country;
    }
    public void setCountry(String country1){
        this.country=country1;
    }
}
