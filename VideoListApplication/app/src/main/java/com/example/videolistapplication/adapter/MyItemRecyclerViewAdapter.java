package com.example.videolistapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;


import com.example.videolistapplication.utils.VideoPlayerView;
import com.example.videolistapplication.bean.VideoBean;
import com.example.videolistapplication.databinding.FragmentItemBinding;

import java.util.List;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private List<VideoBean> mVideoBeanList;
    private Context mContext;
    //记录之前播放的条目下标
    public  int currentPosition = -1;
    public MyItemRecyclerViewAdapter(List<VideoBean> items, Context context) {
        mVideoBeanList = items;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentItemBinding inflate = FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mVideoBeanList.get(position);
        holder.videoPlayerView.setDataFunction(this,position);
        holder.setData();
    }

    @Override
    public int getItemCount() {
        return mVideoBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public VideoBean mItem;

        public VideoPlayerView videoPlayerView;

        private Context mContext;


        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());

            mContext = binding.getRoot().getContext();
            videoPlayerView = binding.videoPlayerView;

        }


        public void setData() {


        }
    }

}