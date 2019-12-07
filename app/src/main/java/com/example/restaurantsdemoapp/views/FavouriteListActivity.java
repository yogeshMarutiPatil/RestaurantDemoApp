package com.example.restaurantsdemoapp.views;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.restaurantsdemoapp.R;
import com.example.restaurantsdemoapp.entity.FavoriteList;
import com.example.restaurantsdemoapp.model.adapter.FavoriteAdapter;
import com.example.restaurantsdemoapp.utils.SwipeToDeleteCallback;
import com.google.android.material.snackbar.Snackbar;


import java.util.Comparator;
import java.util.List;

public class FavouriteListActivity extends AppCompatActivity {

    private RecyclerView rv;
    private FavoriteAdapter adapter;
    private List<FavoriteList> favoriteLists;
    private CoordinatorLayout coordinatorLayout;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),
                R.drawable.sort);
        toolbar.setOverflowIcon(drawable);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Favourite Restaurants");
        rv = (RecyclerView) findViewById(R.id.fav_recycler_view);
        coordinatorLayout = findViewById(R.id.coordinatedLayout);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        favoriteLists = MainActivity.favoriteDatabase.favoriteDao().getFavoriteData();
        adapter = new FavoriteAdapter(favoriteLists, getApplicationContext());
        rv.setAdapter(adapter);

        getFavData();
        enableSwipeToDeleteAndUndo();
    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final FavoriteList item = adapter.getData().get(position);

                adapter.removeItem(position);
                MainActivity.favoriteDatabase.favoriteDao().delete(item);


                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Favourite restaurant removed", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        adapter.restoreItem(item, position);
                        rv.scrollToPosition(position);
                        MainActivity.favoriteDatabase.favoriteDao().addData(item);

                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(rv);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getFavData() {
        favoriteLists.sort(Comparator.comparing(favoriteList -> favoriteList.getStatus()));
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fav_menu, menu);

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // go to previous screen when app icon in action bar is clicked
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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
        return super.onOptionsItemSelected(item);
    }
}
