package com.example.reto2deezer.control;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.reto2deezer.R;
import com.example.reto2deezer.model.DataContainerPlaylist;
import com.example.reto2deezer.model.DataContainerTrack;
import com.example.reto2deezer.model.Playlist;
import com.example.reto2deezer.util.Constants;
import com.example.reto2deezer.util.HTTPSWebUtilDomi;
import com.example.reto2deezer.view.MainActivity;
import com.example.reto2deezer.view.TracklistActivity;
import com.google.gson.Gson;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.Collections;

public class MainController implements View.OnClickListener, HTTPSWebUtilDomi.OnResponseListener, AdapterView.OnItemClickListener, SwipyRefreshLayout.OnRefreshListener {

    private MainActivity mainActivity;
    private HTTPSWebUtilDomi utilDomi;
    private DataContainerPlaylist data;
    private DataContainerTrack dt;
    private Intent intent;
    private Playlist selected;


    public MainController(MainActivity mainActivity) {

        this.mainActivity = mainActivity;

        mainActivity.getSearchImage().setOnClickListener(this);
        utilDomi = new HTTPSWebUtilDomi();
        utilDomi.setListener(this);

        mainActivity.getListPlaylist().setOnItemClickListener(this);
        mainActivity.getMySwipy().setOnRefreshListener(this);

        intent = new Intent(mainActivity, TracklistActivity.class);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.searchImage:

                searchPlaylist();

                break;

        }

    }

    @Override
    public void onResponse(int callbackID, String response) {

        switch (callbackID) {

            case Constants.SEARCH_PLAYLIST_CALLBACK:

                Gson gson = new Gson();
                data = gson.fromJson(response, DataContainerPlaylist.class);

                if (data.getData().length > 0) {
                    ArrayList<Playlist> playlists = new ArrayList<>();
                    Collections.addAll(playlists, data.getData());


                    mainActivity.runOnUiThread(() -> {

                        mainActivity.getAdapter().setPlaylists(playlists);
                        mainActivity.getAdapter().notifyDataSetChanged();
                        mainActivity.getListPlaylist().smoothScrollToPosition(0);

                    });
                }

                break;


            case Constants.GET_PLAYLIST_INFO_CALLBACK:

                Gson gson2 = new Gson();
                selected = gson2.fromJson(response, Playlist.class);

                break;


            case Constants.GET_TRACKLIST_CALLBACK:


                Gson gson3 = new Gson();
                dt = gson3.fromJson(response, DataContainerTrack.class);

                intent.putExtra("dataTracks", dt);
                intent.putExtra("playlist", selected);
                mainActivity.startActivity(intent);

                break;

            case Constants.UPDATE_MORE_INFO_CALLBACK:

                Gson gson4 = new Gson();
                data = gson4.fromJson(response, DataContainerPlaylist.class);
                Collections.addAll(mainActivity.getAdapter().getPlaylists(), data.getData());

                mainActivity.runOnUiThread(() -> {


                    mainActivity.getAdapter().notifyDataSetChanged();


                });


                break;

        }
    }

    public void searchPlaylist() {

        String playlistTitle = mainActivity.getSearchPlaylist().getText().toString();

        if (!playlistTitle.isEmpty()) {

            new Thread(() -> {

                utilDomi.GETrequest(Constants.SEARCH_PLAYLIST_CALLBACK, "https://api.deezer.com/search/playlist?q=" + playlistTitle);

            }).start();
        } else {

            Toast.makeText(mainActivity.getApplicationContext(), "There is no text in the text field.", Toast.LENGTH_SHORT).show();

        }

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        new Thread(() -> {

            utilDomi.GETrequest(Constants.GET_PLAYLIST_INFO_CALLBACK, "https://api.deezer.com/playlist/" + mainActivity.getAdapter().getPlaylists().get(position).getId());
            utilDomi.GETrequest(Constants.GET_TRACKLIST_CALLBACK, mainActivity.getAdapter().getPlaylists().get(position).getTracklist());


        }).start();


    }


    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {

        if (data != null) {
            if (data.getNext() != null && data.getNext() != "") {
                new Thread(() -> {

                    utilDomi.GETrequest(Constants.UPDATE_MORE_INFO_CALLBACK, data.getNext());

                }).start();


            }

            if (data.getNext() == null || data.getNext() == "") {

                Toast.makeText(mainActivity.getApplicationContext(), "There is no more results.", Toast.LENGTH_SHORT).show();

            }

            new Thread(() -> {

                try {
                    Thread.sleep(1500);

                    mainActivity.getMySwipy().setRefreshing(false);
                } catch (Exception ex) {


                }
            }).start();

        }else{


            mainActivity.getMySwipy().setRefreshing(false);
        }
    }
}
