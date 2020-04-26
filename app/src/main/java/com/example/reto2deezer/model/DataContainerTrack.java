package com.example.reto2deezer.model;

import java.io.Serializable;

public class DataContainerTrack{

    private Track[] data;

    public DataContainerTrack() {
    }

    public DataContainerTrack(Track[] data) {
        this.data = data;
    }

    public Track[] getData() {
        return data;
    }

    public void setData(Track[] data) {
        this.data = data;
    }
}
