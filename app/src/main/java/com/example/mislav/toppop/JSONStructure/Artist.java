package com.example.mislav.toppop.JSONStructure;

import com.google.gson.annotations.SerializedName;

public class Artist {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
}
