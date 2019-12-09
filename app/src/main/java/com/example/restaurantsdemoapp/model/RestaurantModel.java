package com.example.restaurantsdemoapp.model;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.restaurantsdemoapp.R;
import com.example.restaurantsdemoapp.contract.MainActivityContract;
import com.example.restaurantsdemoapp.model.adapter.RestaurantRecyclerViewAdapter;
import com.example.restaurantsdemoapp.model.pojo.Restaurant;
import com.example.restaurantsdemoapp.model.pojo.Restaurants;
import com.example.restaurantsdemoapp.views.FavouriteListActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.List;

public class RestaurantModel implements MainActivityContract.Model {
    @Override
    public List<Restaurant> getRestaurantData(Context context, List<Restaurant> restaurantList) {

        String jsonResponse = loadJSONFromAsset(context);
        Restaurants restaurantsJsonResponse = parseJSON(jsonResponse);
        restaurantList.clear();
        restaurantList.addAll(restaurantsJsonResponse.getRestaurants());

        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getStatus().equals("open")) {
                restaurant.setStatus("A");
            } else if (restaurant.getStatus().equals("closed")) {
                restaurant.setStatus("C");
            } else {
                restaurant.setStatus("B");
            }
        }

        return  restaurantList;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean sortingOption(int id, Context context, List<Restaurant> restaurantList, RestaurantRecyclerViewAdapter mAdapter){
        switch (id) {
            case R.id.action_search:
                return true;

            case R.id.action_favourites:
                context.startActivity(new Intent(context, FavouriteListActivity.class));
                return true;

            case R.id.best_match:
                restaurantList.sort(Comparator.comparing(restaurant -> restaurant.getSortingValues().getBestMatch()));
                for (Restaurant rest : restaurantList) {
                    rest.setSortedElement("Best Match Score: " + String.valueOf(rest.getSortingValues().getBestMatch()));
                }
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.newest:
                restaurantList.sort(Comparator.comparing(restaurant -> restaurant.getSortingValues().getNewest()));
                for (Restaurant rest : restaurantList) {
                    rest.setSortedElement("Newest Rating: " + String.valueOf(rest.getSortingValues().getNewest()));
                }
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.distance:
                restaurantList.sort(Comparator.comparing(restaurant -> restaurant.getSortingValues().getDistance()));
                for (Restaurant rest : restaurantList) {
                    rest.setSortedElement("Distance: " + String.valueOf(rest.getSortingValues().getDistance()));
                }
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.popularity:
                restaurantList.sort(Comparator.comparing(restaurant -> restaurant.getSortingValues().getPopularity()));
                for (Restaurant rest : restaurantList) {
                    rest.setSortedElement("Popularity Score: " + String.valueOf(rest.getSortingValues().getPopularity()));
                }
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.average_product_price:
                restaurantList.sort(Comparator.comparing(restaurant -> restaurant.getSortingValues().getAverageProductPrice()));
                for (Restaurant rest : restaurantList) {
                    rest.setSortedElement("Average Product Price: " + String.valueOf(rest.getSortingValues().getAverageProductPrice()));
                }
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.delivery_cost:
                restaurantList.sort(Comparator.comparing(restaurant -> restaurant.getSortingValues().getDeliveryCosts()));
                for (Restaurant rest : restaurantList) {
                    rest.setSortedElement("Delivery Cost: " + String.valueOf(rest.getSortingValues().getDeliveryCosts()));
                }
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.minimum_cost:
                restaurantList.sort(Comparator.comparing(restaurant -> restaurant.getSortingValues().getMinCost()));
                for (Restaurant rest : restaurantList) {
                    rest.setSortedElement("Minimum Cost: " + String.valueOf(rest.getSortingValues().getMinCost()));
                }
                mAdapter.notifyDataSetChanged();
                return true;
        }
        return false;
    }

    public Restaurants parseJSON(String response) {
        // GSON and its API.
        Gson gson = new GsonBuilder().create();
        Restaurants responseObject = gson.fromJson(response, Restaurants.class);
        return responseObject;
    }

    public static String loadJSONFromAsset(Context myContext) {
        String json = null;
        try {
            InputStream is = myContext.getAssets().open("restaurantsList.json");

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return json;
    }
}
