package com.example.ba.crazymusic;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener, IMediaManager.OnChangeStateListener {
    private Button mButtonPausePlay;
    private MyService mService;
    private ServiceConnection  mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.MyBinder mMyBinder = (MyService.MyBinder) iBinder;
            mService = mMyBinder.getService();
            mService.addListener(MainActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent mIntent = new Intent(MainActivity.this, MyService.class);
        bindService(mIntent, mConn, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_play_pause:
                mService.onChangeStateMedia();
                break;
        }
    }

    private void initView() {
        mButtonPausePlay = findViewById(R.id.button_play_pause);
        mButtonPausePlay.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConn);
    }

    @Override
    public void notifyStateChange(int state) {
        switch (state) {
            case Constants.STATE_PAUSE:
                mService.pause();
                mButtonPausePlay.setBackgroundResource(android.R.drawable.ic_media_play);
                break;
            case Constants.STATE_PLAY:
                mService.playSong(R.raw.kemduyen);
                mButtonPausePlay.setBackgroundResource(android.R.drawable.ic_media_pause);
                break;
            default:
                break;
        }
    }
}

