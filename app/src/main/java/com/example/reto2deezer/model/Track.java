package com.example.reto2deezer.model;

import java.io.Serializable;

public class Track implements Serializable {


    private long id;
    private String title;
    private String title_short;
    private int duration;
    private String link;
    private Album album;
    private Artist artist;
    private String release_date;


    public Track() {
    }

    public Track(long id, String title, String title_short, int duration, String link, Album album, Artist artist, String release_date) {
        this.id = id;
        this.title = title;
        this.title_short = title_short;
        this.duration = duration;
        this.link = link;
        this.album = album;
        this.artist = artist;
        this.release_date = release_date;
    }

    public String getTitle_short() {
        return title_short;
    }

    public void setTitle_short(String title_short) {
        this.title_short = title_short;
    }

    public String getRelease_date() {

        String r = "";
        if (release_date != null && release_date != "") {
            String[] date = release_date.split("-");
            r = date[0];
        } else {

            r = "Not found";

        }
        return r;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {

        String dur = "";

        double aux = duration / 60.0;
        int min = (int) aux;
        int seconds = (int) ((aux - min) * 60.0);

        if (seconds / 10 < 1) {

            dur += min + ":0" + seconds;

        } else {

            dur += min + ":" + seconds;

        }

        return dur;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
