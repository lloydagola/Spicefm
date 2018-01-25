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

/**
 * Created by Eric Lloyd on 20-Nov-17.
 */

public class VideosRecylcerAdapter extends RecyclerView.Adapter<VideosRecylcerAdapter.VideosVH> {
    private Context context;
    private ArrayList<Video> videos;

    public VideosRecylcerAdapter(Context context, ArrayList<Video> videos) {
        this.context = context;
        this.videos = videos;
    }

    @Override
    public VideosVH onCreateViewHolder(ViewGroup parent, int viewType) {

        return new VideosVH(LayoutInflater.from(context).inflate(R.layout.snippet_tile_videos, parent, false));
    }

    @Override
    public void onBindViewHolder(VideosVH holder, int position) {

        Video currentVideo = videos.get(position);

        //use the Glide library to load news images from the thumbnail url
        Glide.with(context).load(currentVideo.getThumbnailUrl()).centerCrop().into(holder.imageView);
        holder.titleTexttView.setText(currentVideo.getTitle());
        holder.descriptionTexttView.setText(currentVideo.getDescription());
        holder.authorTextView.setText(currentVideo.getChannelTitle());
        holder.viewsTextView.setText(currentVideo.getViewCount());

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }


    public class VideosVH extends RecyclerView.ViewHolder {


        private final ImageView imageView;
        private final TextView titleTexttView;
        private final TextView authorTextView;
        private final TextView viewsTextView;
        private final TextView descriptionTexttView;

        public VideosVH(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.flat_card_image_view);
            titleTexttView = (TextView) itemView.findViewById(R.id.trending_title_text_view);
            descriptionTexttView = (TextView) itemView.findViewById(R.id.description_text_view);
            authorTextView = (TextView) itemView.findViewById(R.id.trending_author_text_view);
            viewsTextView = (TextView) itemView.findViewById(R.id.views_text_view);

        }
    }
}
