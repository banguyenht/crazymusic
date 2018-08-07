package com.example.ba.crazymusic;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.List;

public class MyService extends Service {
    private MediaManager mMediaManager;
    private List<Itemsong> mItemsongs;

    public void setItemsongs(List<Itemsong> mItemsongs) {
        this.mItemsongs = mItemsongs;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaManager = new MediaManager();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void play(int position) {
        try {
            mMediaManager.setContext(this);
            mMediaManager.setResource(mItemsongs.get(position).getResource());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaManager.play();
    }

    public void playNext(int position) {
        stop();
        play(position);
    }

    public void pause() {
        mMediaManager.pause();
    }

    public int getCurrentPosition() {
        return mMediaManager.getCurrentPosition();
    }

    public void stop() {
        mMediaManager.stop();
    }

    public void resume() {
        mMediaManager.resume();
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
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
