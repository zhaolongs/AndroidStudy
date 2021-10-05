package com.example.videolistapplication;


import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videolistapplication.adapter.MyItemRecyclerViewAdapter;
import com.example.videolistapplication.bean.UserBean;
import com.example.videolistapplication.bean.VideoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class ItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MyItemRecyclerViewAdapter myItemRecyclerViewAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        Context context = view.getContext();
        linearLayoutManager = new LinearLayoutManager(context);
        // Set the adapter
        if (view instanceof RecyclerView) {

            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(linearLayoutManager);

        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<VideoBean> videoBeanList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            UserBean userBean = new UserBean();
            userBean.setUserId(i);
            userBean.setUserName("早起的年轻人");
            userBean.setUserImage("https://gimg2.baidu.com/image_search/src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180520%2F0473e00bdfd2476fbe0c228a45a1652c.jpeg&refer=http%3A%2F%2F5b0988e595225.cdn.sohucs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1635338531&t=2dccf620a0c5dad30b432a37a8661d03");

            VideoBean videoBean = new VideoBean();
            videoBean.setUserBean(userBean);
            videoBean.setCollectNumber(100);
            videoBean.setPariseNumber(2903);
            videoBean.setMessageNumber(333);

            videoBean.setTitle("Android RecyclerView 滑动到指定位置 RecyclerView 滑动到顶部");
            videoBean.setVideoUrl("");
            videoBeanList.add(videoBean);
        }
        myItemRecyclerViewAdapter = new MyItemRecyclerViewAdapter(videoBeanList, getContext());
        //设置通信通道
        myItemRecyclerViewAdapter.setCustomVideoInterface(mCustomVideoInterface);
        //设置数据适配器
        recyclerView.setAdapter(myItemRecyclerViewAdapter);
        //设置滑动监听
        recyclerView.addOnScrollListener(onScrollListener);

    }
    /**
     * RecyclerView 滑动监听回调
     */
    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            /**
             * SCROLL_STATE_IDLE = 0（静止没有滚动）
             * SCROLL_STATE_DRAGGING = 1 正在被外部拖拽,一般为用户正在用手指滚动
             *  SCROLL_STATE_SETTLING = 2 自动滚动
             */
            if(newState==RecyclerView.SCROLL_STATE_IDLE){

            }

        }

        //进行滑动
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            //获取屏幕上显示的第一个条目和
            int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
            //获取最后一个条目的下标
            int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            //当前播放视频的索引
            if(firstVisibleItemPosition>mPosition||lastVisibleItemPosition<mPosition){
                //停止播放
                //暂停上一个视频播放
                if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                }
                //通知更新控制层View显示
                if (mCustomVideoStatueInterface != null) {
                    mCustomVideoStatueInterface.pause();
                }
            }


        }
    };

    /**
     * 用来记录当前播放 Item 的 MediaPlayer
     */
    private MediaPlayer mMediaPlayer;
    /**
     * 当前播放视频的条目的位置
     */
    private int mPosition=-1;
    /**
     * 接口回调  Fragment 到 视频播放 View 的通信通道
     */
    private FragmentToViewInterface mCustomVideoStatueInterface;
    /**
     * 接口回调  视频播放 View 到 Fragment 的通信通道
     */
    private ViewToFragmentInterface mCustomVideoInterface = new ViewToFragmentInterface() {
        @Override
        public void onTextureCreate(MediaPlayer mediaPlayer, FragmentToViewInterface videoStatueInterface, int position) {
            //暂停上一个视频播放
            if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
            }
            //通知更新控制层View显示
            if (mCustomVideoStatueInterface != null) {
                mCustomVideoStatueInterface.pause();
            }
            //记录当前播放的MediaPlayer
            mMediaPlayer = mediaPlayer;
            //记录当前播放的通信通道
            mCustomVideoStatueInterface = videoStatueInterface;
            //更新
            mPosition = position;
        }
    };



}