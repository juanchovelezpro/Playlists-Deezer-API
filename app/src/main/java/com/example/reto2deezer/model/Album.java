package com.example.reto2deezer.model;

import java.io.Serializable;

public class Album implements Serializable {

    private String title;
    private String cover_big;
    private String cover_medium;

    public Album(){


    }

    public Album(String title, String cover_big, String cover_medium) {
        this.title = title;
        this.cover_big = cover_big;
        this.cover_medium = cover_medium;
    }

    public String getCover_medium() {
        return cover_medium;
    }

    public void setCover_medium(String cover_medium) {
        this.cover_medium = cover_medium;
    }

    public String getCover_big() {
        return cover_big;
    }

    public void setCover_big(String cover_big) {
        this.cover_big = cover_big;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
