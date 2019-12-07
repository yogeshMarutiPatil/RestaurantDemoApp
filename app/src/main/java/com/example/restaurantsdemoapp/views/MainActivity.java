package com.example.restaurantsdemoapp.views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.restaurantsdemoapp.R;
import com.example.restaurantsdemoapp.controller.RestaurantsJsonResponse;
import com.example.restaurantsdemoapp.db.FavoriteDatabase;
import com.example.restaurantsdemoapp.model.adapter.RestaurantRecyclerViewAdapter;
import com.example.restaurantsdemoapp.model.pojo.Restaurant;
import com.example.restaurantsdemoapp.model.pojo.Restaurants;
import com.example.restaurantsdemoapp.utils.SortOptions;
import com.example.restaurantsdemoapp.utils.StatusSorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RestaurantRecyclerViewAdapter.RestaurantAdapterListener {

    private RecyclerView mRecyclerView;
    private RestaurantRecyclerViewAdapter mAdapter;
    private List<Restaurant> restaurantList = new ArrayList<>();
    private androidx.appcompat.widget.SearchView searchView ;
    public static FavoriteDatabase favoriteDatabase;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),
                R.drawable.sort);
        toolbar.setOverflowIcon(drawable);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Restaurants");

        setUpAdapter();

        favoriteDatabase= Room.databaseBuilder(getApplicationContext(),FavoriteDatabase.class,"myfavdb").allowMainThreadQueries().build();
        restaurantParsedResponse();
    }

    private void setUpAdapter() {
        mAdapter = new RestaurantRecyclerViewAdapter(MainActivity.this, restaurantList, this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void restaurantParsedResponse() {
        String jsonResponse = RestaurantsJsonResponse.loadJSONFromAsset(this);
        Restaurants restaurantsJsonResponse = new RestaurantsJsonResponse().parseJSON(jsonResponse);
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
        //restaurantList.sort(Comparator.comparing(restaurant -> restaurant.getStatus()));
        restaurantList.sort(new StatusSorter());
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);// Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (androidx.appcompat.widget.SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_search:
                return true;

            case R.id.action_favourites:
                startActivity(new Intent(MainActivity.this,FavouriteListActivity.class));
                return true;

            case R.id.best_match:
                restaurantList.sort(Comparator.comparing(restaurant -> restaurant.getSortingValues().getBestMatch()));
                for(Restaurant rest: restaurantList){
                    rest.setSortedElement("Best Match Score: " + String.valueOf(rest.getSortingValues().getBestMatch()));
                }
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.newest:
                restaurantList.sort(Comparator.comparing(restaurant -> restaurant.getSortingValues().getNewest()));
                for(Restaurant rest: restaurantList){
                    rest.setSortedElement("Newest Rating: " +String.valueOf(rest.getSortingValues().getNewest()));
                }
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.distance:
                restaurantList.sort(Comparator.comparing(restaurant -> restaurant.getSortingValues().getDistance()));
                for(Restaurant rest: restaurantList){
                    rest.setSortedElement("Distance: " +String.valueOf(rest.getSortingValues().getDistance()));
                }
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.popularity:
                restaurantList.sort(Comparator.comparing(restaurant -> restaurant.getSortingValues().getPopularity()));
                for(Restaurant rest: restaurantList){
                    rest.setSortedElement("Popularity Score: " +String.valueOf(rest.getSortingValues().getPopularity()));
                }
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.average_product_price:
                restaurantList.sort(Comparator.comparing(restaurant -> restaurant.getSortingValues().getAverageProductPrice()));
                for(Restaurant rest: restaurantList){
                    rest.setSortedElement("Average Product Price: " +String.valueOf(rest.getSortingValues().getAverageProductPrice()));
                }
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.delivery_cost:
                restaurantList.sort(Comparator.comparing(restaurant -> restaurant.getSortingValues().getDeliveryCosts()));
                for(Restaurant rest: restaurantList){
                    rest.setSortedElement("Delivery Cost: " +String.valueOf(rest.getSortingValues().getDeliveryCosts()));
                }
                mAdapter.notifyDataSetChanged();
                return true;

            case R.id.minimum_cost:
                restaurantList.sort(Comparator.comparing(restaurant -> restaurant.getSortingValues().getMinCost()));
                for(Restaurant rest: restaurantList){
                    rest.setSortedElement("Minimum Cost: " +String.valueOf(rest.getSortingValues().getMinCost()));
                }
                mAdapter.notifyDataSetChanged();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onRestaurantSelected(Restaurant restaurant) {
        Toast.makeText(getApplicationContext(), "Selected: " + restaurant.getName() + ", " + restaurant.getStatus(), Toast.LENGTH_LONG).show();
    }
}
