package com.example.restaurantsdemoapp.roomdb.dao;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.restaurantsdemoapp.roomdb.dao.db.FavoriteDatabase;
import com.example.restaurantsdemoapp.roomdb.dao.entity.FavoriteList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class FavouriteDaoTest {
    private FavouriteDao favouriteDao;
    private FavoriteDatabase favoriteDatabase;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        favoriteDatabase = Room.inMemoryDatabaseBuilder(context, FavoriteDatabase.class).build();
        favouriteDao = favoriteDatabase.favoriteDao();
    }

    @After
    public void closeDb() throws IOException {
        favoriteDatabase.close();
    }

    @Test
    public void writeFavouriteAndReadInList() throws Exception {
        FavoriteList favoriteList= new FavoriteList();
        favoriteList.setName("Tandoori Express");
        favouriteDao.addData(favoriteList);
        List<FavoriteList> byName = favouriteDao.getFavoriteData();
        assertThat(byName.get(0).getName(), equalTo(favoriteList.getName()));
    }

}