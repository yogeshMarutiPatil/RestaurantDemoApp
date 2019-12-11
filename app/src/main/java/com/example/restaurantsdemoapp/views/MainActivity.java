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
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.restaurantsdemoapp.R;
import com.example.restaurantsdemoapp.contract.MainActivityContract;
import com.example.restaurantsdemoapp.roomdb.dao.db.FavoriteDatabase;
import com.example.restaurantsdemoapp.model.adapter.RestaurantRecyclerViewAdapter;
import com.example.restaurantsdemoapp.model.pojo.Restaurant;
import com.example.restaurantsdemoapp.presenters.MainViewPresenters;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    MainActivityContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private RestaurantRecyclerViewAdapter mAdapter;
    private List<Restaurant> restaurantList = new ArrayList<>();
    private androidx.appcompat.widget.SearchView searchView;
    public static FavoriteDatabase favoriteDatabase;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainViewPresenters(this);
        getRestaurantData();
        favoriteDatabase= FavoriteDatabase.getInstance(getApplicationContext());
    }




    @Override
    public void setupUI() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),
                R.drawable.sort);
        toolbar.setOverflowIcon(drawable);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(R.string.restaurants);
        mAdapter = new RestaurantRecyclerViewAdapter(MainActivity.this, restaurantList);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void getRestaurantData() {
        restaurantList = mPresenter.getRestaurantData(this, restaurantList);
        restaurantList.sort(Comparator.comparing(restaurant -> restaurant.getStatus()));
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
        int id = item.getItemId();
        //return mPresenter.sortingOption(id, this, restaurantList, mAdapter);
        boolean temp = mPresenter.sortingOption(id, this, restaurantList, mAdapter);
        if(temp)
            return temp;
        else
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


}
