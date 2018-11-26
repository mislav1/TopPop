package com.example.mislav.toppop;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private ArrayList<SongItem> lsitOfSongs;
    Context context;

    public SongAdapter (ArrayList<SongItem> listOfSongs, Context context){
        this.lsitOfSongs = listOfSongs;
        this.context = context;
    }

    @NonNull
    @Override
    public SongAdapter.SongViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.song_list_item, viewGroup, false);
        return  new SongViewHolder(view, context, lsitOfSongs);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder songViewHolder, int i) {
        songViewHolder.bind(this.lsitOfSongs.get(i));
    }

    @Override
    public int getItemCount() {
        return this.lsitOfSongs.size();
    }

    class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView positionOfSong;
        TextView songArtist;
        TextView songName;
        TextView songDuration;
        Context context;
        ArrayList<SongItem> listOfSongs;


        public SongViewHolder(@NonNull View itemView, Context context, ArrayList<SongItem> listOFSongs) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.context = context;
            this.listOfSongs = listOFSongs;
            positionOfSong = itemView.findViewById(R.id.position);
            songArtist = itemView.findViewById(R.id.artist);
            songName = itemView.findViewById(R.id.song);
            songDuration = itemView.findViewById(R.id.duration);
        }

        public void bind (SongItem song){
            positionOfSong.setText(String.valueOf(song.getPosition()));
            songArtist.setText(song.getArtist());
            songName.setText(song.getName());
            songDuration.setText(secondsToString(song.getDuration()));
        }

        private String secondsToString(int pTime) {
            return String.format("%02d:%02d", pTime / 60, pTime % 60);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            SongItem currentSong = this.listOfSongs.get(position);
            Intent intent = new Intent(this.context, SongDetailActivity.class);
            intent.putExtra("image_url", currentSong.getUrl());
            intent.putExtra("song_title", currentSong.getName());
            intent.putExtra("artist_name", currentSong.getArtist());
            intent.putExtra("album_name", currentSong.getAlbum());
            intent.putExtra("albumID", currentSong.getAlbumId());
            this.context.startActivity(intent);
        }
    }
}
