package com.example.restaurantsdemoapp.presenters;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.restaurantsdemoapp.contract.MainActivityContract;
import com.example.restaurantsdemoapp.model.RestaurantModel;
import com.example.restaurantsdemoapp.views.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static org.mockito.Mockito.doReturn;


public class MainViewPresentersTest {

    private MainActivityContract.Presenter mainPresenter;

    @Mock
    private MainActivityContract.View mainView;
    @Mock
    Context context;
    @Mock
    AssetManager assetManager;
    @Mock
    InputStream inputStream;

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        mainPresenter = new MainViewPresenters(mainView);
        doReturn(assetManager).when(context).getAssets();
        inputStream= context.getAssets().open("restaurantsList.json");

    }

    @Test
    public void getRestaurantData() throws Exception{

        //Mockito.when(androidContext.getAssets().open("")).thenReturn()
    }

}