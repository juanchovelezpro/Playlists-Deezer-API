package com.example.reto2deezer.control;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.reto2deezer.R;
import com.example.reto2deezer.model.DataContainerTrack;
import com.example.reto2deezer.model.Track;
import com.example.reto2deezer.util.HTTPSWebUtilDomi;
import com.example.reto2deezer.view.TrackAdapter;
import com.example.reto2deezer.view.TracklistActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

public class TracklistController implements View.OnClickListener, HTTPSWebUtilDomi.OnResponseListener, TrackAdapter.OnItemClickListener {

    private TracklistActivity tracklistActivity;
    private HTTPSWebUtilDomi utilDomi;
    private String url;
    private DataContainerTrack data;


    public TracklistController(TracklistActivity tracklistActivity){

        this.tracklistActivity = tracklistActivity;

        utilDomi = new HTTPSWebUtilDomi();
        utilDomi.setListener(this);

        tracklistActivity.getBack().setOnClickListener(this);

        url=(String)tracklistActivity.getIntent().getExtras().get("response");

        updateTracks();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.back:

                break;

        }


    }

    public void updateTracks(){

        Gson gson = new Gson();
        data = gson.fromJson(url, DataContainerTrack.class);
        ArrayList<Track> t = new ArrayList<>();
        Collections.addAll(t,data.getData());

        tracklistActivity.runOnUiThread(()->{

            tracklistActivity.getAdapter().setTracks(t);
            tracklistActivity.getAdapter().notifyDataSetChanged();

        });


    }

    @Override
    public void onResponse(int callbackID, String response) {

    }

    @Override
    public void onItemClick(int position) {

        Toast.makeText(tracklistActivity, tracklistActivity.getAdapter().getTracks().get(position).getTitle(),Toast.LENGTH_SHORT).show();

    }
}
