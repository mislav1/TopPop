package com.example.mislav.toppop.JSONStructure;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Tracks {

    @SerializedName("data")
    ArrayList<Data> data;

    public ArrayList<Data> getData() {
        return data;
    }
}
