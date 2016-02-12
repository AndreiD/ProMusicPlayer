package com.me.promusicplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Toast;
import com.me.promusicplayer.adapters.MainAdapter;
import com.me.promusicplayer.models.Song;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener, MediaController.MediaPlayerControl, MediaPlayer.OnErrorListener {

  private ListView listView_main;
  private ArrayList<Song> the_data;
  private MediaPlayer mediaPlayer;
  private MediaController controller;
  private int current_item = 0;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    the_data = getData();
    listView_main = (ListView) findViewById(R.id.listView_main);
    listView_main.setAdapter(new MainAdapter(MainActivity.this, the_data));

    mediaPlayer = new MediaPlayer();
    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    mediaPlayer.setOnErrorListener(this);

    listView_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("Clicked", "You clicked on position " + String.valueOf(the_data.get(position)));
        try {
          mediaPlayer.reset();
          mediaPlayer.setDataSource(the_data.get(position).getUrl());
          mediaPlayer.prepareAsync();
        } catch (IOException e) {
          e.printStackTrace();
        }

        current_item = position;
      }
    });

    mediaPlayer.setOnPreparedListener(this);

    controller = new MediaController(MainActivity.this) {
      @Override public void hide() {
      }
    };
    controller.setMediaPlayer(this);
    controller.setAnchorView(findViewById(android.R.id.content));
    controller.setEnabled(true);
  }

  private ArrayList<Song> getData() {
    ArrayList<Song> songArrayList = new ArrayList<>();
    Song song = new Song("http://www.mfiles.co.uk/mp3-downloads/mendelssohn-wedding-march.mp3", "Wedding March", "Felix Mendelssohn");
    songArrayList.add(song);
    song = new Song("http://www.mfiles.co.uk/mp3-downloads/wagner-bridal-chorus.mp3", "Bridal Chorus", "Richard Wagner");
    songArrayList.add(song);
    song = new Song("http://www.mfiles.co.uk/mp3-downloads/pachelbels-canon-arranged.mp3", "Canon in D", "Johann Pachelbel");
    songArrayList.add(song);
    song = new Song("http://www.mfiles.co.uk/mp3-downloads/book1-prelude01.mp3", "Prelude No. 1 (Ave Maria)", "Johann Sebastian Bach");
    songArrayList.add(song);
    song = new Song("http://www.mfiles.co.uk/mp3-downloads/arabesque1.mp3", "Arabeque No. 1", "Claude Debussy");
    songArrayList.add(song);
    song = new Song("http://www.mfiles.co.uk/mp3-downloads/debussy-clair-de-lune.mp3", "Clair de Lune", "Claude Debussy");
    songArrayList.add(song);
    return songArrayList;
  }

  @Override public void onPrepared(MediaPlayer mp) {

    mediaPlayer.start();
    Toast.makeText(MainActivity.this, "Playing " + the_data.get(current_item).getName(), Toast.LENGTH_LONG).show();
    controller.show(0);
    controller.requestFocus();
  }

  @Override public void start() {
    mediaPlayer.start();
  }

  @Override public void pause() {
    mediaPlayer.pause();
  }

  @Override public int getDuration() {
    return mediaPlayer.getDuration();
  }

  @Override public int getCurrentPosition() {
    try {
      return mediaPlayer.getCurrentPosition();
    } catch (Exception ex) {
      return 0;
    }
  }

  @Override public void seekTo(int pos) {
    mediaPlayer.seekTo(pos);
  }

  @Override public boolean isPlaying() {
    return mediaPlayer.isPlaying();
  }

  @Override public int getBufferPercentage() {
    return 0;
  }

  @Override public boolean canPause() {
    return true;
  }

  @Override public boolean canSeekBackward() {
    return true;
  }

  @Override public boolean canSeekForward() {
    return true;
  }

  @Override public int getAudioSessionId() {
    return 0;
  }

  @Override public boolean onError(MediaPlayer mp, int what, int extra) {
    Log.e("onError triggered", "Code: " + String.valueOf(what) + " extra: " + String.valueOf(extra));
    mp.reset();
    return false;
  }

  @Override protected void onDestroy() {
    if (mediaPlayer != null) {
      mediaPlayer.release();
    }
    super.onDestroy();
  }
}
