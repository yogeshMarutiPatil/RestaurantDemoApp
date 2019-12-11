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


}