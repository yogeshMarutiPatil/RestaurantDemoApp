package com.example.restaurantsdemoapp.presenters;

import android.content.Context;

import com.example.restaurantsdemoapp.contract.MainActivityContract;
import com.example.restaurantsdemoapp.model.RestaurantModel;
import com.example.restaurantsdemoapp.model.adapter.RestaurantRecyclerViewAdapter;
import com.example.restaurantsdemoapp.model.pojo.Restaurant;

import java.util.List;

public class MainViewPresenters implements MainActivityContract.Presenter{

    MainActivityContract.View mView;
    MainActivityContract.Model mModel;

    public MainViewPresenters(MainActivityContract.View view) {

        mView = view;
        mModel = new RestaurantModel();
        mView.setupUI();
    }


    @Override
    public List<Restaurant> getRestaurantData(Context context, List<Restaurant> restaurantList) {
        restaurantList  = mModel.getRestaurantData(context, restaurantList);
        return restaurantList;
    }

    @Override
    public boolean sortingOption(int id, Context context, List<Restaurant> restaurantList, RestaurantRecyclerViewAdapter adapter){
        return mModel.sortingOption(id, context, restaurantList, adapter);
    }
}
