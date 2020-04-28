package com.example.reto2deezer.control;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.reto2deezer.R;
import com.example.reto2deezer.model.Track;
import com.example.reto2deezer.view.TrackActivity;

public class TrackController implements View.OnClickListener {

    private TrackActivity activity;

    private Track track;

    public TrackController(TrackActivity activity) {

        this.activity = activity;


        activity.getListenTrack().setOnClickListener(this);
        activity.getArrowBack().setOnClickListener(this);

        track = (Track) activity.getIntent().getExtras().get("track");


        updateTrackInfo();

    }


    public void updateTrackInfo() {

        activity.runOnUiThread(() -> {

            activity.getTitleTrack().setText(track.getTitle_short());
            activity.getArtistName().setText(track.getArtist().getName());
            activity.getAlbumTitle().setText(track.getAlbum().getTitle());
            activity.getDurationText().setText(track.getDuration());

            Glide.with(activity).load(track.getAlbum().getCover_big()).into(activity.getImageTrack());


        });


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.listenTrack:

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(track.getLink()));
                activity.startActivity(i);

                break;

            case R.id.arrowBack:

                activity.onBackPressed();

                break;

        }

    }


}
