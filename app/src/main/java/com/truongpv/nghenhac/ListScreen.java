package com.truongpv.nghenhac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ListScreen extends AppCompatActivity implements OnClickListener{
    RecyclerView rcv;
    CustomSong adt;
    List<Song> mSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_screen);

        rcv = findViewById(R.id.rcv);
        mSongs = new ArrayList<Song>();

        mSongs.add(new Song("Love yourseft","Justin Biber",R.drawable.home_img1,R.drawable.phiasau));
        mSongs.add(new Song("Anything","Issac",R.drawable.home_img2,R.drawable.isaac));
        mSongs.add(new Song("Borderline","Temp lmpala",R.drawable.home_img5,R.drawable.phiasau));
        mSongs.add(new Song("Borderline","Temp lmpala",R.drawable.home_img4,R.drawable.isaac));
        mSongs.add(new Song("Borderline","Temp lmpala",R.drawable.home_img5,R.drawable.phiasau));
        mSongs.add(new Song("Borderline","Temp lmpala",R.drawable.home_img1,R.drawable.phiasau));
        adt = new CustomSong(mSongs,this);
        rcv.setHasFixedSize(true);
        rcv.setAdapter(adt);
        rcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void clickItem(Song song) {
        Intent intent  = new Intent(ListScreen.this,MainActivity1.class);
        startActivity(intent);

    }
}