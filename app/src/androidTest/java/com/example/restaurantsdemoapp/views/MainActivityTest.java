package com.example.restaurantsdemoapp.views;

import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.espresso.Espresso;

import com.example.restaurantsdemoapp.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private MainActivity mActivity;
    private RecyclerView mRecyclerView;
    private int resId = R.id.recycler_view;
    private int itemCount = 0;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUpTest() {
        this.mActivity = this.mainActivityActivityTestRule.getActivity();
        this.mRecyclerView = this.mActivity.findViewById(this.resId);
        this.itemCount = this.mRecyclerView.getAdapter().getItemCount();
    }

    @Test
    public void RecyclerViewTest() {
        if (this.itemCount > 0) {
            for (int i = 0; i < this.itemCount; i++) {
                onView(withId(this.resId))
                        .perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));


            }
        }
    }

    @Test
    public void recyclerScrollTest() {

        RecyclerView recyclerView = mainActivityActivityTestRule.getActivity().findViewById(R.id.recycler_view);
        int itemcount = recyclerView.getAdapter().getItemCount();
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollToPosition(itemcount - 1));

    }

    @Test
    public void appBarButtonsTest() {
        onView(withId(R.id.action_search)).perform(click());
        onView(withId(R.id.action_favourites)).perform(click());
    }

    @Test
    public void appBarSortButtonsTest() {

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        //onView(withId(R.id.newest)).perform(click());
        //onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(1,click()));

        /*onView(withId(R.drawable.sort)).perform(click());
        onView(withId(R.id.best_match)).perform(click());
        onView(withId(R.id.newest)).perform(click());
        onView(withId(R.id.distance)).perform(click());
        onView(withId(R.id.popularity)).perform(click());
        onView(withId(R.id.average_product_price)).perform(click());
        onView(withId(R.id.delivery_cost)).perform(click());
        onView(withId(R.id.minimum_cost)).perform(click());*/
    }


}