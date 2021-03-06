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
        mProgressBar = view.findViewById(R.id.pb_loading);
        mPlayImageView = view.findViewById(R.id.iv_video_play);
        mSeekBar = view.findViewById(R.id.sb_progress);
        mContext = view.getContext();

        mCurrentTimeTextView = view.findViewById(R.id.tv_current_time);
        mTotalTimeTextView = view.findViewById(R.id.tv_total_time);
        mControllerFrameLayout = view.findViewById(R.id.fl_controller);
        mControllerLinLayout= view.findViewById(R.id.ll_progress);


        //??????????????????
        mSeekBar.setOnSeekBarChangeListener(mOnSeekBarChangeListener);
        mControllerFrameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //???????????????
                    if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                        //???????????????
                        showControllerFunction();
                        //2???????????????
                        mHandelr.sendEmptyMessageDelayed(1, 2000);
                    }
                }
                return true;
            }
        });
        return view;
    }

    /**
     * ??????
     */
    void hideControllerFunction(){
        mPlayImageView.setVisibility(View.GONE);
        if(mControllerLinLayout.getVisibility()==View.VISIBLE) {
            //??????????????????
            Animation animation = AnimationUtils.loadAnimation(this.getContext(), R.anim.bottom_exit);
            //????????????????????????
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
            //????????????
            mControllerLinLayout.startAnimation(animation);
        }
    }

    /**
     * ??????
     */
    void showControllerFunction(){
        mPlayImageView.setVisibility(View.VISIBLE);
        if(mControllerLinLayout.getVisibility()==View.INVISIBLE) {
            //??????????????????
            Animation animation = AnimationUtils.loadAnimation(this.getContext(), R.anim.bottom_enter);
            //????????????????????????
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mControllerLinLayout.setVisibility(View.VISIBLE);
                    //2???????????????
                    mHandelr.sendEmptyMessageDelayed(1, 2000);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            //????????????
            mControllerLinLayout.startAnimation(animation);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //?????????
        //????????????
        mTextureView.setSurfaceTextureListener(mSurfaceTextureListener);

        mPlayImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPlayImageView.setImageResource(R.mipmap.video_pause);

                if (!isPrepare) {
                    //?????????
                    //????????????
                    mediaPlayer.prepareAsync();
                    //?????????????????????
                    mProgressBar.setVisibility(View.VISIBLE);
                    //????????????????????????
                    hideControllerFunction();
                } else {
                    //?????????
                    if (mediaPlayer.isPlaying()) {
                        //?????????????????????
                        mHandelr.removeCallbacksAndMessages(null);
                        //??????
                        mediaPlayer.pause();
                        //????????????????????????
                        showControllerFunction();
                        mPlayImageView.setImageResource(R.mipmap.video_play);
                    } else {
                        mediaPlayer.start();
                        //????????????????????????
                        hideControllerFunction();
                        //????????????
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
                    //??????????????????
                    mSeekBar.setProgress(currentPosition);
                    //?????????????????????
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    String format = simpleDateFormat.format(new Date(currentPosition));
                    //??????????????????
                    mCurrentTimeTextView.setText(format);

                    Message message = Message.obtain();
                    message.what = 0;
                    mHandelr.sendMessageDelayed(message, 100);


                }
            } else if (what == 1) {
                if(mediaPlayer.isPlaying()) {
                    //???????????????
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
                //????????????
                mediaPlayer.seekTo(seekBar.getProgress());
            }

        }
    };

    private TextureView.SurfaceTextureListener mSurfaceTextureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {

            //?????????
            //??????Surface
            surface1 = new Surface(surface);
            //???????????????
            mediaPlayer = new MediaPlayer();
            //???????????????
            Uri uri = Uri.parse("android.resource://" + mContext.getPackageName() + "/" + R.raw.video1);
            try {
                mediaPlayer.setDataSource(mContext, uri);
                //????????????
                mediaPlayer.setSurface(surface1);
                //????????????
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                //???????????????
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        //????????????
                        mediaPlayer.start();
                        isPrepare = true;
                        //????????????
                        mProgressBar.setVisibility(View.GONE);
                        //???????????????
                        int duration = mediaPlayer.getDuration();
                        mSeekBar.setMax(duration);
                        //?????????????????????
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                        String format = simpleDateFormat.format(new Date(duration));
                        //???????????? ?????????
                        mTotalTimeTextView.setText(format);
                        //??????????????????
                        mCurrentTimeTextView.setText("00:00");
                        //??????????????????
                        Message message = Message.obtain();
                        message.what = 0;
                        mHandelr.sendMessageDelayed(message, 100);
                    }
                });
                //??????????????????
                mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        return false;
                    }
                });
                //??????????????????
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                    }
                });
                //????????????
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