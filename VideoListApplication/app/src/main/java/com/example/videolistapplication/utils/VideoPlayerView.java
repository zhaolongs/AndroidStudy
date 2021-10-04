package com.example.videolistapplication.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.videolistapplication.R;
import com.example.videolistapplication.adapter.MyItemRecyclerViewAdapter;
import com.example.videolistapplication.utils.MediaHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VideoPlayerView extends FrameLayout implements View.OnClickListener {
    private boolean hasPause;

    public VideoPlayerView(@NonNull Context context) {
        super(context);
        initViewFunction();
    }

    public VideoPlayerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViewFunction();
    }

    public VideoPlayerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViewFunction();
    }

    public VideoPlayerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViewFunction();
    }

    ProgressBar pbLoading;
    ImageView ivReplay;
    ImageView ivShare;
    RelativeLayout rlPlayFinish;
    TextView tvTitle;
    ImageView ivPlay;
    TextView tvAllTime;
    TextView tvUseTime;
    SeekBar seekBar;
    TextView tvTime;
    ImageView ivFullscreen;
    LinearLayout llPlayControl;

    public TextureView videoView;

    void initViewFunction() {
        View view = View.inflate(getContext(), R.layout.video_controller_layout, this);

        llPlayControl = view.findViewById(R.id.ll_play_control);
        pbLoading = view.findViewById(R.id.pb_loading);

        ivReplay = view.findViewById(R.id.iv_replay);
        ivReplay.setOnClickListener(this);
        ivShare = view.findViewById(R.id.iv_share);

        rlPlayFinish = view.findViewById(R.id.rl_play_finish);

        tvTitle = view.findViewById(R.id.tv_title);

        ivPlay = view.findViewById(R.id.iv_play);
        ivPlay.setOnClickListener(this);
        tvAllTime = view.findViewById(R.id.tv_all_time);

        tvUseTime = view.findViewById(R.id.tv_use_time);

        seekBar = view.findViewById(R.id.seekBar);

        tvTime = view.findViewById(R.id.tv_time);

        videoView = view.findViewById(R.id.tv_texture_view);

        ivFullscreen = view.findViewById(R.id.iv_fullscreen);

        initViewDisplay();


        //设置视频播放时的点击界面
        setOnTouchListener(onTouchListener);
        //设置SeekBar的拖动监听
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        //播放完成的界面要销毁触摸事件
        rlPlayFinish.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    public void initViewDisplay() {
        tvTitle.setVisibility(View.VISIBLE);
        ivPlay.setVisibility(View.VISIBLE);
        ivPlay.setImageResource(R.mipmap.video_play);
        tvAllTime.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.GONE);
        llPlayControl.setVisibility(View.GONE);
        rlPlayFinish.setVisibility(View.GONE);
        tvUseTime.setText("00:00");
        seekBar.setProgress(0);
        seekBar.setSecondaryProgress(0);
    }

    MyItemRecyclerViewAdapter myItemRecyclerViewAdapter;
    int position;

    public void setDataFunction(MyItemRecyclerViewAdapter myItemRecyclerViewAdapter, int position) {
        if (myItemRecyclerViewAdapter.currentPosition != position) {
            initViewDisplay();
        }
        this.myItemRecyclerViewAdapter = myItemRecyclerViewAdapter;
        this.position = position;
        //进行TextureView控件创建的监听
        videoView.setSurfaceTextureListener(surfaceTextureListener);
    }

    public boolean hasPlay;//是否播放了
    private OnTouchListener onTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            //按下+已经播放了
            if (event.getAction() == MotionEvent.ACTION_DOWN && hasPlay) {
                //显示或者隐藏视频控制界面
                showOrHideVideoController();
            }
            return true;//去处理事件
        }
    };


    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        //拖动的过程中调用
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        //开始拖动的时候调用
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            //暂停视频的播放、停止时间和进度条的更新
            MediaHelper.pause();
            mHandler.removeMessages(MSG_UPDATE_TIME_PROGRESS);
        }

        //停止拖动时调用
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            //把视频跳转到对应的位置
            int progress = seekBar.getProgress();
            int duration = mPlayer.getDuration();
            int position = duration * progress / 100;
            mPlayer.seekTo(position);
            //开始播放、开始时间和进度条的更新
            MediaHelper.play();
            updatePlayTimeAndProgress();
        }
    };


    private MediaPlayer mPlayer;
    private Surface mSurface;
    private TextureView.SurfaceTextureListener surfaceTextureListener = new TextureView.SurfaceTextureListener() {

        //创建完成  TextureView才可以进行视频画面的显示
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            // Log.i(TAG,"onSurfaceTextureAvailable");
            mSurface = new Surface(surface);//连接对象（MediaPlayer和TextureView）
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
            // Log.i(TAG,"onSurfaceTextureSizeChanged");
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            // Log.i(TAG,"onSurfaceTextureDestroyed");
            return true;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {
            // Log.i(TAG,"onSurfaceTextureUpdated");
        }
    };


    //视频播放（视频的初始化）
    private void play(String url) {
        try {
            mPlayer = MediaHelper.getInstance();
            mPlayer.reset();
//            mPlayer.setDataSource(url);
            //获得播放源访问入口
            AssetFileDescriptor afd = this.getResources().openRawResourceFd(R.raw.video1); // 注意这里的区别
            //给MediaPlayer设置播放源
            mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            //让MediaPlayer和TextureView进行视频画面的结合
            mPlayer.setSurface(mSurface);
            //设置监听
            mPlayer.setOnBufferingUpdateListener(onBufferingUpdateListener);
            mPlayer.setOnCompletionListener(onCompletionListener);
            mPlayer.setOnErrorListener(onErrorListener);
            mPlayer.setOnPreparedListener(onPreparedListener);
            mPlayer.setScreenOnWhilePlaying(true);//在视频播放的时候保持屏幕的高亮
            //异步准备
            mPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //准备完成监听
    private MediaPlayer.OnPreparedListener onPreparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            //隐藏视频加载进度条
            pbLoading.setVisibility(View.GONE);
            videoView.setVisibility(VISIBLE);
            //进行视频的播放
            MediaHelper.play();
            hasPlay = true;
            //隐藏标题
            delayHideTitle();
            //设置视频的总时长
            SimpleDateFormat format = new SimpleDateFormat("mm:ss");
            String time = format.format(new Date(mPlayer.getDuration()));
            tvTime.setText(time);
            tvUseTime.setText("00:00");
            //更新播放的时间和进度
            //获取目前播放的进度
            int currentPosition = MediaHelper.getInstance().getCurrentPosition();
            //格式化
            String useTime = format.format(new Date(currentPosition));
            tvUseTime.setText(useTime);
            //更新进度
            int duration = MediaHelper.getInstance().getDuration();
            if (duration == 0) {
                return;
            }
            int progress = 100 * currentPosition / duration;
            seekBar.setProgress(progress);
            //发送一个更新的延时消息
            mHandler.sendEmptyMessageDelayed(MSG_UPDATE_TIME_PROGRESS, 500);
        }
    };
    private static final int MSG_HIDE_TITLE = 0;
    private static final int MSG_UPDATE_TIME_PROGRESS = 1;
    private static final int MSG_HIDE_CONTROLLER = 2;
    //消息处理器
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_HIDE_TITLE:
                    tvTitle.setVisibility(View.GONE);
                    break;
                case MSG_UPDATE_TIME_PROGRESS:
                    updatePlayTimeAndProgress();
                    break;
                case MSG_HIDE_CONTROLLER:
                    showOrHideVideoController();
                    break;
            }
        }
    };

    //显示或者隐藏视频控制界面
    private void showOrHideVideoController() {
        if (llPlayControl.getVisibility() == View.GONE) {
            //显示（标题、播放按钮、视频进度控制）
            tvTitle.setVisibility(View.VISIBLE);
            ivPlay.setVisibility(View.VISIBLE);
            //加载动画
            Animation animation = AnimationUtils.loadAnimation(this.getContext(), R.anim.bottom_enter);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    llPlayControl.setVisibility(View.VISIBLE);
                    //过2秒后自动隐藏
                    mHandler.sendEmptyMessageDelayed(MSG_HIDE_CONTROLLER, 2000);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            //执行动画
            llPlayControl.startAnimation(animation);
        } else {
            //隐藏（标题、播放按钮、视频进度控制）
            tvTitle.setVisibility(View.GONE);
            ivPlay.setVisibility(View.GONE);
            //加载动画
            Animation animation = AnimationUtils.loadAnimation(this.getContext(), R.anim.bottom_exit);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    llPlayControl.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            //执行动画
            llPlayControl.startAnimation(animation);
        }
    }

    //更新播放的时间和进度
    public void updatePlayTimeAndProgress() {
        //获取目前播放的进度
        int currentPosition = MediaHelper.getInstance().getCurrentPosition();
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");

        //格式化
        String useTime = format.format(new Date(currentPosition));
        tvUseTime.setText(useTime);
        //更新进度
        int duration = MediaHelper.getInstance().getDuration();
        if (duration == 0) {
            return;
        }
        int progress = 100 * currentPosition / duration;
        seekBar.setProgress(progress);
        //发送一个更新的延时消息
        mHandler.sendEmptyMessageDelayed(MSG_UPDATE_TIME_PROGRESS, 500);
    }

    public void delayHideTitle() {
        //移除消息
        mHandler.removeMessages(MSG_HIDE_TITLE);
        //发送一个空的延时2秒消息
        mHandler.sendEmptyMessageDelayed(MSG_HIDE_TITLE, 2000);
    }

    //错误监听
    private MediaPlayer.OnErrorListener onErrorListener = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            return true;
        }
    };

    //完成监听
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            //视频播放完成
            tvTitle.setVisibility(View.VISIBLE);
            rlPlayFinish.setVisibility(View.VISIBLE);
            tvAllTime.setVisibility(View.VISIBLE);
        }
    };

    //缓冲的监听
    private MediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(MediaPlayer mp, int percent) {
//            Log.i(TAG,"percent:"+percent);
            seekBar.setSecondaryProgress(percent);
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_replay:
                //隐藏播放完成界面
                rlPlayFinish.setVisibility(View.GONE);
                //隐藏时间
                tvAllTime.setVisibility(View.GONE);
                tvUseTime.setText("00:00");
                //进度条
                seekBar.setProgress(0);
                //把媒体播放器的位置移动到开始的位置
                MediaHelper.getInstance().seekTo(0);
                //开始播放
                MediaHelper.play();
                //延时隐藏标题
                delayHideTitle();
                break;
            case R.id.iv_share:
                break;
            case R.id.iv_play:


                if (position != myItemRecyclerViewAdapter.currentPosition && myItemRecyclerViewAdapter.currentPosition != -1) {

                    //让其他的条目停止播放(还原条目开始的状态)
                    MediaHelper.release();
                    //把播放条目的下标设置给适配器
                    myItemRecyclerViewAdapter.currentPosition = position;
                    //刷新显示
                    myItemRecyclerViewAdapter.notifyDataSetChanged();
                    //播放
                    ivPlay.setVisibility(View.GONE);
                    tvAllTime.setVisibility(View.GONE);
                    pbLoading.setVisibility(View.VISIBLE);
                    //视频播放界面也需要显示
                    videoView.setVisibility(View.VISIBLE);
                    ivPlay.setImageResource(R.mipmap.video_pause);
                    play("");
                    return;
                }

                if (MediaHelper.getInstance().isPlaying()) {
                    //暂停
                    MediaHelper.pause();
                    //移除隐藏Controller布局的消息
                    mHandler.removeMessages(MSG_HIDE_CONTROLLER);
                    //移除更新播放时间和进度的消息
                    mHandler.removeMessages(MSG_UPDATE_TIME_PROGRESS);
                    ivPlay.setImageResource(R.mipmap.video_play);
                    hasPause = true;
                } else {
                    if (hasPause) {
                        //继续播放
                        MediaHelper.play();
                        mHandler.sendEmptyMessageDelayed(MSG_HIDE_CONTROLLER, 2000);
                        updatePlayTimeAndProgress();
                        hasPause = false;
                    } else {
                        //播放
                        ivPlay.setVisibility(View.GONE);
                        tvAllTime.setVisibility(View.GONE);
                        pbLoading.setVisibility(View.VISIBLE);
                        //视频播放界面也需要显示
                        videoView.setVisibility(View.VISIBLE);
                        myItemRecyclerViewAdapter.currentPosition = position;
                        play("");
                    }
                    ivPlay.setImageResource(R.mipmap.video_pause);
                }


                break;
            case R.id.iv_fullscreen:
                break;
        }
    }
}
