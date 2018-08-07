package com.example.ba.crazymusic;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener, SongAdapter.IAdapter {
    public static String[] SONGNAMES = {"Anh_Khong_Hoi_Tiec",
            "Cham_Day_Noi_Dau", "Lo_Nhu_Anh_Yeu_Em",
            "nguoi_tung_thuong", "Yeu Nham", "Ngo", "Cham tay noi dau", "Nguoi am phu"};
    private RecyclerView mRecyclerView;
    private SongAdapter mAdapter;
    private List<Itemsong> mListsong;
    private Button mButtonNext;
    private Button mButtonPrevious;
    private Button mButtonPausePlay;
    private ServiceConnection mConn;
    private MyService mService;
    public static int mCurrrentPosition;
    private Intent mIntent;
    private boolean mIsPlay;
    private int mCurrentPosition;
    String TAG = MainActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initService();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_next:
                if (mCurrrentPosition == mListsong.size()) {
                    mCurrrentPosition = 0;
                }
                mService.playNext(mCurrrentPosition++);
                break;
            case R.id.button_previous:
                if (mCurrrentPosition == 0) {
                    mCurrrentPosition = mListsong.size() - 1;
                }
                mService.playNext(mCurrrentPosition--);
                break;
            case R.id.button_play_pause:
                // mIsPlay == false thi mp3 chay
                if (mIsPlay) {
                    mService.pause();
                    mButtonPausePlay.
                            setBackgroundResource(android.R.drawable.ic_media_play);
                } else {
                    mService.resume();
                    mButtonPausePlay.
                            setBackgroundResource(android.R.drawable.ic_media_pause);
                }
                mIsPlay = !mIsPlay;
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(int position) {
        mButtonPausePlay.setBackgroundResource(android.R.drawable.ic_media_pause);
        mService.play(position);
        mCurrentPosition = position;
        mIsPlay=true;
    }

    @Override
    public Itemsong getItem(int positon) {
        return mListsong.get(positon);
    }

    @Override
    public int getCount() {
        return mListsong.size();
    }

    private void init() {
        mRecyclerView = findViewById(R.id.recyclerview_rc);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mListsong = new ArrayList<>();
        mListsong.add(new Itemsong(R.raw.anhkhonghoitiec, SONGNAMES[0]));
        mListsong.add(new Itemsong(R.raw.chamdaynoidau, SONGNAMES[1]));
        mListsong.add(new Itemsong(R.raw.lonhuanhye, SONGNAMES[2]));
        mListsong.add(new Itemsong(R.raw.nguoitungthuong, SONGNAMES[3]));
        mListsong.add(new Itemsong(R.raw.yeunham, SONGNAMES[4]));
        mListsong.add(new Itemsong(R.raw.anhkhonghoitiec, SONGNAMES[0]));
        mListsong.add(new Itemsong(R.raw.ngo, SONGNAMES[5]));
        mListsong.add(new Itemsong(R.raw.nguoitungthuong, SONGNAMES[3]));
        mListsong.add(new Itemsong(R.raw.lonhuanhye, SONGNAMES[2]));
        mListsong.add(new Itemsong(R.raw.nguoitungthuong, SONGNAMES[3]));
        mListsong.add(new Itemsong(R.raw.yeunham, SONGNAMES[4]));
        mListsong.add(new Itemsong(R.raw.nguoiamphu, SONGNAMES[7]));
        mListsong.add(new Itemsong(R.raw.ngo, SONGNAMES[5]));
        mAdapter = new SongAdapter();
        mAdapter.setmIAdapter(this);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);
        mButtonNext = findViewById(R.id.button_next);
        mButtonNext.setOnClickListener(this);
        mButtonPrevious = findViewById(R.id.button_previous);
        mButtonPrevious.setOnClickListener(this);
        mButtonPausePlay = findViewById(R.id.button_play_pause);
        mButtonPausePlay.setOnClickListener(this);
    }

    public void initService() {
        mIntent = new Intent(MainActivity.this, MyService.class);
        mConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName,
                                           IBinder iBinder) {
                MyService.MyBinder mMyBinder = (MyService.MyBinder) iBinder;
                mService = mMyBinder.getService();
                mService.setItemsongs(mListsong);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        bindService(mIntent, mConn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConn);
    }
}
