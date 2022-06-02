package com.example.exoplayerwithrecyclerview;

import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;

public class MainActivity extends AppCompatActivity {

  StyledPlayerView playerView;
  ProgressBar progressBar;
  ImageView bfFullScreen;
  ExoPlayer simpleExoPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerView = findViewById(R.id.idExoPlayerVIew);
        progressBar = findViewById(R.id.progress_bar);
        bfFullScreen = findViewById(R.id.bt_fullscreen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Uri videoUrl = Uri.parse("http://159.223.79.46:5080/WebRTCApp/play.html?name=durbar-exclusive_10");



        simpleExoPlayer = new ExoPlayer.Builder(this).setMediaSourceFactory(new DefaultMediaSourceFactory(this).setLiveTargetOffsetMs(5000)).build();
        MediaItem mediaItem = new MediaItem.Builder()
                .setUri(videoUrl)
                        .setLiveConfiguration(
                                new MediaItem.LiveConfiguration.Builder()
                                        .setMaxPlaybackSpeed(1.02f)
                                        .build()
                        ).build();

        MediaSource mediaSource = new HlsMediaSource.Factory(new DefaultHttpDataSource.Factory()).createMediaSource(mediaItem);
        simpleExoPlayer.setMediaSource(mediaSource);
        playerView.setPlayer(simpleExoPlayer);


        simpleExoPlayer.prepare();
        //simpleExoPlayer.setPlayWhenReady(true);
        //simpleExoPlayer.play();
    }

}