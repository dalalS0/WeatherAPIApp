package com.example.weatherapiapp.Service;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.weatherapiapp.model.ForcastReportModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherDataService {
    Context context;
    private static String APIKey = "//provided your api key here";

    public static final String URL = "http://api.weatherapi.com/v1/forecast.json?" + APIKey;

    public WeatherDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);
        void onResponse(ForcastReportModel City);

    }

    public interface ForcastListResponseListener {
        void onError(String message);
        void onResponse(ArrayList<ForcastReportModel> forcastReportModels,int index);

    }


    public void getCityInfo(String cityName, VolleyResponseListener volleyResponseListener){

    String url = URL + cityName;
    ForcastReportModel forcastCityModel = new ForcastReportModel();

    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    String name = "";
                    String temp_c = "";
                    String wind = "";
                    String region = "";
                    String humidity = "";
                    String imageUrl = "";
                    String localtime = "",WethTxt = "";

                    try {
                        JSONObject location = response.getJSONObject("location");
                        name = location.getString("name");
                        localtime = location.getString("localtime");
                        region = location.getString("country");

                        JSONObject current = response.getJSONObject("current");
                        temp_c = current.getString("temp_c");
                        humidity = current.getString("humidity");
                        wind = current.getString("wind_kph");

                        JSONObject condition = current.getJSONObject("condition");
                        WethTxt = condition.getString("text");
                        imageUrl = condition.getString("icon");



                        forcastCityModel.setCityName(name);
                        forcastCityModel.setDate(localtime);
                        forcastCityModel.setTemp(temp_c);
                        forcastCityModel.setHumidity(humidity);
                        forcastCityModel.setWind(wind);
                        forcastCityModel.setRegion(region);
                        forcastCityModel.setWeatherTxt(WethTxt);
                        forcastCityModel.setImageUrl(imageUrl);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    volleyResponseListener.onResponse(forcastCityModel);

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                   //Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    volleyResponseListener.onError("Something wrong");


                }
            });

    //queue.add(jsonObjectRequest);
    VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    //if we were to have multiple things happening at the same time
    //the singleton will handle them in sequential order

}




    public void getHoursInfo(String cityName,ForcastListResponseListener forcastListResponseListener){

        String url = URL + cityName;
        ArrayList<ForcastReportModel> reportModels = new ArrayList<>();


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        String temp_c = "",wind_k = "",humidity = "",uv;
                        String imageUrl = "",text = "",name = "",country = "";
                        String localtime = "";
                        String Alltime = "";
                        String sunsetStr = "", sunriseStr = "";

                        try {

                            JSONObject forecast = response.getJSONObject("forecast");
                            JSONArray forecast_day = forecast.getJSONArray("forecastday");
                            JSONObject firstObj = forecast_day.getJSONObject(0);
                            JSONArray hour_arr = firstObj.getJSONArray("hour");
                            JSONObject location = response.getJSONObject("location");
                            JSONObject astro = firstObj.getJSONObject("astro");
                            sunriseStr = astro.getString("sunrise");
                            sunsetStr = astro.getString("sunset");

                            localtime = location.getString("localtime");
                            name = location.getString("name");
                            country = location.getString("country");


                            int index = 0;
                            for(int i =0; i< hour_arr.length(); i++) {

                                ForcastReportModel oneDay_weather = new ForcastReportModel();

                                JSONObject hours = hour_arr.getJSONObject(i);
                                JSONObject condition = hours.getJSONObject("condition");

                                temp_c = hours.getString("temp_c");
                                Alltime = hours.getString("time");
                                imageUrl = condition.getString("icon");
                                text = condition.getString("text");
                                wind_k = hours.getString("wind_kph");
                                uv = hours.getString("uv");
                                humidity = hours.getString("humidity");



                                oneDay_weather.setDate(Alltime);
                                oneDay_weather.setTemp(temp_c);
                                oneDay_weather.setImageUrl(imageUrl);
                                oneDay_weather.setLocaltime(localtime);
                                oneDay_weather.setCityName(name);
                                oneDay_weather.setRegion(country);
                                oneDay_weather.setWind(wind_k);
                                oneDay_weather.setWeatherTxt(text);
                                oneDay_weather.setHumidity(humidity);
                                oneDay_weather.setSunriseStr(sunriseStr);
                                oneDay_weather.setSunsetStr(sunsetStr);
                               oneDay_weather.setUv_index(uv);


                                boolean equals = Alltime.substring(11, 13).equals(localtime.substring(11, 13));

                                switch(i)
                                {
                                    case 0:
                                        if(equals){
                                            index = 0;
                                        }
                                        break;
                                    case 1:
                                        if(equals){
                                            index = 1;
                                        }
                                        break;
                                    case 2:
                                        if(equals){
                                            index = 2;
                                        }
                                        break;
                                    case 3:
                                        if(equals){
                                            index = 3;
                                        }
                                        break;
                                    case 4:
                                        if(equals){
                                            index = 4;
                                        }
                                        break;
                                    case 5:
                                        if(equals){
                                            index = 5;
                                        }
                                        break;
                                    case 6:
                                        if(equals){
                                            index = 6;
                                        }
                                        break;
                                    case 7:
                                        if(equals){
                                            index = 7;
                                        }
                                        break;
                                    case 8:
                                        if(equals){
                                            index = 8;
                                        }
                                        break;
                                    case 9:
                                        if(equals){
                                            index = 9;
                                        }
                                        break;
                                    case 10:
                                        if(equals){
                                            index = 10;
                                        }
                                        break;
                                    case 11:
                                        if(equals){
                                            index = 11;
                                        }
                                        break;
                                    case 12:
                                        if(equals){
                                            index = 12;
                                        }
                                        break;
                                    case 13:
                                        if(equals){
                                            index = 13;
                                        }
                                        break;
                                    case 14:
                                        if(equals){
                                            index = 14;
                                        }
                                        break;
                                    case 15:
                                        if(equals){
                                            index = 15;
                                        }
                                        break;
                                    case 16:
                                        if(equals){
                                            index = 16;
                                        }
                                        break;
                                    case 17:
                                        if(equals){
                                            index = 17;
                                        }
                                        break;
                                    case 18:
                                        if(equals){
                                            index = 18;
                                        }
                                        break;
                                    case 19:
                                        if(equals){
                                            index = 19;
                                        }
                                        break;
                                    case 20:
                                        if(equals){
                                            index = 20;
                                        }
                                        break;
                                    case 21:
                                        if(equals){
                                            index = 21;
                                        }
                                        break;
                                    case 22:
                                        if(equals){
                                            index = 22;
                                        }
                                        break;
                                    case 23:
                                        if(equals){
                                            index = 23;
                                        }
                                        break;
                                }

                                reportModels.add(oneDay_weather);

                            }


                            forcastListResponseListener.onResponse(reportModels,index);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                        forcastListResponseListener.onError("Something wrong");


                    }
                });

        //queue.add(jsonObjectRequest);
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
        //if we were to have multiple things happening at the same time
        //the singleton will handle them in sequential order

    }


}
