package com.creationgroundmedia.week2.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by geo on 3/11/17.
 */

public class Article implements Parcelable {
    private String webUrl;
    private String snippet;
    private String imageUrl;
    private String headline;

    public String getWebUrl() {
        return webUrl;
    }

    public String getSnippet() {
        return snippet;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getHeadline() {
        return headline;
    }

    private Article(JSONObject jsonObject) {
        try {
            webUrl = jsonObject.getString("web_url");
            snippet = jsonObject.getString("snippet");
            headline = jsonObject.getJSONObject("headline").getString("main");
            JSONArray multimedia = jsonObject.getJSONArray("multimedia");
            if (multimedia.length() > 0) {
                imageUrl = "http://www.nytimes.com/" + ((JSONObject) multimedia.getJSONObject(0)).getString("url");
            } else {
                imageUrl = "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Article> fromJsonArray(JSONArray array) {
        ArrayList<Article> results = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try {
                results.add(new Article(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.webUrl);
        dest.writeString(this.snippet);
        dest.writeString(this.imageUrl);
        dest.writeString(this.headline);
    }

    protected Article(Parcel in) {
        this.webUrl = in.readString();
        this.snippet = in.readString();
        this.imageUrl = in.readString();
        this.headline = in.readString();
    }

    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}
