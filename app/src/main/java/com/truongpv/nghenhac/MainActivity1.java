package com.truongpv.nghenhac;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity1 extends AppCompatActivity {
    ImageView play, prev, next, imageView, imgXoay;
    TextView songTitle, songSinger;
    SeekBar mSeekBarTime;
    static MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    int currentIndex = 0;
    List<Song> mSongs;
    Song song;

    TextView playerDuration, playerPosition;
    ImageView btFF, btRew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        play = findViewById(R.id.stop);
        prev = findViewById(R.id.Lui);
        next = findViewById(R.id.tien);
        imageView = findViewById(R.id.imgSong);
        songTitle = findViewById(R.id.txtTitle1);
        songSinger = findViewById(R.id.txtSinger1);
        mSeekBarTime = findViewById(R.id.seekBar);
        imgXoay = findViewById(R.id.imgxoay);


        playerDuration = findViewById(R.id.playerDuration);
        playerPosition = findViewById(R.id.playerPosition);
        btFF = findViewById(R.id.bt_ff1);
        btRew = findViewById(R.id.bt_rew);

        Animation xoay = AnimationUtils.loadAnimation(this, R.anim.anim_quaydia);
        Animation dungxoay = AnimationUtils.loadAnimation(this, R.anim.anim_dungquay);
        Animation lacnhac = AnimationUtils.loadAnimation(this, R.anim.anim_laccaichuong);

        imageView.startAnimation(xoay);
        imgXoay.startAnimation(lacnhac);

        mSongs =new ArrayList<>();
        song = new Song("Đen Đá Không Đường ", "Amee ", R.drawable.anh1, R.drawable.tinh_yeu_cao_thuong);
        mSongs.add(song);

        if (song != null) {
            if (mMediaPlayer != null) {
                mMediaPlayer.stop();
            }
            imageView.setImageResource(song.getImageSong());
            songTitle.setText(song.getTitle());
            songSinger.setText(song.getSingle());
            mMediaPlayer = MediaPlayer.create(getApplicationContext(), song.getResourece());
            playerDuration.setText(convertFormat(mMediaPlayer.getDuration()));

            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mMediaPlayer.start();
                }
            });

        }


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer == null) { //
                    Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show();
                } else {
                    mSeekBarTime.setMax(mMediaPlayer.getDuration());
                    if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
//                        clickStopService();
                        mMediaPlayer.pause();
                        play.setImageResource(R.drawable.stop);

                        //animation
                        imageView.startAnimation(dungxoay);
                    } else {
//                        clickStartService();
                        mMediaPlayer.start();
                        play.setImageResource(R.drawable.play);

                        //animation
                        imageView.startAnimation(xoay);
                    }
                    SongNames();
                }

            }

        });


        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer != null) {
                    play.setImageResource(R.drawable.play);
                }
                if (currentIndex > 0) {
                    currentIndex--;
                } else {
                    currentIndex = mSongs.size() - 1;
                }
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                }
//
//                clickStartService();
                mMediaPlayer = MediaPlayer.create(getApplicationContext(), mSongs.get(currentIndex).getResourece());
                mMediaPlayer.start();
                SongNames();
            }
        });
        btRew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = mMediaPlayer.getCurrentPosition();
                if (mMediaPlayer.isPlaying() && currentPosition > 5000) {
                    currentPosition = currentPosition - 5000;
                    mMediaPlayer.seekTo(currentPosition);

                    playerPosition.setText(convertFormat(currentPosition));
                }
            }
        });
        btFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = mMediaPlayer.getCurrentPosition();
                int duration = mMediaPlayer.getDuration();
                if (mMediaPlayer.isPlaying() && duration != currentPosition) {
                    currentPosition = currentPosition + 5000;
                    mMediaPlayer.seekTo(currentPosition);

                    playerDuration.setText(convertFormat(mMediaPlayer.getDuration()));
                }
            }
        });

    }

    private String convertFormat(int duration) {
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }


//    private void clickStartService() {
//        Intent intent = new Intent(this, MyService.class);
//        Bundle bundle = new Bundle();
////
////        Song song = mSongs.get(currentIndex);
//        bundle.putSerializable("object_song", song);
//        intent.putExtras(bundle);
//
//        startService(intent);
//    }

//    private void clickStopService() {
//        Intent intent = new Intent(this, MyService.class);
//        stopService(intent);
//    }

    //    private void sendActiontoServie(int action, Song song){
////        Toast.makeText(this, "aaaaaaaaa", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(MainActivity.this, MyService.class);
//        //lợi dụng hàm bài trước
//        intent.putExtra("action_music_service", action);
//        intent.putExtra("object_song",song);
//        startService(intent);
////        Toast.makeText(this, "bbbbbbbbbb", Toast.LENGTH_SHORT).show();
//    }
    private void SongNames() {
        Song song = mSongs.get(currentIndex);
        imageView.setImageResource(song.getImageSong());
        songTitle.setText(song.getTitle());
        songSinger.setText(song.getSingle());

        //seek bar duration
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mSeekBarTime.setMax(mMediaPlayer.getDuration());
                mMediaPlayer.start();
            }
        });

        mSeekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mMediaPlayer.seekTo(progress);
                    mSeekBarTime.setProgress(progress);
                }
                playerPosition.setText(convertFormat(mMediaPlayer.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mMediaPlayer!= null){
                    try {
                        if(mMediaPlayer.isPlaying()){
                            Message message = new Message();

                            message.what = mMediaPlayer.getCurrentPosition();
                            handler.sendMessage(message);
                            Thread.sleep(1000);
                        }
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    @SuppressLint("Handle Leak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mSeekBarTime.setProgress(msg.what);
        }
    };


}
