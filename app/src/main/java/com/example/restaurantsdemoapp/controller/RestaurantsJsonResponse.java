package com.example.restaurantsdemoapp.controller;

import android.content.Context;

import com.example.restaurantsdemoapp.model.pojo.Restaurants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;

public class RestaurantsJsonResponse {
    public Restaurants parseJSON(String response) {
        // GSON and its API.
        Gson gson = new GsonBuilder().create();
        Restaurants responseObject = gson.fromJson(response, Restaurants.class);
        return responseObject;
    }

    public static String loadJSONFromAsset(Context myContext) {
        String json = null;
        try {
            InputStream is = myContext.getAssets().open("restaurantsList.json");

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return json;
    }
}
