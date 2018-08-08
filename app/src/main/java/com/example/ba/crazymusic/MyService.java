package com.example.ba.crazymusic;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.List;

public class MyService extends Service implements IMediaManager {
    private MediaManager mMediaManager;

    @Override
    public void onCreate() {
        super.onCreate();
//        mMediaManager = new MediaManager(this);
        mMediaManager = new MediaManager(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    public int getCurrentPosition() {
        return mMediaManager.getCurrentPosition();
    }

    @Override
    public int getDuration() {
        return mMediaManager.getDuration();
    }

    @Override
    public void onChangeStateMedia() {
        mMediaManager.onChangeStateMedia();
    }

    @Override
    public void resume() {
        mMediaManager.resume();

    }

    @Override
    public void playSong(int idResource) {
        mMediaManager.playSong(idResource);
    }

    @Override
    public void stop() {
        mMediaManager.stop();
    }

    @Override
    public void release() {
        mMediaManager.release();
    }

    @Override
    public void pause() {
        mMediaManager.pause();
    }

    public void addListener(OnChangeStateListener listener) {
        mMediaManager.addListener(listener);
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

}
