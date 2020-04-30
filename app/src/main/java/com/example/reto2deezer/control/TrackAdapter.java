package com.example.reto2deezer.control;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.reto2deezer.R;
import com.example.reto2deezer.model.Track;
import com.example.reto2deezer.view.TracklistActivity;

import java.util.ArrayList;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolderTrack> implements View.OnClickListener {

    private TracklistActivity activity;
    private ArrayList<Track> tracks;
    private View.OnClickListener listener;


    public TrackAdapter(TracklistActivity activity) {

        this.activity = activity;

        tracks = new ArrayList<>();

    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    @NonNull
    @Override
    public ViewHolderTrack onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_row, null, false);

        view.setOnClickListener(this);

        return new ViewHolderTrack(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTrack holder, int position) {

        holder.trackTitle.setText(tracks.get(position).getTitle_short());
        holder.artist.setText(tracks.get(position).getArtist().getName());
        holder.released.setText(tracks.get(position).getRelease_date());
        holder.putImage(activity, tracks, position);

    }

    public void setOnClickListener(View.OnClickListener listener) {

        this.listener = listener;

    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public class ViewHolderTrack extends RecyclerView.ViewHolder {

        ImageView trackImage;
        TextView trackTitle;
        TextView artist;
        TextView released;


        public ViewHolderTrack(@NonNull View itemView) {
            super(itemView);

            trackImage = itemView.findViewById(R.id.trackImage);
            trackTitle = itemView.findViewById(R.id.trackTitle);
            artist = itemView.findViewById(R.id.artist);
            released = itemView.findViewById(R.id.releasedYear);

        }

        public void putImage(Activity a, ArrayList<Track> tracks, int pos) {

            Glide.with(a).load(tracks.get(pos).getAlbum().getCover_big()).centerCrop().into(trackImage);

        }

    }

}
