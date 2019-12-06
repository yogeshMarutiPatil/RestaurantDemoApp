package com.example.restaurantsdemoapp.utils;

import com.example.restaurantsdemoapp.model.pojo.Restaurant;

import java.util.Comparator;

public class SortOptions implements Comparator<Restaurant> {
    @Override
    public int compare(Restaurant o1, Restaurant o2) {
        return o1.getSortingValues().getRatingAverage().compareTo(o2.getSortingValues().getRatingAverage());
    }

}
