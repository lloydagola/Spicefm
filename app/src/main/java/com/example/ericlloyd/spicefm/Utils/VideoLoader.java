package com.example.ericlloyd.spicefm.Utils;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Eric Lloyd on 16-Nov-17.
 */

public class VideoLoader extends android.support.v4.content.AsyncTaskLoader<ArrayList<Video>> {
    private String url;

    private static final String TAG = "VideoLoader";

    public VideoLoader(Context context, String url) {
        super(context);

        this.url = url;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.v(TAG, "onStartLoading...");
    }

    @Override
    public ArrayList<Video> loadInBackground() {

        ArrayList<Video> videos = QueryUtils.fetchYoutubeVideos(url);

        Log.v(TAG, "loadInBackground...");

        return videos;
    }
}
