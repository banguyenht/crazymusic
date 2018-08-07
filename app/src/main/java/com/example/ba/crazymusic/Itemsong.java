package com.example.ba.crazymusic;

public class Itemsong {
    private int mResource;
    private String mTitleSong;

    public Itemsong(int mResource, String mTitleSong) {
        this.mResource = mResource;
        this.mTitleSong = mTitleSong;
    }

    public int getResource() {
        return mResource;
    }

    public void setResource(int mResMp3) {
        this.mResource = mResMp3;
    }

    public String getDisplay() {
        return mTitleSong;
    }

    public void setTitleSong(String mTitleSong) {
        this.mTitleSong = mTitleSong;
    }
}
