package com.example.ericlloyd.spicefm.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ericlloyd.spicefm.R;

import java.util.ArrayList;

import static com.example.ericlloyd.spicefm.R.layout.snippet_card;
import static com.example.ericlloyd.spicefm.R.layout.snippet_event;
import static com.example.ericlloyd.spicefm.R.layout.snippet_tile;
import static com.example.ericlloyd.spicefm.R.layout.snippet_tile_mini;

/**
 * Created by Eric Lloyd on 31-Oct-17.
 */

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.FeaturedStoriesViewHolder> {

    private Context context;
    private ArrayList<NewsArticle> newsArticleArrayList;
    private String layoutType;

    public NewsRecyclerAdapter(Context context, ArrayList<NewsArticle> newsArticleArrayList, String layoutType) {

        this.context = context;
        this.newsArticleArrayList = newsArticleArrayList;
        this.layoutType = layoutType;

    }


    @Override
    public FeaturedStoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

        switch (layoutType) {

            case "snippet_event":

                //views for the events
                view = LayoutInflater.from(context).inflate(snippet_event, parent, false);

                break;

            case "snippet_tile_mini":

                //views for the mini-tiles (top stories)
                view = LayoutInflater.from(context).inflate(snippet_tile_mini, parent, false);

                break;

            case "snippet_tile":

                //views for the tile layout (trending stories)
                view = LayoutInflater.from(context).inflate(snippet_tile, parent, false);

                break;

            case "snippet_card":

                //views for the cards
                view = LayoutInflater.from(context).inflate(snippet_card, parent, false);

                break;


        }

        return new FeaturedStoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeaturedStoriesViewHolder holder, int position) {

        NewsArticle currentNewsArticle = newsArticleArrayList.get(position);

        switch (layoutType) {

            case "snippet_event":
                //views for the events
                holder.eventTextView.setText(currentNewsArticle.getArticleTitle());
                //use the Glide library to load news images from the thumbnail url
                Glide.with(context).load(currentNewsArticle.getArticleThumbnainUrl()).centerCrop().into(holder.eventsImageView);

                break;

            case "snippet_tile_mini":

                //views for the mini-tiles (top stories)
                holder.topTitleTextView.setText(currentNewsArticle.getArticleTitle());
                holder.topExcerptTextView.setText(currentNewsArticle.getArticleExcerpt());
                holder.topAuthorTextView.setText(currentNewsArticle.getArticleAuthor());

                //use the Glide library to load news images from the thumbnail url
                Glide.with(context).load(currentNewsArticle.getArticleThumbnainUrl()).centerCrop().into(holder.topStoryImageView);

                break;

            case "snippet_tile":

                //views for the tile layout (trending stories)
                holder.trendingTitleTextView.setText(currentNewsArticle.getArticleTitle());
                holder.trendingAuthorTextView.setText(currentNewsArticle.getArticleAuthor());
                holder.trendingExcerptTextView.setText(currentNewsArticle.getArticleExcerpt());

                //use the Glide library to load news images from the thumbnail url
                Glide.with(context).load(currentNewsArticle.getArticleThumbnainUrl()).centerCrop().into(holder.trendingImageView);

                break;

            case "snippet_card":

                //views for the cards
                holder.cardTitleTextView.setText(currentNewsArticle.getArticleTitle());
                holder.cardAuthorTextView.setText(currentNewsArticle.getArticleAuthor());
                holder.cardExcerptTextView.setText(currentNewsArticle.getArticleExcerpt());

                //use the PicGlideasso library to load news images from the thumbnail url
                Glide.with(context).load(currentNewsArticle.getArticleThumbnainUrl()).centerCrop().into(holder.cardImageView);

                break;


        }
    }

    @Override
    public int getItemCount() {
        return newsArticleArrayList.size();
    }

    static class FeaturedStoriesViewHolder extends RecyclerView.ViewHolder {

        //views for the top/featured stories
        TextView topTitleTextView, topAuthorTextView, topExcerptTextView;
        ImageView topStoryImageView;

        //views for the flat card trending stories
        TextView trendingTitleTextView, trendingAuthorTextView, trendingExcerptTextView;
        ImageView trendingImageView;

        //views for the cards
        TextView cardTitleTextView, cardAuthorTextView, cardExcerptTextView;
        ImageView cardImageView;

        //views for the events
        TextView eventTextView;
        ImageView eventsImageView;

        public FeaturedStoriesViewHolder(View itemView) {
            super(itemView);


            //views for the miniTiles stories
            topTitleTextView = (TextView) itemView.findViewById(R.id.top_title_text_view);
            topAuthorTextView = (TextView) itemView.findViewById(R.id.top_author_text_view);
            topExcerptTextView = (TextView) itemView.findViewById(R.id.top_excerpt_text_view);
            topStoryImageView = (ImageView) itemView.findViewById(R.id.top_story_image_view);


            //views for the trending snippetTiles stories
            trendingTitleTextView = (TextView) itemView.findViewById(R.id.description_text_view);
            trendingAuthorTextView = (TextView) itemView.findViewById(R.id.trending_author_text_view);
            trendingExcerptTextView = (TextView) itemView.findViewById(R.id.flat_card_excerpt_text_view);
            trendingImageView = (ImageView) itemView.findViewById(R.id.flat_card_image_view);


            //views for the snippetCards
            cardTitleTextView = (TextView) itemView.findViewById(R.id.card_title_text_view);
            cardAuthorTextView = (TextView) itemView.findViewById(R.id.card_author_text_view);
            cardImageView = (ImageView) itemView.findViewById(R.id.card_image_view);


            //views for the events
            eventTextView = (TextView) itemView.findViewById(R.id.event_title_text_view);
            cardExcerptTextView = (TextView) itemView.findViewById(R.id.card_description_text_view);
            eventsImageView = (ImageView) itemView.findViewById(R.id.events_image_view);

        }
    }
}
