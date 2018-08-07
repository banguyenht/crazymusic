package com.example.ba.crazymusic;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.ba.crazymusic.Itemsong;
import com.example.ba.crazymusic.MediaManager;

import java.io.IOException;
import java.util.List;

public class MyService extends Service {
    private MediaManager mediaManager;
    private List<Itemsong> itemsongs;

    public void setItemsongs(List<Itemsong> itemsongs) {
        this.itemsongs = itemsongs;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaManager = new MediaManager();
        Log.d("create service", "create service");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int a = 0;
        return START_REDELIVER_INTENT;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }

    public void play(int position) {
        try {
            mediaManager.setMcontext(this);
            mediaManager.setResource(itemsongs.get(position).getmResMp3());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaManager.play();
    }

    public void playNext(int position) {
        stop();
        play(position);
    }

    public void pause() {
        mediaManager.pause();
    }

    public int getCurrentPosition() {
        return mediaManager.getCurrentPosition();
    }

    public void stop() {
        mediaManager.stop();
    }

    public void resume() {
        mediaManager.resume();
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        Toast.makeText(this, "unbindService", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder(this);
    }


    public static class MyBinder extends Binder {
        public MyService service;

        public MyBinder(MyService service) {

            this.service = service;
        }

        public MyService getService() {
            return service;
        }
    }
}
