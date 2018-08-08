package com.example.ba.crazymusic;

public interface IMediaManager {
    void playSong(int idResource);

    void stop();

    void release();

    void pause();

    int getCurrentPosition();

    int getDuration();

    void onChangeStateMedia();

    void resume();

    void addListener(OnChangeStateListener listener);

    interface OnChangeStateListener {
        void notifyStateChange(int state);
    }
}
