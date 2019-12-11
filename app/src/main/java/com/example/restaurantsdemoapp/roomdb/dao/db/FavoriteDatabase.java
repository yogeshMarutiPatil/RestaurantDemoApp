package com.example.restaurantsdemoapp.roomdb.dao.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.restaurantsdemoapp.roomdb.dao.FavouriteDao;
import com.example.restaurantsdemoapp.roomdb.dao.entity.FavoriteList;

@Database(entities={FavoriteList.class},version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {

    private static FavoriteDatabase appDatabase;

    public static FavoriteDatabase getInstance(Context context){

        if(appDatabase==null){

            appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    FavoriteDatabase.class, "myfavdb").allowMainThreadQueries().build();
        }

        return appDatabase;

    }
    public abstract FavouriteDao favoriteDao();
}
