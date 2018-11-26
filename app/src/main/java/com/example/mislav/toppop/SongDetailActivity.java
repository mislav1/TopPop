package com.example.mislav.toppop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mislav.toppop.JSONAlbumSongs.AlbumSongs;
import com.example.mislav.toppop.JSONAlbumSongs.Data;
import com.example.mislav.toppop.JSONAlbumSongs.Tracks;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SongDetailActivity extends AppCompatActivity {

    TextView txNameOfSong;
    TextView txNameOfArtist;
    TextView txNameOfAlbum;
    ImageView imCoverUrl;
    TextView txAllAlbumSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);
        txNameOfSong = findViewById(R.id.songTitle);
        txNameOfArtist = findViewById(R.id.songArtist);
        txNameOfAlbum = findViewById(R.id.albumTitle);
        imCoverUrl = findViewById(R.id.coverImage);
        txAllAlbumSongs = findViewById(R.id.allSongs);

        String songTitle = getIntent().getStringExtra("song_title");
        String artistName = getIntent().getStringExtra("artist_name");
        String albumName = getIntent().getStringExtra("album_name");
        int albumId = getIntent().getIntExtra("albumID", 0);



        txNameOfSong.setText(songTitle);
        txNameOfArtist.setText(artistName);
        txNameOfAlbum.setText(albumName);

        Retrofit retrofitTracks = new Retrofit.Builder()
                .baseUrl(API.ALL_TRACKS_URL).addConverterFactory(GsonConverterFactory.create())
                .build();

        API apiTracks = retrofitTracks.create(API.class);

        Call<AlbumSongs> callTracks = apiTracks.getTracksList(albumId);

        callTracks.enqueue(new Callback<AlbumSongs>() {
            @Override
            public void onResponse(Call<AlbumSongs> call, Response<AlbumSongs> response) {

                Tracks tracks = response.body().getTracks();

                ArrayList<Data> albumSongsData = tracks.getData();

                StringBuilder allAlbumSongs = new StringBuilder("");
                for (Data data : albumSongsData){
                    allAlbumSongs.append(data.getTitle());
                }
                txAllAlbumSongs.setText(allAlbumSongs.toString());
            }

            @Override
            public void onFailure(Call<AlbumSongs> call, Throwable t) {
                Log.i("SongDetailActivity", "onFailure: Failed Getting json");
            }
        });

        String url = getIntent().getStringExtra("image_url");
        Picasso.with(this).load(url)
                .error(R.mipmap.ic_launcher)
                .into(imCoverUrl,new com.squareup.picasso.Callback(){
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
