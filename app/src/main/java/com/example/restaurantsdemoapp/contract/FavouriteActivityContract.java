package com.example.restaurantsdemoapp.contract;

import android.content.Context;

import com.example.restaurantsdemoapp.model.adapter.FavoriteAdapter;
import com.example.restaurantsdemoapp.model.adapter.RestaurantRecyclerViewAdapter;
import com.example.restaurantsdemoapp.model.pojo.Restaurant;
import com.example.restaurantsdemoapp.roomdb.dao.entity.FavoriteList;

import java.util.List;

public interface FavouriteActivityContract {

    interface FavouriteModel {
        public boolean sortingOption(int id, Context context, List<FavoriteList> restaurantList, FavoriteAdapter mAdapter);
    }

    interface FavouriteView {
        public void setupUI();
    }

    interface FavouritePresenter {
        public boolean sortingOption(int id, Context context, List<FavoriteList> restaurantList, FavoriteAdapter mAdapter);


    }
}
