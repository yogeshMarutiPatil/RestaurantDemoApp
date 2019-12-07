package com.example.restaurantsdemoapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.restaurantsdemoapp.dao.FavouriteDao;
import com.example.restaurantsdemoapp.entity.FavoriteList;

@Database(entities={FavoriteList.class},version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {

    public abstract FavouriteDao favoriteDao();
}
