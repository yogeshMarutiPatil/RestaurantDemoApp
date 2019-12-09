package com.example.restaurantsdemoapp.model.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantsdemoapp.R;
import com.example.restaurantsdemoapp.roomdb.dao.entity.FavoriteList;
import com.example.restaurantsdemoapp.model.pojo.Restaurant;
import com.example.restaurantsdemoapp.views.MainActivity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RestaurantRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantRecyclerViewAdapter.ViewHolder> implements Filterable {
    private OnItemClickListener clickListener;
    private Context context;
    private List<Restaurant> restaurantList;
    private List<Restaurant> restaurantListFiltered;

    public RestaurantRecyclerViewAdapter(Context context, List<Restaurant> restaurantList) {
        super();
        this.restaurantList = restaurantList;
        this.restaurantListFiltered = restaurantList;
        this.context = context;

    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_layout, viewGroup, false);
        return new ViewHolder(v);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.bindData(restaurantListFiltered.get(i));
        viewHolder.ib_favourite.setTag(i);
    }


    @Override
    public int getItemCount() {
        return restaurantListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<Restaurant> filteredList = new ArrayList<>();
                String charString = charSequence.toString();
                if (charString == null || charString.length() == 0) {
                    for (Restaurant rest : restaurantList) {
                        rest.setSortedElement(null);
                    }
                    restaurantListFiltered = restaurantList;
                } else {
                    String filterpattern = charSequence.toString().toLowerCase().trim();
                    for (Restaurant restaurant : restaurantList) {
                        if (restaurant.getName().toLowerCase().contains(filterpattern)) {
                            filteredList.add(restaurant);
                        }
                    }

                    for (Restaurant rest : restaurantList) {
                        rest.setSortedElement(null);
                    }

                    restaurantListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = restaurantListFiltered;
                return filterResults;
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                restaurantListFiltered = (ArrayList<Restaurant>) filterResults.values;
                restaurantList.sort(Comparator.comparing(restaurant -> restaurant.getStatus()));
                notifyDataSetChanged();
            }
        };
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_name;
        TextView tv_rating;
        TextView tv_opening_hour;
        TextView tv_sorted_element;
        ImageButton ib_favourite;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            tv_name = itemView.findViewById(R.id.tvName);
            tv_rating = itemView.findViewById(R.id.tvRating);
            tv_opening_hour = itemView.findViewById(R.id.tvOpeningHour);
            tv_sorted_element = itemView.findViewById(R.id.tvSortedElement);
            ib_favourite = itemView.findViewById(R.id.ibFavorite);

            ib_favourite.setOnClickListener(onFavouriteClickListener);
        }

        private View.OnClickListener onFavouriteClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int index = (int) v.getTag();
                FavoriteList favoriteList = new FavoriteList();

                favoriteList.setName(restaurantList.get(index).getName());
                favoriteList.setStatus(restaurantList.get(index).getStatus());
                favoriteList.setSortingValues(restaurantList.get(index).getSortingValues());

                if (MainActivity.favoriteDatabase.favoriteDao().isFavorite(restaurantList.get(index).getName()) != 1) {
                    ib_favourite.setImageResource(R.drawable.ic_favorite_red);
                    MainActivity.favoriteDatabase.favoriteDao().addData(favoriteList);

                } else {
                    ib_favourite.setImageResource(R.drawable.ic_favorite_grey);
                    MainActivity.favoriteDatabase.favoriteDao().delete(favoriteList);

                }
            }
        };


        @Override
        public void onClick(View v) {
            //clickListener.onItemClick(v, getAdapterPosition());
        }


        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        private void bindData(Restaurant restaurant) {
            tv_name.setText(restaurant.getName());
            tv_rating.setText(String.valueOf(restaurant.getSortingValues().getRatingAverage()));

            tv_sorted_element.setText(restaurant.getSortedElement());

            if (restaurant.getSortingValues().getRatingAverage() >= 3) {
                tv_rating.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_rating_background_green));
            } else if (restaurant.getSortingValues().getRatingAverage() >= 2) {
                tv_rating.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_rating_background_yellow));
            } else {
                tv_rating.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_rating_background_red));
            }

            if (restaurant.getStatus().equalsIgnoreCase("A")) {
                tv_opening_hour.setTextColor(ContextCompat.getColor(context, R.color.green));
                tv_opening_hour.setText("Open");
            } else if (restaurant.getStatus().equalsIgnoreCase("C")) {
                tv_opening_hour.setTextColor(ContextCompat.getColor(context, R.color.red));
                tv_opening_hour.setText("Closed");
            } else {
                tv_opening_hour.setTextColor(ContextCompat.getColor(context, R.color.duskYellow));
                tv_opening_hour.setText("Order Ahead");
            }

            if (MainActivity.favoriteDatabase.favoriteDao().isFavorite(restaurant.getName()) == 1)
                ib_favourite.setImageResource(R.drawable.ic_favorite_red);
            else
                ib_favourite.setImageResource(R.drawable.ic_favorite_grey);


        }

    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onFavoriteClick(View view, int position);
    }

}