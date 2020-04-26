package com.example.reto2deezer.model;

public class DataContainerPlaylist {

    private Playlist[] data;

    public DataContainerPlaylist(){


    }

    public DataContainerPlaylist(Playlist[] data) {
        this.data = data;
    }

    public Playlist[] getData() {
        return data;
    }

    public void setData(Playlist[] data) {
        this.data = data;
    }
}
