package com.example.reto2deezer.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reto2deezer.R;
import com.example.reto2deezer.control.TracklistController;

public class TracklistActivity extends AppCompatActivity {

    private TracklistController controller;
    private ImageView tracklistImage;
    private ImageButton back;
    private TextView tracklistTitle;
    private TextView description;
    private TextView numberTracks3;
    private TextView numberFans;
    private RecyclerView tracklist;
    private TrackAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracklist);

        tracklistImage = findViewById(R.id.tracklistImage);
        tracklistTitle = findViewById(R.id.tracklistTitle);
        description = findViewById(R.id.description);
        numberTracks3 = findViewById(R.id.numberTracks3);
        numberFans = findViewById(R.id.numberFans);
        back = findViewById(R.id.back);

        adapter = new TrackAdapter(this, controller);

        tracklist = findViewById(R.id.tracklist);
        tracklist.setLayoutManager(new LinearLayoutManager(this));
        tracklist.setAdapter(adapter);

        controller = new TracklistController(this);

    }

    public RecyclerView getTracklist() {
        return tracklist;
    }

    public TrackAdapter getAdapter() {
        return adapter;
    }

    public ImageButton getBack() {
        return back;
    }

    public TracklistController getController() {
        return controller;
    }

    public ImageView getTracklistImage() {
        return tracklistImage;
    }

    public TextView getTracklistTitle() {
        return tracklistTitle;
    }

    public TextView getDescription() {
        return description;
    }

    public TextView getNumberTracks3() {
        return numberTracks3;
    }

    public TextView getNumberFans() {
        return numberFans;
    }
}
