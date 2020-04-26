package com.example.reto2deezer.control;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.reto2deezer.R;
import com.example.reto2deezer.model.DataContainerPlaylist;
import com.example.reto2deezer.model.DataContainerTrack;
import com.example.reto2deezer.model.Playlist;
import com.example.reto2deezer.model.Track;
import com.example.reto2deezer.util.Constants;
import com.example.reto2deezer.util.HTTPSWebUtilDomi;
import com.example.reto2deezer.view.MainActivity;
import com.example.reto2deezer.view.TracklistActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

public class MainController implements View.OnClickListener, HTTPSWebUtilDomi.OnResponseListener, AdapterView.OnItemClickListener {

    private MainActivity mainActivity;
    private HTTPSWebUtilDomi utilDomi;
    private DataContainerPlaylist data;



    public  MainController(MainActivity mainActivity){

        this.mainActivity = mainActivity;

        mainActivity.getSearchImage().setOnClickListener(this);
        utilDomi = new HTTPSWebUtilDomi();
        utilDomi.setListener(this);

        mainActivity.getListPlaylist().setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {

       switch (v.getId()){

           case R.id.searchImage:

            searchPlaylist();

               break;

       }

    }

    @Override
    public void onResponse(int callbackID, String response) {

        switch(callbackID){

            case Constants.SEARCH_PLAYLIST_CALLBACK:

                Gson gson = new Gson();
                data = gson.fromJson(response, DataContainerPlaylist.class);
                ArrayList<Playlist> playlists = new ArrayList<>();
                Collections.addAll(playlists, data.getData());

                mainActivity.runOnUiThread(() ->{

                    mainActivity.getAdapter().setPlaylists(playlists);
                    mainActivity.getAdapter().notifyDataSetChanged();


                });



                break;


            case Constants.GET_TRACKLIST_CALLBACK:

                Intent i = new Intent(mainActivity, TracklistActivity.class);
                i.putExtra("response",response);
                mainActivity.startActivity(i);

                break;


        }
   }

   public void searchPlaylist(){

        String playlistTitle = mainActivity.getSearchPlaylist().getText().toString();

        new Thread(()->{

            utilDomi.GETrequest(Constants.SEARCH_PLAYLIST_CALLBACK, "https://api.deezer.com/search/playlist?q="+playlistTitle);

        }).start();



   }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            new Thread(()->{

                utilDomi.GETrequest(Constants.GET_TRACKLIST_CALLBACK, mainActivity.getAdapter().getPlaylists().get(position).getTracklist());

            }).start();

    }
}
