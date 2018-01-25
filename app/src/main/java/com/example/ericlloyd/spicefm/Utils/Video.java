package com.example.ericlloyd.spicefm.Utils;

import java.io.Serializable;

/**
 * Created by Eric Lloyd on 16-Nov-17.
 */

public class Video implements Serializable{

    private String title;
    private String channelTitle;
    private String description;
    private String publishedAt;
    private String thumbnailUrl;
    private String viewCount;
    private String likeCount;
    private String dislikeCount;
    private String commentCount;

    public Video(String title, String channelTitle, String description,
                 String publishedAt, String thumbnailUrl, String viewCount,
                 String likeCount, String dislikeCount, String commentCount) {

        this.title = title;
        this.channelTitle = channelTitle;
        this.description = description;
        this.publishedAt = publishedAt;
        this.thumbnailUrl = thumbnailUrl;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.commentCount = commentCount;



    }

    public String getTitle() {
        return title;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public String getDescription() {
        return description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getViewCount() {
        return viewCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public String getDislikeCount() {
        return dislikeCount;
    }

    public String getCommentCount() {
        return commentCount;
    }
}
