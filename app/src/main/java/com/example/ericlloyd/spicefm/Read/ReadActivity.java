package com.example.ericlloyd.spicefm.Read;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ericlloyd.spicefm.R;
import com.example.ericlloyd.spicefm.Utils.ClickListener;
import com.example.ericlloyd.spicefm.Utils.CustomFragmentPagerAdapter;
import com.example.ericlloyd.spicefm.Utils.NewsArticle;
import com.example.ericlloyd.spicefm.Utils.NewsLoader;
import com.example.ericlloyd.spicefm.Utils.NewsRecyclerAdapter;
import com.example.ericlloyd.spicefm.Utils.RecyclerTouchListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class ReadActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<NewsArticle>>{

    private static final int ACTIVITY_NUM = 3;
    private static final String LOG_TAG = "ReadActivity";
    private static final int LOADER_ID = 1;
    private Toolbar toolbar;
    private BottomNavigationViewEx bottomNavigation;
    private ViewPager viewPager;
    private CustomFragmentPagerAdapter pagerAdapter;
    private TabLayout tabLayout;
    private Intent intent;
    private NewsArticle currentNewsArticle;
    private TextView titleTextView;
    private TextView authorTextView;
    private TextView timeTextView;
    private TextView articleTextView;
    private ImageView articleImageView;
    private String urlString = "https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=68cf2e25176a47929f85ab918aa5d325";
    private RecyclerView readRecyclerView;
    private Context context;
    private NewsRecyclerAdapter readAdapter;
    private ArrayList<NewsArticle> newsArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        context = getApplicationContext();

        setUpToolbar();

        setUpViews();

        setUpNewsArticle();

        getSupportLoaderManager().initLoader(LOADER_ID, null, this);

    }

    /**
     * sets up the toolbar
     */
    public void setUpToolbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


    /**
     * initialize the views of the activity_read
     */
    private void setUpViews() {

        articleImageView = (ImageView) findViewById(R.id.article_image_view);
        titleTextView = (TextView) findViewById(R.id.text_view_title);
        authorTextView = (TextView) findViewById(R.id.text_view_author);
        timeTextView = (TextView) findViewById(R.id.text_view_time);
        articleTextView = (TextView) findViewById(R.id.text_view_article);

    }


    /**
     * sets the texts of the views
     */
    private void setUpNewsArticle() {

        //use the Glide library to load news images from the thumbnail url
        Glide.with(getApplicationContext())
                .load(getNewsArticle().getArticleThumbnainUrl())
                .centerCrop()
                .into(articleImageView);

        titleTextView.setText(getNewsArticle().getArticleTitle());
        authorTextView.setText(getNewsArticle().getArticleAuthor());
        timeTextView.setText(getNewsArticle().getArticleCategory());
        articleTextView.setText(getNewsArticle().getArticleExcerpt()
                + getNewsArticle().getArticleText()
                + "\n\n"
                + getString(R.string.large_text));
    }


    /**
     * retrieves the news articles from the RecommendedFragment intent
     * @return
     */
    private NewsArticle getNewsArticle() {
        intent = getIntent();
        currentNewsArticle = (NewsArticle)intent.getSerializableExtra("currentNewsArticle");

        return  currentNewsArticle;

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
     * initialize the recyclerView and assign it news articles
     * @param newsArticles the arraylist for setting up the recyclerView
     */
    private void setUpRecyclerView(final ArrayList<NewsArticle> newsArticles){

        readRecyclerView = (RecyclerView) findViewById(R.id.read_recycler_view);

        readAdapter = new NewsRecyclerAdapter(context, newsArticles, "snippet_tile_mini");
        readRecyclerView.setAdapter(readAdapter);

        readRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true));

        //this code snippet helps make scrolling smoother
        readRecyclerView.setNestedScrollingEnabled(false);
        readRecyclerView.setHasFixedSize(true);
        //line separating recycler view items
        readRecyclerView.addItemDecoration(new DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL));

        readRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(context,
                readRecyclerView, new ClickListener() {

            @Override
            public void onClick(View view, final int position) {
                currentNewsArticle = newsArticles.get(position);
                //Values are passing to activity & to fragment as well
                Intent intent = new Intent(context, ReadActivity.class);
                intent.putExtra("currentNewsArticle", currentNewsArticle);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(context, "Long press on position :"+position, Toast.LENGTH_LONG).show();
            }

        }));


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
    public Loader<ArrayList<NewsArticle>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(context, urlString);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<NewsArticle>> loader, ArrayList<NewsArticle> data) {

        newsArticles = data;

        setUpRecyclerView(data);

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<NewsArticle>> loader) {

        readAdapter = null;

    }
}
