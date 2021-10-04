package com.example.videolistapplication;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Surface surface1;
    private MediaPlayer mediaPlayer;

    private Context mContext;
    private boolean isPrepare = false;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance() {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private TextureView mTextureView;
    private Button mStartButton;
    private Button mStopButton;
    private ProgressBar mProgressBar;
    private ImageView mPlayImageView;
    private SeekBar mSeekBar;

    private TextView mCurrentTimeTextView;
    private TextView mTotalTimeTextView;

    private FrameLayout mControllerFrameLayout;
    private LinearLayout mControllerLinLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        mTextureView = view.findViewById(R.id.tv_texture_view);
        mStartButton = view.findViewById(R.id.bt_start);
        mStopButton = view.findViewById(R.id.bt_pasue);
        mProgressBar = view.findViewById(R.id.pb_loading);
        mPlayImageView = view.findViewById(R.id.iv_video_play);
        mSeekBar = view.findViewById(R.id.sb_progress);
        mContext = view.getContext();

        mCurrentTimeTextView = view.findViewById(R.id.tv_current_time);
        mTotalTimeTextView = view.findViewById(R.id.tv_total_time);
        mControllerFrameLayout = view.findViewById(R.id.fl_controller);
        mControllerLinLayout= view.findViewById(R.id.ll_progress);


        //设置拖动监听
        mSeekBar.setOnSeekBarChangeListener(mOnSeekBarChangeListener);
        mControllerFrameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //正在播放中
                    if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                        //显示控制层
                        showControllerFunction();
                        //2秒后再隐藏
                        mHandelr.sendEmptyMessageDelayed(1, 2000);
                    }
                }
                return true;
            }
        });
        return view;
    }

    /**
     * 隐藏
     */
    void hideControllerFunction(){
        mPlayImageView.setVisibility(View.GONE);
        if(mControllerLinLayout.getVisibility()==View.VISIBLE) {
            //加载一下动画
            Animation animation = AnimationUtils.loadAnimation(this.getContext(), R.anim.bottom_exit);
            //设置一下动画监听
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mControllerLinLayout.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            //设置动画
            mControllerLinLayout.startAnimation(animation);
        }
    }

    /**
     * 显示
     */
    void showControllerFunction(){
        mPlayImageView.setVisibility(View.VISIBLE);
        if(mControllerLinLayout.getVisibility()==View.INVISIBLE) {
            //加载一下动画
            Animation animation = AnimationUtils.loadAnimation(this.getContext(), R.anim.bottom_enter);
            //设置一下动画监听
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mControllerLinLayout.setVisibility(View.VISIBLE);
                    //2秒后再隐藏
                    mHandelr.sendEmptyMessageDelayed(1, 2000);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            //设置动画
            mControllerLinLayout.startAnimation(animation);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //第一步
        //设置监听
        mTextureView.setSurfaceTextureListener(mSurfaceTextureListener);

        mPlayImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPlayImageView.setImageResource(R.mipmap.video_pause);

                if (!isPrepare) {
                    //第三步
                    //开始加载
                    mediaPlayer.prepareAsync();
                    //显示一个进度条
                    mProgressBar.setVisibility(View.VISIBLE);
                    //隐藏开始播放按钮
                    hideControllerFunction();
                } else {
                    //第四步
                    if (mediaPlayer.isPlaying()) {
                        //移除所有的任务
                        mHandelr.removeCallbacksAndMessages(null);
                        //暂停
                        mediaPlayer.pause();
                        //显示开始播放按钮
                        showControllerFunction();
                        mPlayImageView.setImageResource(R.mipmap.video_play);
                    } else {
                        mediaPlayer.start();
                        //隐藏开始播放按钮
                        hideControllerFunction();
                        //开启定时
                        mHandelr.sendEmptyMessage(0);
                    }
                }
            }
        });
    }

    private Handler mHandelr = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            if (what == 0) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    //获取当前进度
                    mSeekBar.setProgress(currentPosition);
                    //格式化一下时间
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    String format = simpleDateFormat.format(new Date(currentPosition));
                    //设置当前时间
                    mCurrentTimeTextView.setText(format);

                    Message message = Message.obtain();
                    message.what = 0;
                    mHandelr.sendMessageDelayed(message, 100);


                }
            } else if (what == 1) {
                if(mediaPlayer.isPlaying()) {
                    //隐藏控制层
                    hideControllerFunction();
                }
            }
        }
    };

    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                //设置进度
                mediaPlayer.seekTo(seekBar.getProgress());
            }

        }
    };

    private TextureView.SurfaceTextureListener mSurfaceTextureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {

            //第二步
            //创建Surface
            surface1 = new Surface(surface);
            //创建控制器
            mediaPlayer = new MediaPlayer();
            //设置数据源
            Uri uri = Uri.parse("android.resource://" + mContext.getPackageName() + "/" + R.raw.video1);
            try {
                mediaPlayer.setDataSource(mContext, uri);
                //关联播放
                mediaPlayer.setSurface(surface1);
                //播放类型
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                //预加载监听
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        //开始播放
                        mediaPlayer.start();
                        isPrepare = true;
                        //隐藏进度
                        mProgressBar.setVisibility(View.GONE);
                        //设置总时长
                        int duration = mediaPlayer.getDuration();
                        mSeekBar.setMax(duration);
                        //格式化一下时间
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                        String format = simpleDateFormat.format(new Date(duration));
                        //设置一次 总时间
                        mTotalTimeTextView.setText(format);
                        //设置当前时间
                        mCurrentTimeTextView.setText("00:00");
                        //设置个计时器
                        Message message = Message.obtain();
                        message.what = 0;
                        mHandelr.sendMessageDelayed(message, 100);
                    }
                });
                //加载错误监听
                mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        return false;
                    }
                });
                //播放完成监听
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                    }
                });
                //缓存监听
                mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                    @Override
                    public void onBufferingUpdate(MediaPlayer mp, int percent) {

                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {

        }
    };
}