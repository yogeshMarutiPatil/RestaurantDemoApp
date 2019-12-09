package com.example.restaurantsdemoapp.presenters;

import android.content.Context;

import com.example.restaurantsdemoapp.contract.FavouriteActivityContract;
import com.example.restaurantsdemoapp.model.FavouritesModel;
import com.example.restaurantsdemoapp.model.adapter.FavoriteAdapter;
import com.example.restaurantsdemoapp.roomdb.dao.entity.FavoriteList;

import java.util.List;

public class FavouriteViewPresenters implements FavouriteActivityContract.FavouritePresenter {

    FavouriteActivityContract.FavouriteView mView;
    FavouriteActivityContract.FavouriteModel mModel;

    public FavouriteViewPresenters(FavouriteActivityContract.FavouriteView view) {

        mView = view;
        mModel = new FavouritesModel();
        mView.setupUI();
    }


    @Override
    public boolean sortingOption(int id, Context context, List<FavoriteList> restaurantList, FavoriteAdapter mAdapter) {
        return mModel.sortingOption(id, context, restaurantList, mAdapter);
    }
}
