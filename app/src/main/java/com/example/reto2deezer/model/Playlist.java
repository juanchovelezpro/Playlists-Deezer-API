package com.example.reto2deezer.model;

import java.io.Serializable;

public class Playlist implements Serializable {

    private long id;
    private String title;
    private String description;
    private int nb_tracks;
    private int fans;
    private String picture_small;
    private String picture_big;
    private User user;
    private String tracklist;

    public Playlist(){

    }

    public Playlist(long id, String title,String description, int nb_tracks,int fans, String picture_small, String picture_big, User user, String tracklist) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.nb_tracks = nb_tracks;
        this.fans = fans;
        this.picture_small = picture_small;
        this.picture_big = picture_big;
        this.user = user;
        this.tracklist = tracklist;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public String getDescription() {

        if(description == null || description == ""){

            description+= "No description.";

        }

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
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

    public int getNb_tracks() {
        return nb_tracks;
    }

    public void setNb_tracks(int nb_tracks) {
        this.nb_tracks = nb_tracks;
    }

    public String getPicture_small() {
        return picture_small;
    }

    public void setPicture_small(String picture_small) {
        this.picture_small = picture_small;
    }

    public String getPicture_big() {
        return picture_big;
    }

    public void setPicture_big(String picture_big) {
        this.picture_big = picture_big;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
