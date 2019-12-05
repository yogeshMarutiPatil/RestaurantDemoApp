package com.example.restaurantsdemoapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.restaurantsdemoapp.R;
import com.example.restaurantsdemoapp.controller.RestaurantsJsonResponse;
import com.example.restaurantsdemoapp.model.adapter.RestaurantRecyclerViewAdapter;
import com.example.restaurantsdemoapp.model.pojo.Restaurant;
import com.example.restaurantsdemoapp.model.pojo.Restaurants;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RestaurantRecyclerViewAdapter.RestaurantAdapterListener {

    private RecyclerView mRecyclerView;
    private ProgressBar pbLoading;
    private RestaurantRecyclerViewAdapter mAdapter;
    private List<Restaurant> restaurantList = new ArrayList<>();
    private androidx.appcompat.widget.SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Restaurants");
        mAdapter = new RestaurantRecyclerViewAdapter(MainActivity.this, restaurantList,this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(false);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        restaurantParsedResponse();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //restaurantParsedResponse();
    }

    private void restaurantParsedResponse() {
        String jsonResponse = loadJSONFromAsset();
        Restaurants restaurantsJsonResponse = new RestaurantsJsonResponse().parseJSON(jsonResponse);


        //mAdapter = new RestaurantRecyclerViewAdapter(MainActivity.this, restaurantsJsonResponse.getRestaurants(),this);
        restaurantList.clear();
        restaurantList.addAll(restaurantsJsonResponse.getRestaurants());

        //mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        //Log.d("Inputstring",restaurant.getName());


    }

    private String loadJSONFromAsset() {
        String json = null;

        try {
            InputStream is = getAssets().open("restaurantsList.json");

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            // Log.d("Inputstring",json.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return json;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);// Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (androidx.appcompat.widget.SearchView) menu.findItem(R.id.action_search)
                .getActionView();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
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
