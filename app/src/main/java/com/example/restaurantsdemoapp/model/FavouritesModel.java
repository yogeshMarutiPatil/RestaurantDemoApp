package com.example.restaurantsdemoapp.model;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.example.restaurantsdemoapp.R;
import com.example.restaurantsdemoapp.contract.FavouriteActivityContract;
import com.example.restaurantsdemoapp.model.adapter.FavoriteAdapter;
import com.example.restaurantsdemoapp.roomdb.dao.entity.FavoriteList;
import com.example.restaurantsdemoapp.views.MainActivity;

import java.util.Comparator;
import java.util.List;

public class FavouritesModel implements FavouriteActivityContract.FavouriteModel{

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean sortingOption(int id, Context context, List<FavoriteList> favoriteLists, FavoriteAdapter adapter){
        switch (id) {
            case android.R.id.home:
                // go to previous screen when app icon in action bar is clicked
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
                return true;

            case R.id.best_match:
                favoriteLists.sort(Comparator.comparing(restaurant -> restaurant.getSortingValues().getBestMatch()));
                for (FavoriteList favoriteList : favoriteLists) {
                    favoriteList.setSortElement("Best Match Score: " + String.valueOf(favoriteList.getSortingValues().getBestMatch()));
                }
                adapter.notifyDataSetChanged();
                return true;

            case R.id.newest:
                favoriteLists.sort(Comparator.comparing(restaurant -> restaurant.getSortingValues().getNewest()));
                for (FavoriteList favoriteList : favoriteLists) {
                    favoriteList.setSortElement("Newest Rating: " + String.valueOf(favoriteList.getSortingValues().getNewest()));
                }
                adapter.notifyDataSetChanged();
                return true;

            case R.id.distance:
                favoriteLists.sort(Comparator.comparing(restaurant -> restaurant.getSortingValues().getDistance()));
                for (FavoriteList favoriteList : favoriteLists) {
                    favoriteList.setSortElement("Distance: " + String.valueOf(favoriteList.getSortingValues().getDistance()));
                }
                adapter.notifyDataSetChanged();
                return true;

            case R.id.popularity:
                favoriteLists.sort(Comparator.comparing(restaurant -> restaurant.getSortingValues().getPopularity()));
                for (FavoriteList favoriteList : favoriteLists) {
                    favoriteList.setSortElement("Popularity Score: " + String.valueOf(favoriteList.getSortingValues().getPopularity()));
                }
                adapter.notifyDataSetChanged();
                return true;

            case R.id.average_product_price:
                favoriteLists.sort(Comparator.comparing(restaurant -> restaurant.getSortingValues().getAverageProductPrice()));
                for (FavoriteList favoriteList : favoriteLists) {
                    favoriteList.setSortElement("Average Product Price: " + String.valueOf(favoriteList.getSortingValues().getAverageProductPrice()));
                }
                adapter.notifyDataSetChanged();
                return true;

            case R.id.delivery_cost:
                favoriteLists.sort(Comparator.comparing(restaurant -> restaurant.getSortingValues().getDeliveryCosts()));
                for (FavoriteList favoriteList : favoriteLists) {
                    favoriteList.setSortElement("Delivery Cost: " + String.valueOf(favoriteList.getSortingValues().getDeliveryCosts()));
                }
                adapter.notifyDataSetChanged();
                return true;

            case R.id.minimum_cost:
                favoriteLists.sort(Comparator.comparing(restaurant -> restaurant.getSortingValues().getMinCost()));
                for (FavoriteList favoriteList : favoriteLists) {
                    favoriteList.setSortElement("Minimum Cost: " + String.valueOf(favoriteList.getSortingValues().getMinCost()));
                }
                adapter.notifyDataSetChanged();
                return true;
        }
        return false;
    }



}
