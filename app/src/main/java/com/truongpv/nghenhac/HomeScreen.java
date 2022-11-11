package com.truongpv.nghenhac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.Map;

public class HomeScreen extends AppCompatActivity {

    ImageView imglist1,imglist2,imglist3;
    Button btnmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        imglist1 = findViewById(R.id.imageView8);
        imglist2 = findViewById(R.id.imageView9);
        imglist3 = findViewById(R.id.imageView10);
        btnmap = findViewById(R.id.btnmap);

        imglist1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(HomeScreen.this,ListScreen.class);
                startActivity(intent);
            }
        });
        imglist2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(HomeScreen.this,ListScreen.class);
                startActivity(intent);
            }
        });
        imglist3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(HomeScreen.this,ListScreen.class);
                startActivity(intent);
            }
        });
        btnmap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(HomeScreen.this, Map.class);
                startActivity(intent);
            }
        });

    }

}