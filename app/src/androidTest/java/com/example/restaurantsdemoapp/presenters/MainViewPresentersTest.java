package com.example.restaurantsdemoapp.presenters;

import android.content.Context;
import androidx.test.InstrumentationRegistry;
import com.example.restaurantsdemoapp.contract.MainActivityContract;
import com.example.restaurantsdemoapp.model.pojo.Restaurants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import static org.junit.Assert.assertEquals;


public class MainViewPresentersTest {

    private MainActivityContract.Presenter mainPresenter;

    @Mock
    private MainActivityContract.View mainView;
    @Mock
    Context context;
    @Mock
    InputStream inputStream;

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        mainPresenter = new MainViewPresenters(mainView);

    }

    @Test
    public void getRestaurantData() throws Exception{
        Context ctx = InstrumentationRegistry.getTargetContext();
        InputStream is = ctx.getResources().getAssets().open("restaurantsList.json");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        String json = new String(buffer, StandardCharsets.UTF_8);
        Gson gson = new GsonBuilder().create();
        Restaurants responseObject = gson.fromJson(json, Restaurants.class);

        assertEquals("Tandoori Express", responseObject.getRestaurants().get(1).getName().toString());
        assertEquals("closed", responseObject.getRestaurants().get(1).getStatus().toString());
        assertEquals(1.0, responseObject.getRestaurants().get(1).getSortingValues().getBestMatch(),1);
        assertEquals(266.0, responseObject.getRestaurants().get(1).getSortingValues().getNewest(),1);
        assertEquals(4.5, responseObject.getRestaurants().get(1).getSortingValues().getRatingAverage(),1);
        assertEquals(2308, responseObject.getRestaurants().get(1).getSortingValues().getDistance().doubleValue(),1);
        assertEquals(123.0, responseObject.getRestaurants().get(1).getSortingValues().getPopularity(),1);
        assertEquals(1146, responseObject.getRestaurants().get(1).getSortingValues().getAverageProductPrice().doubleValue(),1);
        assertEquals(150, responseObject.getRestaurants().get(1).getSortingValues().getDeliveryCosts().doubleValue(),1);
        assertEquals(1300, responseObject.getRestaurants().get(1).getSortingValues().getMinCost().doubleValue(),1);


    }


}