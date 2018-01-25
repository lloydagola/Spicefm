package com.example.ericlloyd.spicefm.Discover;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ericlloyd.spicefm.Read.ReadActivity;
import com.example.ericlloyd.spicefm.Utils.ClickListener;
import com.example.ericlloyd.spicefm.Utils.NewsArticle;
import com.example.ericlloyd.spicefm.Utils.NewsRecyclerAdapter;
import com.example.ericlloyd.spicefm.Utils.QueryUtils;
import com.example.ericlloyd.spicefm.R;
import com.example.ericlloyd.spicefm.Utils.RecyclerTouchListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PoliticsFragment extends Fragment {

    /*
    the politics fragment will parse from a hardcoded json string as opposed to the api to serve as a control
     */

    private String urlString;

    //json string
    private final String JSON_RESPONSE = "{\"status\":\"ok\",\"source\":\"the-next-web\",\"sortBy\":\"latest\",\"articles\":[{\"author\":\"TNW Deals\",\"title\":\"Build and fly your very own flying, driving super drone — and it’s yours for only $32.99\",\"description\":\"Drones are cool. But as you pilot your drone craft, impressing your friends and neighbors with your aerial acrobatics, wouldn&rsquo;t it be even cooler to be able to say, &ldquo;I ...\",\"url\":\"http://data.thenextweb.com/tnw/post/build_and_fly_your_very_own_flying__driving_super_drone_-_and_its_yours_for_only__32-99\",\"urlToImage\":\"https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2017/10/iyMIcm5-1-social.jpg\",\"publishedAt\":\"2017-11-03T15:00:00Z\"},{\"author\":\"Ben Dickson\",\"title\":\"Why the gig economy needs blockchain?\",\"description\":\"The emergence of online resource-sharing and freelancing platforms have ushered in the new era of the gig economy. These services have made it possible to get or provide help on tasks ...\",\"url\":\"http://data.thenextweb.com/tnw/post/why_the_gig_economy_needs_blockchain_\",\"urlToImage\":\"https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2016/06/15-5-social.jpg\",\"publishedAt\":\"2017-11-03T14:32:00Z\"},{\"author\":\"TNW Deals\",\"title\":\"Data scientists make six figures...and with this course, you can get on the road towards one\",\"description\":\"If you want a career in numbers, here are a few serious numbers for you to mull over. Advertised data scientist jobs are paying over $105,000 annually. Meanwhile, the growing need ...\",\"url\":\"http://data.thenextweb.com/tnw/post/data_scientists_make_six_figures-and_with_this_course__you_can_get_on_the_road_towards_one\",\"urlToImage\":\"https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2017/10/X9ccwbQ-1-social.jpg\",\"publishedAt\":\"2017-11-03T14:00:00Z\"},{\"author\":\"Matthew Hughes\",\"title\":\"Germany's carmakers want to introduce a Europe-wide electric car charging network by 2020\",\"description\":\"The first charging stations will roll out this year.\",\"url\":\"https://thenextweb.com/eu/2017/11/03/germanys-carmakers-want-to-introduce-a-europe-wide-electric-car-charging-network-by-2020/\",\"urlToImage\":\"https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2017/11/588961_ionity_2017_porsche_ag-social.jpg\",\"publishedAt\":\"2017-11-03T12:41:54Z\"},{\"author\":\"Matthew Hughes\",\"title\":\"Sticky-fingered crooks lift hundreds of iPhone X's from UPS truck\",\"description\":\"Given the cheapest iPhone X costs $999, the thieves have managed to get away with over $300,000 worth of gear.\",\"url\":\"https://thenextweb.com/africa/2017/11/03/sticky-fingered-crooks-lift-hundreds-of-iphone-xs-from-ups-truck/\",\"urlToImage\":\"https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2017/10/iPhone-X-A11-Bionic-social.jpg\",\"publishedAt\":\"2017-11-03T11:52:06Z\"},{\"author\":\"Mix\",\"title\":\"Facebook is about to ruin your feed with tacky animated GIF polls\",\"description\":\"Nobody asked for this, but Facebook is currently experimenting with a new poll format on its platform that lets users vote with GIFs.\",\"url\":\"https://thenextweb.com/facebook/2017/11/03/facebook-gif-polls-test/\",\"urlToImage\":\"https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2017/11/facebook.png\",\"publishedAt\":\"2017-11-03T11:39:05Z\"},{\"author\":\"Napier Lopez\",\"title\":\"Xbox One X Review: Unremarkably remarkable\",\"description\":\"Reviewing the Xbox One X is weird. At a basic level, all there is to say is that it's an Xbox One with much better graphics. If you don't have a 4K TV or you generally don't care about ...\",\"url\":\"http://data.thenextweb.com/tnw/post/xbox_one_x_review__the_worlds_most_powerful_console_is_unremarkably_remarkable\",\"urlToImage\":\"https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2017/11/Xbox-One-X-2-of-4-social.jpg\",\"publishedAt\":\"2017-11-03T07:48:00Z\"},{\"author\":\"Scott Gerber\",\"title\":\"Eight ways tech will change the way you search online\",\"description\":\"Artificial intelligence, augmented reality and virtual reality have all seen steady improvements during the last few years, which is opening up a host of options for designers. One ...\",\"url\":\"http://data.thenextweb.com/tnw/post/eight_ways_tech_will_change_the_way_you_search_online\",\"urlToImage\":\"https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2017/10/online-search-social.jpeg\",\"publishedAt\":\"2017-11-03T07:00:00Z\"},{\"author\":\"Bryan Clark\",\"title\":\"For 11 minutes, Twitter was a Trump-free zone. It wasn't long enough.\",\"description\":\"Update: Our title mistakenly stated Trump's account was gone for six minutes – it was 11. Which still isn't enough.\\r\\n\\r\\nFor six minutes today, the world experienced social media without ...\",\"url\":\"http://data.thenextweb.com/tnw/post/trump_account_disappeared_from_twitter_for_a_glorious_6_minutes\",\"urlToImage\":\"https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2017/02/Screen-Shot-2017-02-02-at-15.18.59-social.jpg\",\"publishedAt\":\"2017-11-02T23:37:00Z\"},{\"author\":\"Bryan Clark\",\"title\":\"Facebook's 'Bonfire' comes to Android (sort of)\",\"description\":\"Facebook’s latest attempt at a standalone app — that totally isn’t a complete copy of another — is available now on Android. Well, sort of. Bonfire, Facebook’s new-ish group video chat app is now available on the Play Store. Unfortunately, you probably won’t be able to download it due to geo-restrictions. In its iOS rollout …\",\"url\":\"https://thenextweb.com/apps/2017/11/02/facebooks-bonfire-comes-android-sort/\",\"urlToImage\":\"https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2017/11/bonfire-app-social.png\",\"publishedAt\":\"2017-11-02T20:03:10Z\"}]}";
    private NewsRecyclerAdapter newsStoriesAdapter;
    private RecyclerView cardRecyclerView;
    private ArrayList<NewsArticle> newsArticleArrayList;


    public PoliticsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_politics, container, false);

        //vertically scrolling list of all news articles
        cardRecyclerView = (RecyclerView) rootView.findViewById(R.id.politics_cards_recycler_view);

        setUpRecyclerViews();


        return rootView;
    }

    /**
     * sets up the recyclerViews
     */
    private void setUpRecyclerViews() {

        newsArticleArrayList = QueryUtils.extractNewsFromJson(JSON_RESPONSE);

        newsStoriesAdapter = new NewsRecyclerAdapter(getActivity(), newsArticleArrayList, "snippet_card");

        cardRecyclerView.setAdapter(newsStoriesAdapter);
        cardRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), cardRecyclerView, new ClickListener() {

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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //helps reduce resource wastage
        linearLayoutManager.setInitialPrefetchItemCount(3);
        cardRecyclerView.setLayoutManager(linearLayoutManager);
        //help with smooth scrolling
        cardRecyclerView.setNestedScrollingEnabled(false);
        cardRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Unregister the adapter.
        // Because the RecyclerView won't unregister the adapter, the
        // ViewHolders are very likely leaked.
        cardRecyclerView.setAdapter(null);

        cardRecyclerView = null;

        newsStoriesAdapter = null;


    }
}
