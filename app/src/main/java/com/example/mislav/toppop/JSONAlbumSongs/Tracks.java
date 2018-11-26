package com.example.mislav.toppop.JSONAlbumSongs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Tracks {

    @SerializedName("data")
    @Expose
    ArrayList<Data> data;

    public ArrayList<Data> getData() {
        return data;
    }
}
