package com.example.ericlloyd.spicefm.Videos;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ericlloyd.spicefm.Discover.PoliticsFragment;
import com.example.ericlloyd.spicefm.R;
import com.example.ericlloyd.spicefm.Read.ReadActivity;
import com.example.ericlloyd.spicefm.Settings.SettingsActivity;
import com.example.ericlloyd.spicefm.Utils.BottomNavigationHelper;
import com.example.ericlloyd.spicefm.Utils.ClickListener;
import com.example.ericlloyd.spicefm.Utils.CustomFragmentPagerAdapter;
import com.example.ericlloyd.spicefm.Utils.NewsArticle;
import com.example.ericlloyd.spicefm.Utils.RecyclerTouchListener;
import com.example.ericlloyd.spicefm.Utils.Video;
import com.example.ericlloyd.spicefm.Utils.VideoLoader;
import com.example.ericlloyd.spicefm.Utils.VideosRecylcerAdapter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import static android.support.design.widget.TabLayout.MODE_SCROLLABLE;
import static java.security.AccessController.getContext;

public class VideosActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Video>>{

    private static final int ACTIVITY_NUM = 2;
    private static final String LOG_TAG = "DiscoverFragment";
    private Toolbar toolbar;
    private BottomNavigationViewEx bottomNavigation;
    private ViewPager viewPager;
    private CustomFragmentPagerAdapter pagerAdapter;
    private TabLayout tabLayout;
    private String urlString = "https://www.googleapis.com/youtube/v3/videos?part=snippet%2CcontentDetails%2Cstatistics&chart=mostPopular&maxResults=20&regionCode=US&key=AIzaSyADhW47o_MIqY-dCeKkGIMu43XwX3ecRFY";
    private int loaderId = 1;
    private RecyclerView videosRecyclerView;
    private ArrayList<Video> videosArrayList = new ArrayList<>();
    private VideosRecylcerAdapter videosRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        setUpToolbar();

        setUpBottomNavigationView();

        //setUpRecyclerView();


        getSupportLoaderManager().initLoader(loaderId, null, this);

    }


    /**
     * sets up the toolbar
     */
    public void setUpToolbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
     * set up the recyclerView
     */
    private void setUpRecyclerViews(final ArrayList<Video> videosArrayList){
        videosRecyclerView = (RecyclerView) findViewById(R.id.videos_recycler_view);

        videosRecyclerAdapter = new VideosRecylcerAdapter(getApplicationContext(), videosArrayList);
        videosRecyclerView.setAdapter(videosRecyclerAdapter);
        videosRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        //help with smooth scrolling
        videosRecyclerView.setNestedScrollingEnabled(false);
        videosRecyclerView.setHasFixedSize(true);
        //line separating recycler view items
        videosRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL));


        videosRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, videosRecyclerView, new ClickListener() {

            @Override
            public void onClick(View view, final int position) {
                Video currentVideo = videosArrayList.get(position);
                //Values are passing to activity & to fragment as well
                Intent intent = new Intent(VideosActivity.this, SettingsActivity.class);
                //intent.putExtra("currentVideo", currentVideo);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));

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

    @Override
    public Loader<ArrayList<Video>> onCreateLoader(int id, Bundle args) {

        Log.v(LOG_TAG, "onCreateLoader called...");
        return new VideoLoader(getApplicationContext(), urlString);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Video>> loader, ArrayList<Video> data) {

        if(data!=null) {

            setUpRecyclerViews(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Video>> loader) {

        Log.v(LOG_TAG, "onLoaderReset called...");

        videosRecyclerAdapter = null;

        getLoaderManager().destroyLoader(loaderId);

    }
}
