package com.example.weatherapiapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weatherapiapp.Adapter.WeatherListAdapter;
import com.example.weatherapiapp.Service.WeatherDataService;
import com.example.weatherapiapp.model.ForcastReportModel;

import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {
    ImageView imageCond;
    TextView cityNameTxt,humidityTxt,tempCTxt,windTxt,dateTxt,regionTxt,WeatherTxt;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_weather);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



            recyclerView = findViewById(R.id.lv);
            cityNameTxt = findViewById(R.id.City_name);
            humidityTxt = findViewById(R.id.humidity);
            tempCTxt = findViewById(R.id.temp);
            windTxt = findViewById(R.id.wind);
            dateTxt = findViewById(R.id.date);
            regionTxt = findViewById(R.id.region);
            imageCond = findViewById(R.id.imageCond);
            WeatherTxt = findViewById(R.id.WeatherTxt);


            Intent intent = getIntent();
            String inputData = intent.getStringExtra("input_data");

            final WeatherDataService weatherDataService = new WeatherDataService(WeatherActivity.this);





            weatherDataService.getCityInfo(inputData, new WeatherDataService.VolleyResponseListener() {// we cant return string directily we have to wait for it using callback method cuz its take time and if we just return string it will be empty cuz its run before the backround process finshed bcuz of non-blocking code
            @Override
            public void onError(String message) {
                Toast.makeText(WeatherActivity.this, message, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(ForcastReportModel City) {
                cityNameTxt.setText(City.getCityName());
                humidityTxt.setText(City.getHumidity());
                tempCTxt.setText(City.getTemp());
                windTxt.setText(City.getWind());
                dateTxt.setText(City.getDate());
                regionTxt.setText(City.getRegion());
                WeatherTxt.setText(City.getWeatherTxt());

                //set image url from api server

                String imageUrl = "https:" + City.getImageUrl();
                Glide.with(getApplicationContext()).load(imageUrl).into(imageCond);


            }
        });



            weatherDataService.getHoursInfo(inputData, new WeatherDataService.ForcastListResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(WeatherActivity.this, message, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(ArrayList<ForcastReportModel> forcastReportModels,int index) {



                WeatherListAdapter adapter = new WeatherListAdapter(WeatherActivity.this, forcastReportModels);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WeatherActivity.this, LinearLayoutManager.HORIZONTAL, false);

                // in below two lines we are setting layoutmanager and adapter to our recycler view.
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);

                //to scroll to specific position in the recyclerview
                linearLayoutManager.scrollToPosition(index);



            }
        });




    }
}