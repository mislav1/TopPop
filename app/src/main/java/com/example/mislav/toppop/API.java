package com.example.mislav.toppop;

import com.example.mislav.toppop.JSONAlbumSongs.AlbumSongs;
import com.example.mislav.toppop.JSONStructure.Chart;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface API {

    String BASE_URL = "https://api.deezer.com/";

    @Headers("Content-Type: application/json")
    @GET("chart")
    Call<Chart> getChart();

    String ALL_TRACKS_URL = "https://api.deezer.com/album/";

    @Headers("Content-Type: application/json")
    @GET("{albumID}")
    Call<AlbumSongs> getTracksList(@Path("albumID") int albumID);
}
