package com.example.reto2deezer.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.reto2deezer.R;
import com.example.reto2deezer.model.Playlist;

import java.util.ArrayList;

public class PlaylistAdapter extends BaseAdapter {

    private MainActivity activity;
    private ArrayList<Playlist> playlists;

    public PlaylistAdapter(MainActivity activity){

        this.activity = activity;

        playlists = new ArrayList<>();
    }


    @Override
    public int getCount() {
        return playlists.size();
    }

    @Override
    public Object getItem(int position) {
        return playlists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.playlist_row, null, false);

        ImageView playlistImage = row.findViewById(R.id.playlistImage);
        TextView titlePlaylist = row.findViewById(R.id.titlePlaylist);
        TextView userOwner = row.findViewById(R.id.userOwner);
        TextView numberTrack = row.findViewById(R.id.numberTracks1);

        Glide.with(activity).load(playlists.get(position).getPicture_big()).centerCrop().into(playlistImage);

        titlePlaylist.setText(playlists.get(position).getTitle());
        userOwner.setText(playlists.get(position).getUser().getName());
        numberTrack.setText(playlists.get(position).getNb_tracks()+"");

        return row;
    }

    public void addPlaylist(Playlist playlist){

        playlists.add(playlist);
        notifyDataSetChanged();

    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }
}
