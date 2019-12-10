package com.example.restaurantsdemoapp.views;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.restaurantsdemoapp.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class FavouriteListActivityTest {

    private FavouriteListActivity favActivity;
    private RecyclerView favRecyclerView;
    private int fav_resId = R.id.fav_recycler_view;
    private int fav_itemCount = 0;

    @Rule
    public ActivityTestRule<FavouriteListActivity> favActivityActivityTestRule = new ActivityTestRule<>(FavouriteListActivity.class);

    @Before
    public void setUpTest() {
        this.favActivity = this.favActivityActivityTestRule.getActivity();
        this.favRecyclerView = this.favActivity.findViewById(this.fav_resId);
        this.fav_itemCount = this.favRecyclerView.getAdapter().getItemCount();
    }

    @Test
    public void RecyclerViewTest() {
        if (this.fav_itemCount > 0) {
            for (int i = 0; i < this.fav_itemCount; i++) {
                onView(withId(this.fav_resId))
                        .perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));


            }
        }
    }

    @Test
    public void recyclerScrollTest() {

        RecyclerView recyclerView = favActivityActivityTestRule.getActivity().findViewById(R.id.recycler_view);
        int itemcount = recyclerView.getAdapter().getItemCount();
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollToPosition(itemcount - 1));

    }

    @Test
    public void appBarButtonsTest() {
        onView(withId(R.id.action_search)).perform(click());
        onView(withId(R.id.action_favourites)).perform(click());
    }

}