package com.example.reto2deezer.model;

import java.io.Serializable;

public class DataContainerTrack implements Serializable{

    private Track[] data;
    private String next;

    public DataContainerTrack() {
    }

    public DataContainerTrack(Track[] data, String next) {
        this.data = data;
        this.next = next;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Track[] getData() {
        return data;
    }

    public void setData(Track[] data) {
        this.data = data;
    }
}
