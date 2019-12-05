package com.example.restaurantsdemoapp.controller;

import android.util.Log;

import com.example.restaurantsdemoapp.model.pojo.Restaurant;
import com.example.restaurantsdemoapp.model.pojo.Restaurants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RestaurantsJsonResponse {
    public Restaurants parseJSON(String response) {
        // GSON and its API.
        Gson gson = new GsonBuilder().create();
        Restaurants responseObject = gson.fromJson(response, Restaurants.class);
        return responseObject;
    }
}
