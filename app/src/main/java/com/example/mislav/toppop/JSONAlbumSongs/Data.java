package com.example.mislav.toppop.JSONAlbumSongs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("title")
    @Expose
    String title;

    public String getTitle() {
        return title;
    }
}
