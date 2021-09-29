package com.example.studyapp.common.acapter;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.studyapp.common.bean.VideoBean;
import com.example.studyapp.common.fragment.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.studyapp.databinding.FragmentItemBinding;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private List<VideoBean> mVideoBeanList;
    private Context mContext;

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

    }

    @Override
    public int getItemCount() {
        return mVideoBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public VideoBean mItem;
        public ImageView imageView;

        public TextureView videoView;
        private MediaPlayer mPlayer;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            imageView = binding.ivVideoItem;

            //获取下屏幕宽度
            Context context = binding.getRoot().getContext();
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();

            //宽度
            int widthPixels = displayMetrics.widthPixels;

            Log.d("屏幕适配", "widthPixels" + widthPixels);

            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            layoutParams.width = widthPixels;
            imageView.setLayoutParams(layoutParams);

            imageView.setMaxHeight(widthPixels * 2);
            imageView.setMaxWidth(widthPixels);


            videoView = binding.videoView;


        }


    }
}