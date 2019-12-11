package com.example.restaurantsdemoapp.model.adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantsdemoapp.R;
import com.example.restaurantsdemoapp.roomdb.dao.entity.FavoriteList;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private List<FavoriteList> favoriteLists;
    Context context;

    public FavoriteAdapter(List<FavoriteList> favoriteLists, Context context) {
        this.favoriteLists = favoriteLists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fav_recycler_view_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bindData(favoriteLists.get(position));

    }

    @Override
    public int getItemCount() {
        return favoriteLists.size();
    }

    public void removeItem(int position) {
        favoriteLists.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(FavoriteList item, int position) {
        favoriteLists.add(position, item);
        notifyItemInserted(position);
    }

    public List<FavoriteList> getData() {
        return favoriteLists;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_rating;
        TextView tv_opening_hour;
        TextView tv_sorted_element;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tvName);
            tv_rating = itemView.findViewById(R.id.tvRating);
            tv_opening_hour = itemView.findViewById(R.id.tvOpeningHour);
            tv_sorted_element = itemView.findViewById(R.id.tvSortedElement);

        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        private void bindData(FavoriteList favoriteList) {
            tv_name.setText(favoriteList.getName());
            tv_rating.setText(String.valueOf(favoriteList.getSortingValues().getRatingAverage()));

            tv_sorted_element.setText(favoriteList.getSortElement());

            if (favoriteList.getSortingValues().getRatingAverage() >= 3) {
                tv_rating.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_rating_background_green));
            } else if (favoriteList.getSortingValues().getRatingAverage() >= 2) {
                tv_rating.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_rating_background_yellow));
            } else {
                tv_rating.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_rating_background_red));
            }

            if (favoriteList.getStatus().equalsIgnoreCase("A")) {
                tv_opening_hour.setTextColor(ContextCompat.getColor(context, R.color.green));
                tv_opening_hour.setText(R.string.open);
            } else if (favoriteList.getStatus().equalsIgnoreCase("C")) {
                tv_opening_hour.setTextColor(ContextCompat.getColor(context, R.color.red));
                tv_opening_hour.setText(R.string.closed);
            } else {
                tv_opening_hour.setTextColor(ContextCompat.getColor(context, R.color.duskYellow));
                tv_opening_hour.setText(R.string.order_ahead);
            }


        }
    }


}
