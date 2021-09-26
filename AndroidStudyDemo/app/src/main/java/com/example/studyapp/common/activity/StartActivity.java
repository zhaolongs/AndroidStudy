package com.example.studyapp.common.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.studyapp.code4.activity.NavHomeActivity2;
import com.example.studyapp.code4.activity.NavHomeActivity3;
import com.example.studyapp.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {

    private ActivityStartBinding activityStartBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_start);
//        TextView centerTextView = findViewById(R.id.tv_welcome);

        activityStartBinding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(activityStartBinding.getRoot());

        activityStartBinding.tvWelcome.setText("今天天气不错");

        //首次启动任务
        mHandler.postDelayed(mRunnable,10);
    }

    //倒计时部时间  毫秒
    private  int mTotalTime = 1000;
    //当前的时间
    private  int mCurrentTime = 0;

    private  Handler mHandler = new Handler(Looper.getMainLooper());

    //定义一个计时任务
    private  Runnable mRunnable = new Runnable() {
        @Override
        public void run() {

            mCurrentTime += 1000;
            //计算剩余的秒数
            int current = (mTotalTime-mCurrentTime)/1000;
            //设置时间显示
            activityStartBinding.tvTime.setText(current+"s");
            if(mCurrentTime<mTotalTime){
                //继续执行
                mHandler.postDelayed(mRunnable,1000);
            }else{
                //跳转页面
                //两类应用 一类是强制登录的 一类是非强制登录的
                //非强制登录
                //直接跳转首页面
                Intent intent = new Intent(StartActivity.this, HomeActivity.class);
                StartActivity.this.startActivity(intent);
                //关闭当前页面
                finish();


            }

        }
    };


}