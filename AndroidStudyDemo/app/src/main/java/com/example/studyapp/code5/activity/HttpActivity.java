package com.example.studyapp.code5.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.studyapp.databinding.ActivityHttpBinding;

public class HttpActivity extends AppCompatActivity {

    private ActivityHttpBinding activityHttpBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHttpBinding = ActivityHttpBinding.inflate(this.getLayoutInflater());
        setContentView(activityHttpBinding.getRoot());
        activityHttpBinding.tvGetUserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpActivity.this.startActivity(new Intent(HttpActivity.this, UserListActivity.class));
            }
        });

        activityHttpBinding.tvPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpActivity.this.startActivity(new Intent(HttpActivity.this, HttpPostActivity.class));
            }
        });
    }


}