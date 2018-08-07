package com.example.ba.crazymusic;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongItemView> {
        private IAdapter mIAdapter;

    public void setmIAdapter(IAdapter mIAdapter) {
        this.mIAdapter = mIAdapter;
    }

    @NonNull
    @Override
    public SongItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SongItemView(LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.itemsong, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SongItemView songItemView,final int i) {
        final Itemsong mCurrentSong=mIAdapter.getItem(i);
        songItemView.mTextViewTitle.setText(mCurrentSong.getmDisplay());
        songItemView.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIAdapter.onClick(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mIAdapter.getCount();
    }

    public class SongItemView extends RecyclerView.ViewHolder {
        private TextView mTextViewTitle;

        public SongItemView(@NonNull View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.textview_title);
        }
    }
     public interface IAdapter{
        void onClick(int position);
        Itemsong getItem(int positon);
        int getCount();
     }

}
