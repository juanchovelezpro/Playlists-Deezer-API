package com.example.reto2deezer.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reto2deezer.R;
import com.example.reto2deezer.control.TrackController;

public class TrackActivity extends AppCompatActivity {

    private ImageView imageTrack;
    private TextView titleTrack;
    private TextView artistName;
    private TextView albumTitle;
    private TextView durationText;
    private Button listenTrack;
    private ImageButton arrowBack;

    private TrackController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);


        imageTrack = findViewById(R.id.imageTrack);
        titleTrack = findViewById(R.id.titleTrack);
        artistName = findViewById(R.id.artistName);
        albumTitle = findViewById(R.id.albumTitle);
        durationText = findViewById(R.id.durationText);
        listenTrack = findViewById(R.id.listenTrack);
        arrowBack = findViewById(R.id.arrowBack);


        controller = new TrackController(this);


    }

    public ImageButton getArrowBack() {
        return arrowBack;
    }

    public void setArrowBack(ImageButton arrowBack) {
        this.arrowBack = arrowBack;
    }

    public ImageView getImageTrack() {
        return imageTrack;
    }

    public void setImageTrack(ImageView imageTrack) {
        this.imageTrack = imageTrack;
    }

    public TextView getTitleTrack() {
        return titleTrack;
    }

    public void setTitleTrack(TextView titleTrack) {
        this.titleTrack = titleTrack;
    }

    public TextView getArtistName() {
        return artistName;
    }

    public void setArtistName(TextView artistName) {
        this.artistName = artistName;
    }

    public TextView getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(TextView albumTitle) {
        this.albumTitle = albumTitle;
    }

    public TextView getDurationText() {
        return durationText;
    }

    public void setDurationText(TextView durationText) {
        this.durationText = durationText;
    }

    public Button getListenTrack() {
        return listenTrack;
    }

    public void setListenTrack(Button listenTrack) {
        this.listenTrack = listenTrack;
    }
}
