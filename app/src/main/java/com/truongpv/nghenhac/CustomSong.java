package com.truongpv.nghenhac;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomSong extends RecyclerView.Adapter<CustomSong.ViewHolder> {
    List<Song> mSongs;
    View.OnClickListener listener;
    private ViewHolder holder;
    private int position;

    public CustomSong(List<Song> mSongs, View.OnClickListener listener) {
        this.mSongs = mSongs;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomSong.ViewHolder holder, int position) {

        this.holder = holder;
        this.position = position;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = mSongs.get(position);
        holder.asong = song;
        holder.imageSong.setImageResource(song.getImageSong());
        holder.songSinger.setText(song.getSingle());
        holder.songTitle.setText(song.getTitle());
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageSong,imgStart;
        TextView songTitle, songSinger;
        Song asong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageSong = itemView.findViewById(R.id.imgSong);
            songTitle = itemView.findViewById(R.id.txtTitle);
            songSinger = itemView.findViewById(R.id.txtSinger);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(asong);
                }
            });
        }
    }
}
