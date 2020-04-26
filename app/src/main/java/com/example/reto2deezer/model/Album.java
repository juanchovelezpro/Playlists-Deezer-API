package com.example.reto2deezer.model;

public class Album {

    private String name;
    private String cover_big;
    private String cover_medium;

    public Album(){


    }

    public Album(String name, String cover_big, String cover_medium) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
