package com.example.studyapp.code00.activity.progressbar;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.studyapp.databinding.ActivityHorizontalProgressBinding;

/***
 * 水平 ProgressBar 的实际使用
 */
public class HorizontalProgressBarctivity extends AppCompatActivity {

    private ActivityHorizontalProgressBinding activityContentProgressBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //View Binding 绑定 Activity
        activityContentProgressBinding = ActivityHorizontalProgressBinding.inflate(getLayoutInflater());
        setContentView(activityContentProgressBinding.getRoot());






    }
}