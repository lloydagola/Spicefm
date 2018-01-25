package com.example.ericlloyd.spicefm.Bookmarks;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ericlloyd.spicefm.Discover.PoliticsFragment;
import com.example.ericlloyd.spicefm.R;
import com.example.ericlloyd.spicefm.Utils.BottomNavigationHelper;
import com.example.ericlloyd.spicefm.Utils.CustomFragmentPagerAdapter;
import com.example.ericlloyd.spicefm.Utils.NewsArticle;
import com.example.ericlloyd.spicefm.Utils.Video;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class BookmarksActivity extends AppCompatActivity {

    private static final int ACTIVITY_NUM = 3;
    private static final String LOG_TAG = "BookmarksActivity";
    private Toolbar toolbar;
    private BottomNavigationViewEx bottomNavigation;
    private ViewPager viewPager;
    private CustomFragmentPagerAdapter pagerAdapter;
    private TabLayout tabLayout;
    private RecyclerView bookmarksRecyclerView;
    private CustomFragmentPagerAdapter customFragmentPagerAdapter;

    private ArrayList<NewsArticle> newsArticles;
    private ArrayList<Video> videos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        setUpToolbar();

        setUpBottomNavigationView();

        setUpViewPager();

    }


    /**
     * sets up the toolbar
     */
    public void setUpToolbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }


    /**
     * create and initialize the bottom navigation bar
     */
    public void setUpBottomNavigationView() {

        bottomNavigation = (BottomNavigationViewEx) findViewById(R.id.bottom_navigation_view);
        BottomNavigationHelper.customizeNavigationBar(bottomNavigation);
        BottomNavigationHelper.activateNavigationBar(getApplicationContext(), bottomNavigation);

        Menu menu = bottomNavigation.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

    }


    /**
     * initialize the Main Activity ViewPager
     * initialize the CustomFragmentPagerAdapter
     * set up the fragments with the tabs
     */
    public void setUpViewPager() {

        viewPager = (ViewPager) findViewById(R.id.view_pager);

        customFragmentPagerAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager());
        addFragmentsToPagerAdapter();

        viewPager.setAdapter(customFragmentPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Articles");
        tabLayout.getTabAt(1).setText("Videos");

    }


    /**
     * creates new Fragments and adds them to the CustomFragmentPagerAdapter
     */
    public void addFragmentsToPagerAdapter() {
        customFragmentPagerAdapter.addFragment(new PoliticsFragment());
        customFragmentPagerAdapter.addFragment(new PoliticsFragment());

    }


    /**
     * inflate the top menu(in the top tool bar)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        Log.d(LOG_TAG, "onCreateOptionsMenu...");

        return true;
    }


    /**
     * initialize the top menu(in the top tool bar)
     *
     * @param item: the menu item that was clicked
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the ActivityMain/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        Log.d(LOG_TAG, "onOptionsItemSelected...");
        return super.onOptionsItemSelected(item);
    }
}
