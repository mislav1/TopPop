package com.example.mislav.toppop;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.mislav.toppop.JSONStructure.Album;
import com.example.mislav.toppop.JSONStructure.Artist;
import com.example.mislav.toppop.JSONStructure.Chart;
import com.example.mislav.toppop.JSONStructure.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TopPop extends AppCompatActivity {

    private  SongAdapter songAdapter;
    private  RecyclerView recyclerView;
    private ArrayList<SongItem> listOfSongs;
    private  SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_pop);

        swipeRefreshLayout = findViewById(R.id.swipeContainer);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listOfSongs.clear();
                fetchDataWithRetrofit();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        listOfSongs = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerViewSongs);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        songAdapter = new SongAdapter(listOfSongs, this);
        recyclerView.setAdapter(songAdapter);

        fetchDataWithRetrofit();

    }

    public void fetchDataWithRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<Chart> call = api.getChart();

        call.enqueue(new Callback<Chart>() {
            @Override
            public void onResponse(Call<Chart> call, Response<Chart> response) {

                Chart chart = response.body();
                ArrayList<Data> data = chart.getTracks().getData();
                for (Data songInfo : data) {
                    String title = songInfo.getTitle();

                    int duration = songInfo.getDuration();

                    int position = songInfo.getPosition();

                    Album album = songInfo.getAlbum();
                    String url = album.getCover();
                    String albumName = album.getTitle();
                    int albumId = album.getId();

                    Artist artist = songInfo.getArtist();
                    String name = artist.getName();

                    SongItem song = new SongItem(title, name, duration, position, url, albumName, albumId);
                    listOfSongs.add(song);
                    songAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Chart> call, Throwable t) {
                Log.i("TopPop activity", "onFailure: problem with json");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.top_pop_menu, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.normal:
                getNormal();
                songAdapter.notifyDataSetChanged();
                return true;
            case R.id.ascending:
                getAscending();
                songAdapter.notifyDataSetChanged();
                return true;
            case R.id.descending:
                getDescending();
                songAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getAscending (){

        Collections.sort(listOfSongs, new Comparator<SongItem>() {
            @Override
            public int compare(SongItem o1, SongItem o2) {
                return o1.getDuration() - o2.getDuration();
            }
        });
    }

    private void getDescending (){

        Collections.sort(listOfSongs, new Comparator<SongItem>() {
            @Override
            public int compare(SongItem o1, SongItem o2) {
                return o2.getDuration() - o1.getDuration();
            }
        });
    }

    private void getNormal(){

        Collections.sort(listOfSongs, new Comparator<SongItem>() {
            @Override
            public int compare(SongItem o1, SongItem o2) {
                return o1.getPosition() - o2.getPosition();
            }
        });
    }
}
