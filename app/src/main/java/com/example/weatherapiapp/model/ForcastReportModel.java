package com.example.weatherapiapp.model;

public class ForcastReportModel {

    String cityName;
    String Date;
    String Wind;
    String humidity;
    String Temp;
    String localtime;
    String sunriseStr,sunsetStr,uv_index;
    String region,WeatherTxt,imageUrl;

    public String getSunriseStr() {
        return sunriseStr;
    }

    public void setSunriseStr(String sunriseStr) {
        this.sunriseStr = sunriseStr;
    }

    public String getSunsetStr() {
        return sunsetStr;
    }

    public void setSunsetStr(String sunsetStr) {
        this.sunsetStr = sunsetStr;
    }

    public String getUv_index() {
        return uv_index;
    }

    public void setUv_index(String uv_index) {
        this.uv_index = uv_index;
    }

    public String getLocaltime() {
        return localtime;
    }

    public void setLocaltime(String localtime) {
        this.localtime = localtime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getWeatherTxt() {
        return WeatherTxt;
    }

    public void setWeatherTxt(String weatherTxt) {
        WeatherTxt = weatherTxt;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getWind() {
        return Wind;
    }

    public void setWind(String wind) {
        Wind = wind;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTemp() {
        return Temp;
    }

    public void setTemp(String temp) {
        Temp = temp;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
