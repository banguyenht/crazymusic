package com.example.ba.crazymusic;

public class Itemsong {
    private int mResMp3;
    private String mDisplay;

    public Itemsong(int mResMp3, String mDisplay) {
        this.mResMp3 = mResMp3;
        this.mDisplay = mDisplay;
    }

    public int getmResMp3() {
        return mResMp3;
    }

    public void setmResMp3(int mResMp3) {
        this.mResMp3 = mResMp3;
    }

    public String getmDisplay() {
        return mDisplay;
    }

    public void setmDisplay(String mDisplay) {
        this.mDisplay = mDisplay;
    }
}
