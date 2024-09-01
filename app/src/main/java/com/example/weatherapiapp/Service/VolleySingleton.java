package com.example.weatherapiapp.Service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private RequestQueue requestQueue;
    private static VolleySingleton mInstance;
    private static Context ctx;



    private VolleySingleton(Context context){
        ctx = context;
        requestQueue = getRequestQueue();
    }


    public static synchronized VolleySingleton getInstance(Context context){
        if(mInstance == null){
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());

        }
        return requestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }
}
