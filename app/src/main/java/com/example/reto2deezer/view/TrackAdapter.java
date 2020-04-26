package com.example.reto2deezer.view;

import android.app.Activity;
import android.media.Image;
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

import java.util.ArrayList;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolderTrack> {

    private TracklistActivity activity;
    private ArrayList<Track> tracks;
    private OnItemClickListener myItemClickListener;

    public TrackAdapter(TracklistActivity activity, OnItemClickListener myItemClickListener){

        this.activity = activity;
        this.myItemClickListener = myItemClickListener;

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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_row,null, false);

        return new ViewHolderTrack(view,myItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTrack holder, int position) {

        holder.trackTitle.setText(tracks.get(position).getTitle());
        holder.artist.setText(tracks.get(position).getArtist().getName());
        holder.released.setText(tracks.get(position).getRelease_date());
        holder.putImage(activity,tracks,position);



    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    public class ViewHolderTrack extends RecyclerView.ViewHolder implements View.OnClickListener {

       ImageView trackImage;
       TextView trackTitle;
       TextView artist;
       TextView released;
       OnItemClickListener onItemClickListener;

        public ViewHolderTrack(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;


            trackImage = itemView.findViewById(R.id.trackImage);
            trackTitle = itemView.findViewById(R.id.trackTitle);
            artist = itemView.findViewById(R.id.artist);
            released = itemView.findViewById(R.id.releasedYear);
            itemView.setOnClickListener(this);

        }

        public void putImage(Activity a, ArrayList<Track> tracks, int pos){

        Glide.with(a).load(tracks.get(pos).getAlbum().getCover_big()).centerCrop().into(trackImage);

        }


        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }
    public interface OnItemClickListener{

        void onItemClick(int position);

    }
}
