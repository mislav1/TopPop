package com.example.mislav.toppop.JSONAlbumSongs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumSongs {

    @SerializedName("tracks")
    @Expose
    Tracks tracks;

    public Tracks getTracks() {
        return tracks;
    }
}
