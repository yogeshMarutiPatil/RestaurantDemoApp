package com.example.restaurantsdemoapp.utils;

import com.example.restaurantsdemoapp.model.pojo.Restaurant;

import java.util.Comparator;

public class StatusSorter implements Comparator<Restaurant> {

    @Override
    public int compare(Restaurant o1, Restaurant o2) {
        return o1.getStatus().compareToIgnoreCase(o2.getStatus());
    }
}
