package com.example.ericlloyd.spicefm.Main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ericlloyd.spicefm.R;
import com.example.ericlloyd.spicefm.Read.ReadActivity;
import com.example.ericlloyd.spicefm.Utils.ClickListener;
import com.example.ericlloyd.spicefm.Utils.NewsArticle;
import com.example.ericlloyd.spicefm.Utils.NewsLoader;
import com.example.ericlloyd.spicefm.Utils.NewsRecyclerAdapter;
import com.example.ericlloyd.spicefm.Utils.RecyclerTouchListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<NewsArticle>> {

    private static final String LOG_TAG = "HomeFragment";
    private String urlString = "https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=68cf2e25176a47929f85ab918aa5d325";
    private ArrayList<NewsArticle> newsArticleArrayList = new ArrayList<>();
    private RecyclerView featuredStoriesRecyclerView;
    private RecyclerView topStoriesRecyclerView;
    private RecyclerView trendingStoriesRecyclerView;
    private RecyclerView eventsRecyclerView;
    private FrameLayout progressBarFrameLayout;
    private TextView loadErrorTextView;
    private NewsRecyclerAdapter newsStoriesAdapter;
    private int loaderId = 1;
    private NewsArticle currentNewsArticle;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //frame layout holding the progressbar and error message
        progressBarFrameLayout = (FrameLayout) rootView.findViewById(R.id.progress_frame_layout);
        loadErrorTextView = (TextView) rootView.findViewById(R.id.load_error_text_view);

        //recyclerView layout for list of featured articles
        featuredStoriesRecyclerView = (RecyclerView) rootView.findViewById(R.id.featured_stories_recycler_view);
        featuredStoriesRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                featuredStoriesRecyclerView, new ClickListener() {

            @Override
            public void onClick(View view, final int position) {
                NewsArticle currentNewsArticle = newsArticleArrayList.get(position);
                //Values are passing to activity & to fragment as well
                Intent intent = new Intent(getActivity(), ReadActivity.class);
                intent.putExtra("currentNewsArticle", currentNewsArticle);
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getContext(), "Long press on position :"+position, Toast.LENGTH_LONG).show();
            }

        }));


        //vertically scrolling list of top news articles
        topStoriesRecyclerView = (RecyclerView) rootView.findViewById(R.id.top_stories_recycler_view);
        topStoriesRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                topStoriesRecyclerView, new ClickListener() {

            @Override
            public void onClick(View view, final int position) {
                currentNewsArticle = newsArticleArrayList.get(position);
                //Values are passing to activity & to fragment as well
                Intent intent = new Intent(getActivity(), ReadActivity.class);
                intent.putExtra("currentNewsArticle", currentNewsArticle);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getContext(), "Long press on position :"+position, Toast.LENGTH_LONG).show();
            }

        }));

        //vertically scrolling list of flat card trending news articles
        trendingStoriesRecyclerView = (RecyclerView) rootView.findViewById(R.id.trending_stories_recycler_view);
        trendingStoriesRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                trendingStoriesRecyclerView, new ClickListener() {

            @Override
            public void onClick(View view, final int position) {
                currentNewsArticle = newsArticleArrayList.get(position);
                //Values are passing to activity & to fragment as well
                Intent intent = new Intent(getActivity(), ReadActivity.class);
                intent.putExtra("currentNewsArticle", currentNewsArticle);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getContext(), "Long press on position :"+position, Toast.LENGTH_LONG).show();
            }

        }));

        //vertically scrolling list of events
        eventsRecyclerView = (RecyclerView) rootView.findViewById(R.id.events_recycler_view);
        eventsRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                eventsRecyclerView, new ClickListener() {

            @Override
            public void onClick(View view, final int position) {
                currentNewsArticle = newsArticleArrayList.get(position);
                //Values are passing to activity & to fragment as well
                Intent intent = new Intent(getActivity(), ReadActivity.class);
                intent.putExtra("currentNewsArticle", currentNewsArticle);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getContext(), "Long press on position :"+position, Toast.LENGTH_LONG).show();
            }

        }));


        getLoaderManager().initLoader(loaderId, null, this);
        Log.v(LOG_TAG, newsArticleArrayList.size() + "articles in the arraylist after getLoaderManager...");


        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Unregister the adapter.
        // Because the RecyclerView won't unregister the adapter, the
        // ViewHolders are very likely leaked.
        featuredStoriesRecyclerView.setAdapter(null);
        topStoriesRecyclerView.setAdapter(null);
        trendingStoriesRecyclerView.setAdapter(null);
        eventsRecyclerView.setAdapter(null);

        featuredStoriesRecyclerView = null;
        topStoriesRecyclerView = null;
        trendingStoriesRecyclerView = null;
        eventsRecyclerView = null;
    }


    @Override
    public Loader<ArrayList<NewsArticle>> onCreateLoader(int id, Bundle args) {

        Log.v(LOG_TAG, "onCreateLoader called...");
        return new NewsLoader(getContext(), urlString);

    }

    @Override
    public void onLoadFinished(Loader<ArrayList<NewsArticle>> loader, ArrayList<NewsArticle> data) {

        if (data != null) {

            //hide the progressbar once we finish loading data
            progressBarFrameLayout.setVisibility(View.GONE);

            newsArticleArrayList = data;

            newsStoriesAdapter = new NewsRecyclerAdapter(getContext(), data, "snippet_tile_mini");

            //featured stories
            //@params 2 : an id to help determine which layout file to inflate in the adapter
            featuredStoriesRecyclerView.setAdapter(newsStoriesAdapter);
            featuredStoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            Log.v(LOG_TAG, newsArticleArrayList.size() + "articles in the arraylist after recycler view...");
            //this code snippet helps make scrolling smoother
            featuredStoriesRecyclerView.setNestedScrollingEnabled(false);
            featuredStoriesRecyclerView.setHasFixedSize(true);
            //line separating recycler view items
            featuredStoriesRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                    DividerItemDecoration.VERTICAL));


            //top stories
            //@params 2 : an id to help determine which layout file to inflate in the adapter
            topStoriesRecyclerView.setAdapter(new NewsRecyclerAdapter(getContext(), data, "snippet_tile_mini"));
            topStoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            //help with smooth scrolling
            topStoriesRecyclerView.setNestedScrollingEnabled(false);
            topStoriesRecyclerView.setHasFixedSize(true);
            //line separating recycler view items
            topStoriesRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                    DividerItemDecoration.VERTICAL));


            //trending stories
            //@params 2 : an id to help determine which layout file to inflate in the adapter
            trendingStoriesRecyclerView.setAdapter(new NewsRecyclerAdapter(getContext(), data, "snippet_tile"));
            trendingStoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            //help with smooth scrolling
            trendingStoriesRecyclerView.setNestedScrollingEnabled(false);
            trendingStoriesRecyclerView.setHasFixedSize(true);
            //line separating recycler view items
            trendingStoriesRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                    DividerItemDecoration.VERTICAL));


            //events
            //@params 2 : an id to help determine which layout file to inflate in the adapter
            eventsRecyclerView.setAdapter(new NewsRecyclerAdapter(getContext(), data, "snippet_event"));
            eventsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            //help with smooth scrolling
            eventsRecyclerView.setNestedScrollingEnabled(false);
            eventsRecyclerView.setHasFixedSize(true);

        } else {

            Log.v(LOG_TAG, "onLoadFinished called but the arrayList was null...");
            progressBarFrameLayout.setVisibility(View.GONE);
            loadErrorTextView.setText("Couldn't connect to the server, please check your connection ans try again...");


        }

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<NewsArticle>> loader) {

        Log.v(LOG_TAG, "onLoaderReset called...");

        newsStoriesAdapter = null;

        getLoaderManager().destroyLoader(loaderId);

    }


}
