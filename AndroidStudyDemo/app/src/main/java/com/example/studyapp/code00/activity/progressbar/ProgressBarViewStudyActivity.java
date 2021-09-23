package com.example.studyapp.code00.activity.progressbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studyapp.databinding.ActivityProgressViewStudyBinding;

/***
 *  ProgressBar 主目录
 */
public class ProgressBarViewStudyActivity extends AppCompatActivity {
    private ActivityProgressViewStudyBinding progressViewStudyBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressViewStudyBinding = ActivityProgressViewStudyBinding.inflate(getLayoutInflater());
        setContentView(progressViewStudyBinding.getRoot());

        progressViewStudyBinding.btProgressContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressBarViewStudyActivity.this.startActivity(new Intent(ProgressBarViewStudyActivity.this, HorizontalProgressBarctivity.class));
            }
        });


    }
}