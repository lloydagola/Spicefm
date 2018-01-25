package com.example.ericlloyd.spicefm.Utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Eric Lloyd on 03-Nov-17.
 */

public final class QueryUtils {

    private static final String LOG_TAG = "QueryUtils";
    private final String URL = "";

    private static final String CLASS_TAG = "QueryUtils";


    private QueryUtils() {

    }

    /*
    a method to create a url object for use in making http request
    @params url: a string url from which we will create a url object
     */
    private static URL createUrlObject(String url) {

        URL url1Object = null;

        try {

            url1Object = new URL(url);
            Log.v(LOG_TAG, "url object created...");

        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error creating url object from the provided urlString...");
        }

        return url1Object;

    }

    private static String makeHttpsUrlConnection(URL url) throws IOException {

        String jsonResponse = "";

        if (url == null) {

            Log.v(LOG_TAG, "The url object in makeHttpsUrlConnection was null...");

            return jsonResponse;
        }

        HttpsURLConnection httpsURLConnection = null;
        InputStream inputStream = null;

        try {

            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setConnectTimeout(10000);
            httpsURLConnection.setReadTimeout(15000);
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.connect();

            //check if the connection was successful
            if (httpsURLConnection.getResponseCode() == 200) {
                Log.v(LOG_TAG, "HttpsUrlConnection was successful...");

                inputStream = httpsURLConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);

            } else {
                Log.i(LOG_TAG, "Error making the url connection. Error code: " + httpsURLConnection.getResponseCode());
            }

        } catch (IOException e) {
            Log.v(LOG_TAG, "Could not make a httpsUrlConnection...");
        } finally {

            if (httpsURLConnection != null) {
                httpsURLConnection.disconnect();
            }

            if (inputStream != null) {
                inputStream.close();
            }

        }


        return jsonResponse;
    }

    /*
    a method to read data from the stream and return it in string format
    @params inputStream: reads from the input stream
     */
    private static String readFromStream(InputStream inputStream) throws IOException{

        StringBuilder outputString = new StringBuilder();

        if (inputStream != null) {

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();

            while (line != null) {
                outputString.append(line);
                line = bufferedReader.readLine();
                Log.v(LOG_TAG, "reading from stream...");

            }
            Log.v(LOG_TAG, "read from stream...");

            inputStreamReader.close();
            bufferedReader.close();


        }




        return outputString.toString();
    }

    private static String getApiData(String urlString){

        URL urlObject = createUrlObject(urlString);
        String jsonString = null;

        try {
            jsonString = makeHttpsUrlConnection(urlObject);
            Log.v(LOG_TAG, "Received json response from api...");

        } catch (Exception e) {
            Log.e("QueryUtils", "Error getting json response form the url object...", e);
        }

        return jsonString;

    }

    public static ArrayList<NewsArticle> extractNewsFromJson(String JSON_RESPONSE) {
        //list of news articles
        ArrayList<NewsArticle> newsArticleArrayList = new ArrayList<>();

        try {

            JSONObject rootJsonObject = new JSONObject(JSON_RESPONSE);
            JSONArray articlesArray = rootJsonObject.getJSONArray("articles");

            //loop through the json articles and add them to out arraylist
            for (int i = 0; i < articlesArray.length(); i++) {
                JSONObject newsObject = articlesArray.getJSONObject(i);
                String articleTitle = newsObject.getString("title");
                String articleAuthor = newsObject.getString("author");
                String articleDescription = newsObject.getString("description");
                String articleThumbnailUrl = newsObject.getString("urlToImage");

                //create an arraylist from the json response
                newsArticleArrayList.add(
                        new NewsArticle(
                                articleTitle,
                                articleAuthor,
                                articleDescription,
                                "test",
                                articleTitle + articleAuthor + articleDescription,
                                articleThumbnailUrl));


                Log.v(LOG_TAG, "extracted json...");

            }


        } catch (JSONException e) {

            Log.e(CLASS_TAG, "Error creating rootJson...", e);

        }


        return newsArticleArrayList;
    }

    public static ArrayList<Video> extractYoutubeVideosFromJson(String JSON_RESPONSE) {
        //list of news articles
        ArrayList<Video> videos = new ArrayList<>();

        try {

            JSONObject rootJsonObject = new JSONObject(JSON_RESPONSE);
            JSONArray videosArray = rootJsonObject.getJSONArray("items");

            //loop through the json articles and add them to out arraylist
            for (int i = 0; i < videosArray.length(); i++) {
                JSONObject videoObject = videosArray.getJSONObject(i);

                JSONObject snippetObject = videoObject.getJSONObject("snippet");

                String title = snippetObject.optString("title");
                String channelTitle = snippetObject.getString("channelTitle");
                String description = snippetObject.getString("description");
                String publishedAt = snippetObject.getString("publishedAt");

                JSONObject thumbnails = snippetObject.getJSONObject("thumbnails");
                JSONObject standard = thumbnails.getJSONObject("standard");
                String thumbnailUrl = standard.getString("url");

                JSONObject statistics = videoObject.getJSONObject("statistics");
                String viewCount = statistics.getString("viewCount");
                String likeCount = statistics.getString("likeCount");
                String dislikeCount = statistics.getString("dislikeCount");
                String commentCount = statistics.getString("commentCount");

                //create an arraylist from the json response
                videos.add(
                        new Video(
                                title,
                                channelTitle,
                                description,
                                publishedAt,
                                thumbnailUrl,
                                viewCount,
                                likeCount,
                                dislikeCount,
                                commentCount));


                Log.v(LOG_TAG, "extracted json...");

            }


        } catch (JSONException e) {

            Log.e(CLASS_TAG, "Error creating rootJson...", e);

        }


        return videos;
    }

    public static ArrayList<NewsArticle> fetchNewsArticles(String urlString) {

        String json = getApiData(urlString);

        //extractNewsFromJson(json);
        ArrayList<NewsArticle> newsArticlesArrayList = extractNewsFromJson(json);


        return newsArticlesArrayList;

    }

    public static ArrayList<Video> fetchYoutubeVideos(String urlString) {

        String json = getApiData(urlString);

        //extractNewsFromJson(json);
        ArrayList<Video> videos = extractYoutubeVideosFromJson(json);


        return videos;

    }


}
