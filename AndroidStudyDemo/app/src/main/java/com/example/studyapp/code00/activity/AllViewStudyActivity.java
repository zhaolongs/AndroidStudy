package com.example.studyapp.code00.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studyapp.code00.activity.progressbar.ProgressBarViewStudyActivity;
import com.example.studyapp.databinding.ActivityAllViewStudyBinding;

/***
 * RecyclerView 主目录
 */
public class AllViewStudyActivity extends AppCompatActivity {

    private ActivityAllViewStudyBinding allViewStudyBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        allViewStudyBinding = ActivityAllViewStudyBinding.inflate(getLayoutInflater());
        setContentView(allViewStudyBinding.getRoot());
        allViewStudyBinding.tvProgressView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllViewStudyActivity.this.startActivity(new Intent(AllViewStudyActivity.this, ProgressBarViewStudyActivity.class));
            }
        });





    }
}