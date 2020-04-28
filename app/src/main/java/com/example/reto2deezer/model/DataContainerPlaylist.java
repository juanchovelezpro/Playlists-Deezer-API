package com.example.reto2deezer.model;

import java.io.Serializable;

public class DataContainerPlaylist implements Serializable {

    private Playlist[] data;
    private String next;

    public DataContainerPlaylist(){


    }

    public DataContainerPlaylist(Playlist[] data, String next) {
        this.data = data;
        this.next = next;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Playlist[] getData() {
        return data;
    }

    public void setData(Playlist[] data) {
        this.data = data;
    }
}
