package com.example.ericlloyd.spicefm.Utils;

import java.io.Serializable;

/**
 * Created by Eric Lloyd on 31-Oct-17.
 */

public class NewsArticle implements Serializable {

    private String articleTitle;
    private String articleCategory;
    private String articleText;
    private String articleAuthor;
    private String articleExcerpt;
    private String articleThumbnainUrl;


    public NewsArticle(String articleTitle, String articleAuthor, String articleExcerpt,
                       String articleCategory, String articleText, String articleThumbnainUrl){

        this.articleTitle = articleTitle;
        this.articleAuthor = articleAuthor;
        this.articleCategory = articleCategory;
        this.articleText = articleText;
        this.articleExcerpt = articleExcerpt;
        this.articleThumbnainUrl = articleThumbnainUrl;

    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getArticleCategory() {
        return articleCategory;
    }

    public String getArticleText() {
        return articleText;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public String getArticleExcerpt() {
        return articleExcerpt;
    }

    public String getArticleThumbnainUrl() {
        return articleThumbnainUrl;
    }
}
