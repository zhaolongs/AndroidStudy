package com.example.videolistapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;


import com.example.videolistapplication.ViewToFragmentInterface;
import com.example.videolistapplication.CustomVideoPlayerView;
import com.example.videolistapplication.bean.VideoBean;
import com.example.videolistapplication.databinding.FragmentItemBinding;

import java.util.List;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private List<VideoBean> mVideoBeanList;
    private Context mContext;

    private ViewToFragmentInterface mCustomVideoInterface;

    public MyItemRecyclerViewAdapter(List<VideoBean> items, Context context) {
        mVideoBeanList = items;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentItemBinding inflate = FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(inflate, mCustomVideoInterface);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mVideoBeanList.get(position);
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return mVideoBeanList.size();
    }

    public void setCustomVideoInterface(ViewToFragmentInterface videoInterface) {
        this.mCustomVideoInterface = videoInterface;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public VideoBean mItem;


        private Context mContext;

        private CustomVideoPlayerView customVideoPlayerView;


        public ViewHolder(FragmentItemBinding binding, ViewToFragmentInterface videoInterface) {
            super(binding.getRoot());

            mContext = binding.getRoot().getContext();
            customVideoPlayerView = binding.customVideoPlayer;
            customVideoPlayerView.setCustomVideoInterface(videoInterface);
        }


        public void setData(int position) {
            customVideoPlayerView.setPosition(position);

        }
    }

}