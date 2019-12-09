package com.example.restaurantsdemoapp.roomdb.dao.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.restaurantsdemoapp.roomdb.dao.FavouriteDao;
import com.example.restaurantsdemoapp.roomdb.dao.entity.FavoriteList;

@Database(entities={FavoriteList.class},version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {

    public abstract FavouriteDao favoriteDao();
}
