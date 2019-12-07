package com.example.restaurantsdemoapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.restaurantsdemoapp.entity.FavoriteList;

import java.util.List;
@Dao
public interface FavouriteDao{
        @Insert
        public void addData(FavoriteList favoriteList);

        @Query("select * from favoritelist")
        public List<FavoriteList> getFavoriteData();

        @Query("SELECT EXISTS (SELECT 1 FROM favoritelist WHERE name=:name)")
        public int isFavorite(String name);

        @Delete
        public void delete(FavoriteList favoriteList);


}
