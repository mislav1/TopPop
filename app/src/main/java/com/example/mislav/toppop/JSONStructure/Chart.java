package com.example.mislav.toppop.JSONStructure;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chart {

    @SerializedName("tracks")
    @Expose
    Tracks tracks;

    public Tracks getTracks() {
        return tracks;
    }
}
