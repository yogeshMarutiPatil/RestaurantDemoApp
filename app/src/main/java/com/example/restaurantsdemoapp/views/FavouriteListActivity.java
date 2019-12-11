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
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.restaurantsdemoapp.R;
import com.example.restaurantsdemoapp.contract.FavouriteActivityContract;
import com.example.restaurantsdemoapp.presenters.FavouriteViewPresenters;
import com.example.restaurantsdemoapp.roomdb.dao.entity.FavoriteList;
import com.example.restaurantsdemoapp.model.adapter.FavoriteAdapter;
import com.example.restaurantsdemoapp.utils.SwipeToDeleteCallback;
import com.google.android.material.snackbar.Snackbar;


import java.util.Comparator;
import java.util.List;

public class FavouriteListActivity extends AppCompatActivity implements FavouriteActivityContract.FavouriteView {

    private RecyclerView rv;
    private FavoriteAdapter adapter;
    private List<FavoriteList> favoriteLists;
    private CoordinatorLayout coordinatorLayout;
    FavouriteActivityContract.FavouritePresenter favPresenter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_list);
        favPresenter = new FavouriteViewPresenters(this);
        getFavData();
        enableSwipeToDeleteAndUndo();
    }

    @Override
    public void setupUI() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),
                R.drawable.sort);
        toolbar.setOverflowIcon(drawable);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.fav_restaurant);
        rv = (RecyclerView) findViewById(R.id.fav_recycler_view);
        coordinatorLayout = findViewById(R.id.coordinatedLayout);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        favoriteLists = MainActivity.favoriteDatabase.favoriteDao().getFavoriteData();
        adapter = new FavoriteAdapter(favoriteLists, getApplicationContext());
        rv.setAdapter(adapter);

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
                        .make(coordinatorLayout,R.string.fav_restaurant_removed, Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.Undo, new View.OnClickListener() {
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
        int id= item.getItemId();
        boolean temp = favPresenter.sortingOption(id, this, favoriteLists, adapter);
        if(temp)
            return temp;
        else
            return super.onOptionsItemSelected(item);
    }




}
