package com.example.mislav.toppop.JSONStructure;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("title")
    private  String title;

    @SerializedName("duration")
    private int duration;

    @SerializedName("position")
    private int position;

    @SerializedName("artist")
    private Artist artist;

    @SerializedName("album")
    private Album album;

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public int getPosition() {
        return position;
    }

    public Artist getArtist() {
        return artist;
    }

    public Album getAlbum() {
        return album;
    }
}
