package com.example.ericlloyd.spicefm.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.example.ericlloyd.spicefm.Bookmarks.BookmarksActivity;
import com.example.ericlloyd.spicefm.Discover.DiscoverActivity;
import com.example.ericlloyd.spicefm.Main.MainActivity;
import com.example.ericlloyd.spicefm.Settings.SettingsActivity;
import com.example.ericlloyd.spicefm.Videos.VideosActivity;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import static com.example.ericlloyd.spicefm.R.id.action_bookmarks;
import static com.example.ericlloyd.spicefm.R.id.action_discover;
import static com.example.ericlloyd.spicefm.R.id.action_home;
import static com.example.ericlloyd.spicefm.R.id.action_preferences;
import static com.example.ericlloyd.spicefm.R.id.action_videos;

/**
 * Created by Eric Lloyd on 14-Nov-17.
 */

public class BottomNavigationHelper {


    private BottomNavigationHelper(){

    }

    public static void customizeNavigationBar(BottomNavigationViewEx bottomNavigationViewEx){

        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.setTextVisibility(false);

    }

    public static void activateNavigationBar(final Context context, final BottomNavigationViewEx bottomNavigationViewEx){

        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case action_home:
                        context.startActivity(new Intent(context, MainActivity.class));
                        break;

                    case action_discover:
                        context.startActivity(new Intent(context, DiscoverActivity.class));
                        break;

                    case action_videos:
                        context.startActivity(new Intent(context, VideosActivity.class));
                        break;

                    case action_bookmarks:
                        context.startActivity(new Intent(context, BookmarksActivity.class));
                        break;

                    case action_preferences:
                        context.startActivity(new Intent(context, SettingsActivity.class));
                        break;
                }



                return false;
            }
        });

    }


}
