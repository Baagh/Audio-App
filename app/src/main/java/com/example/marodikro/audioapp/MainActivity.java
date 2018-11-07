package com.example.marodikro.audioapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    public void playMe(View view){
         mediaPlayer.start();
    }

    public void pauseMe(View view){
        mediaPlayer.pause();
    }
    public void stop(View view){
          mediaPlayer.stop();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.my);

        audioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);

        int myMaxVolume=audioManager.getStreamMaxVolume(audioManager.STREAM_MUSIC);

        int myCurrentVolume=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar volume=(SeekBar)findViewById(R.id.seekBar);
        volume.setMax(myMaxVolume);
        volume.setProgress(myCurrentVolume);

        final SeekBar timeline=(SeekBar)findViewById(R.id.timeline);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
              timeline.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,1000);
        //set onchange listener

        volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0
                );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        timeline.setMax(mediaPlayer.getDuration());

        timeline.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
