package com.example.ba.crazymusic;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;

public class MediaManager implements IMediaManager {
    private MediaPlayer mMediaPlayer;
    private Context mContext;
    private int mMediaState;
    private List<OnChangeStateListener> mListeners;

    public MediaManager(Context context) {
        this.mContext = context;
        mListeners = new ArrayList<>();
    }

    public void setMediaState(int mediaState) {
        this.mMediaState = mediaState;
    }

    public int getMediaState() {
        return mMediaState;
    }

    @Override
    public void playSong(int idResource) {
        mMediaPlayer = MediaPlayer.create(mContext, idResource);
        mMediaPlayer.start();
        setMediaState(Constants.STATE_PLAY);
    }

    @Override
    public void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            setMediaState(Constants.STATE_STOP);
        }
    }

    @Override
    public void release() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            setMediaState(Constants.STATE_RELEASED);
        }
    }

    @Override
    public void pause() {
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
            setMediaState(Constants.STATE_PAUSE);
        }
    }

    @Override
    public int getCurrentPosition() {
        return mMediaPlayer.getCurrentPosition();
    }

    @Override
    public int getDuration() {
        return mMediaPlayer.getDuration();
    }

    @Override
    public void onChangeStateMedia() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            setMediaState(Constants.STATE_PAUSE);
        }
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
            setMediaState(Constants.STATE_PLAY);
        }
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
            setMediaState(Constants.STATE_PLAY);
        }
        updateStateMedia();
    }

    @Override
    public void resume() {
        mMediaPlayer.seekTo(getCurrentPosition());
        mMediaPlayer.start();
        setMediaState(Constants.STATE_RESUME);
    }

    public void addListener(OnChangeStateListener listener) {
        mListeners.add(listener);
    }

    private void updateStateMedia() {
//        mListeners.get(0).notifyStateChange(getMediaState());
        for (OnChangeStateListener listener : mListeners) {
            listener.notifyStateChange(getMediaState());
        }
    }
}
