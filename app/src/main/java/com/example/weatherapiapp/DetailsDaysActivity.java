package com.example.weatherapiapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class DetailsDaysActivity extends AppCompatActivity {


    ImageView image;
    TextView cityNameTxt,humidityTxt,tempText,windTxt,dateTxt,regionTxt,WeatherTxt;
    TextView sunsetText,sunriseText,uvIndexText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details_days);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tempText = findViewById(R.id.temp);
        cityNameTxt = findViewById(R.id.City_name);
        humidityTxt = findViewById(R.id.humidity);
        windTxt = findViewById(R.id.wind);
        dateTxt = findViewById(R.id.date);
        regionTxt = findViewById(R.id.region);
        image = findViewById(R.id.imageCond);
        WeatherTxt = findViewById(R.id.WeatherTxt);
        sunriseText = findViewById(R.id.sunrise);
        sunsetText = findViewById(R.id.sunset);
        uvIndexText = findViewById(R.id.uv_index);


        Intent intent = getIntent();
        String imgStr = intent.getStringExtra("img");
        String tempStr = intent.getStringExtra("temp");
        String dateStr = intent.getStringExtra("date");
        String windStr = intent.getStringExtra("wind_k");
        String weatherStr = intent.getStringExtra("Weather_txt");
        String humStr = intent.getStringExtra("hum");
        String cityNameStr = intent.getStringExtra("cityName");
        String countryStr = intent.getStringExtra("country");
        String sunsetStr = intent.getStringExtra("sunset");
        String sunriseStr = intent.getStringExtra("sunrise");
        String uvStr = intent.getStringExtra("uv");





        tempText.setText(tempStr);
        dateTxt.setText(dateStr);
        windTxt.setText(windStr);
        humidityTxt.setText(humStr);
        WeatherTxt.setText(weatherStr);
        regionTxt.setText(countryStr);
        cityNameTxt.setText(cityNameStr);
        sunsetText.setText(sunsetStr);
        sunriseText.setText(sunriseStr);
        uvIndexText.setText(uvStr);

        String imageUrl = "https:" + imgStr;
        Glide.with(getApplicationContext()).load(imageUrl).into(image);
    }
}