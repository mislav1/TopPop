package com.example.mislav.toppop.JSONStructure;

import com.google.gson.annotations.SerializedName;

public class Album {

    @SerializedName("cover_medium")
    private String cover;

    @SerializedName("title")
    private String title;

    @SerializedName("id")
    private int id;

    public String getCover() {
        return cover;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }
}
