package com.example.videolistapplication;

import android.media.MediaPlayer;

/**
 * 接口回调  视频播放 View 到 Fragment 的通信通道
 */
public interface ViewToFragmentInterface {
    void onTextureCreate(MediaPlayer mediaPlayer, FragmentToViewInterface videoStatueInterface, int position);
}
