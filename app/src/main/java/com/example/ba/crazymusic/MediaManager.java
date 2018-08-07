package com.example.ba.crazymusic;

import android.content.Context;
import android.media.MediaPlayer;

import java.io.IOException;

public class MediaManager {
    private MediaPlayer mMediaPlayer;
    private Context mContext;

    public MediaManager() {
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public void setResource(int path)
            throws IOException {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
        }
        mMediaPlayer = MediaPlayer.create(mContext, path);
    }

    public void play() {
        if (mMediaPlayer != null) {
            mMediaPlayer.start();
        }
    }

    public int getCurrentPosition() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public void pause() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        }
    }

    public void stop() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
    }

    public void release() {
        if (mMediaPlayer == null) {
            return;
        }
        mMediaPlayer.release();
        mMediaPlayer = null;
    }

    public void resume() {
        if (mMediaPlayer == null) {
            return;
        }
        mMediaPlayer.seekTo(mMediaPlayer.getCurrentPosition());
        mMediaPlayer.start();
    }
}
