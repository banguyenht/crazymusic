package com.example.ba.crazymusic;

import android.content.Context;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by Lap trinh on 3/7/2018.
 */

public class MediaManager {
    private MediaPlayer mediaPlayer;
    private Context mcontext;
    public MediaManager() {
    }

    public void setMcontext(Context mcontext) {
        this.mcontext = mcontext;
    }

    public void setResource(int path)
            throws IOException {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(mcontext,path);
    }

    public void play() {
        if (mediaPlayer != null){
            mediaPlayer.start();
        }
    }
    public int getCurrentPosition(){
        if (mediaPlayer!=null){
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void stop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    public void release() {
        if (mediaPlayer == null) {
            return;
        }
        mediaPlayer.release();
        mediaPlayer = null;
    }
    public void resume(){
        if(mediaPlayer== null){
            return;
        }
        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition());
        mediaPlayer.start();
    }
}
