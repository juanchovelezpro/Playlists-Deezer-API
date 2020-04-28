package com.example.reto2deezer.control;

import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.reto2deezer.R;
import com.example.reto2deezer.model.DataContainerTrack;
import com.example.reto2deezer.model.Playlist;
import com.example.reto2deezer.model.Track;
import com.example.reto2deezer.util.Constants;
import com.example.reto2deezer.util.HTTPSWebUtilDomi;
import com.example.reto2deezer.view.TrackActivity;
import com.example.reto2deezer.view.TracklistActivity;
import com.google.gson.Gson;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

public class TracklistController implements View.OnClickListener, HTTPSWebUtilDomi.OnResponseListener, SwipyRefreshLayout.OnRefreshListener {

    private TracklistActivity tracklistActivity;
    private HTTPSWebUtilDomi utilDomi;
    private DataContainerTrack data;
    private Playlist playlist;

    public TracklistController(TracklistActivity tracklistActivity) {

        this.tracklistActivity = tracklistActivity;

        utilDomi = new HTTPSWebUtilDomi();
        utilDomi.setListener(this);

        tracklistActivity.getBack().setOnClickListener(this);
        tracklistActivity.getMyRefresh().setOnRefreshListener(this);
        tracklistActivity.getDescription().setMovementMethod(new ScrollingMovementMethod());

        data = (DataContainerTrack) tracklistActivity.getIntent().getExtras().get("dataTracks");
        playlist = (Playlist) tracklistActivity.getIntent().getExtras().get("playlist");

        updatePlaylist();
        updateTracks();

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.back:

                tracklistActivity.onBackPressed();

                break;


            default:

                Intent i = new Intent(tracklistActivity, TrackActivity.class);
                i.putExtra("track", tracklistActivity.getAdapter().getTracks().get(tracklistActivity.getTracklist().getChildAdapterPosition(v)));
                tracklistActivity.startActivity(i);

                break;


        }


    }

    public void updatePlaylist() {

        tracklistActivity.getTracklistTitle().setText(playlist.getTitle());
        Glide.with(tracklistActivity).load(playlist.getPicture_big()).centerCrop().into(tracklistActivity.getTracklistImage());
        tracklistActivity.getNumberFans().setText(playlist.getFans() + "");
        tracklistActivity.getNumberTracks3().setText(playlist.getNb_tracks() + "");
        tracklistActivity.getDescription().setText(playlist.getDescription());


    }


    public void updateTracks() {

        if (data.getData() != null) {
            for (int i = 0; i < data.getData().length; i++) {

                int index = i;
                new Thread(() -> {
                    utilDomi.GETrequest(Constants.UPDATE_TRACK_CALLBACK, "https://api.deezer.com/track/" + data.getData()[index].getId());
                }).start();

            }
        }

    }


    @Override
    public void onResponse(int callbackID, String response) {


        switch (callbackID) {

            case Constants.UPDATE_TRACK_CALLBACK:

                Gson gson = new Gson();
                Track track = gson.fromJson(response, Track.class);


                tracklistActivity.runOnUiThread(() -> {

                    tracklistActivity.getAdapter().getTracks().add(track);
                    tracklistActivity.getAdapter().notifyDataSetChanged();

                });


                break;

            case Constants.UPDATE_MORE_INFO_CALLBACK:

                Gson gson2 = new Gson();
                data = gson2.fromJson(response, DataContainerTrack.class);
                updateTracks();


                break;

        }

    }


    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {


        if (data.getNext() != null && data.getNext() != "") {

            new Thread(() -> {

                utilDomi.GETrequest(Constants.UPDATE_MORE_INFO_CALLBACK, data.getNext());

            }).start();

        }

        if (data.getNext() == null || data.getNext() == "") {

            Toast.makeText(tracklistActivity.getApplicationContext(), "There is no more tracks in this playlist.", Toast.LENGTH_SHORT).show();

        }


        new Thread(() -> {

            try {
                Thread.sleep(1000);
                tracklistActivity.getMyRefresh().setRefreshing(false);
            } catch (Exception ex) {

            }
        }).start();
    }

}