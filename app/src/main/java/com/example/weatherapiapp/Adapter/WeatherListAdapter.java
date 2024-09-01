package com.example.weatherapiapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weatherapiapp.DetailsDaysActivity;
import com.example.weatherapiapp.R;
import com.example.weatherapiapp.model.ForcastReportModel;

import java.util.ArrayList;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<ForcastReportModel> forcastReportModels;

    // Constructor
    public WeatherListAdapter(Context context, ArrayList<ForcastReportModel> forcastReportModels) {
        this.context = context;
        this.forcastReportModels = forcastReportModels;
    }

    @NonNull
    @Override
    public WeatherListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_adapter_lists, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull WeatherListAdapter.ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        ForcastReportModel model = forcastReportModels.get(position);
        holder.timeList.setText(model.getDate().substring(11));
        holder.tempList.setText(model.getTemp());

        String imageUrl = "https:" + model.getImageUrl();
        Glide.with(context).load(imageUrl).into(holder.imgList);

        String allTime = model.getDate();
        String localtime = model.getLocaltime();
        //to check if specific index the time of it the same as current time will be blue
        if (allTime.substring(11, 13).equals(localtime.substring(11,13))) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#1C86E8"));

        }else{//66052A87
            holder.cardView.setCardBackgroundColor(Color.parseColor("#DC0D1545"));
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DetailsDaysActivity.class);
                intent.putExtra("temp",model.getTemp());
                intent.putExtra("date",model.getDate());
                intent.putExtra("img",model.getImageUrl());
                intent.putExtra("wind_k",model.getWind());
                intent.putExtra("Weather_txt",model.getWeatherTxt());
                intent.putExtra("hum",model.getHumidity());
                intent.putExtra("cityName",model.getCityName());
                intent.putExtra("country",model.getRegion());
                intent.putExtra("sunset",model.getSunsetStr());
                intent.putExtra("sunrise",model.getSunriseStr());
                intent.putExtra("uv",model.getUv_index());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return forcastReportModels.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgList;
        private final TextView tempList;
        private final TextView timeList;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView_list);
            imgList = itemView.findViewById(R.id.img_list);
            tempList = itemView.findViewById(R.id.temp_list);
            timeList = itemView.findViewById(R.id.time_list);
        }
    }
}

