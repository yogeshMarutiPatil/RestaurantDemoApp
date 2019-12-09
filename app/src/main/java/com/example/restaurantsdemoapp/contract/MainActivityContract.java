package com.example.restaurantsdemoapp.contract;

import android.content.Context;

import com.example.restaurantsdemoapp.model.adapter.RestaurantRecyclerViewAdapter;
import com.example.restaurantsdemoapp.model.pojo.Restaurant;

import java.util.List;

public interface MainActivityContract {

    interface Model {
        public List<Restaurant> getRestaurantData(Context context, List<Restaurant> restaurantList);
        public boolean sortingOption(int id, Context context, List<Restaurant> restaurantList, RestaurantRecyclerViewAdapter mAdapter);
    }

    interface View {
        public void setupUI();
        public void getRestaurantData();
    }

    interface Presenter {
        public List<Restaurant> getRestaurantData(Context context, List<Restaurant> restaurantList);
        public boolean sortingOption(int id, Context context, List<Restaurant> restaurantList, RestaurantRecyclerViewAdapter mAdapter);
    }
}
