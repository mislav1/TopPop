package com.example.mislav.toppop;

public class SongItem {
    private String name;
    private String artist;
    private int duration;
    private int position;
    private String url;
    private String album;
    private int albumId;

    public SongItem(String name, String artist, int duration, int position, String url, String album,
                    int albumId){
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        this.position = position;
        this.url = url;
        this.album = album;
        this.albumId = albumId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }

    public int getPosition() {
        return position;
    }

    public String getUrl() {
        return url;
    }

    public String getAlbum() {
        return album;
    }
}
