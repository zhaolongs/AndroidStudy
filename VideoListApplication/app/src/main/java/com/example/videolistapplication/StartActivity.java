package com.example.videolistapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.videolistapplication.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {

    private ActivityStartBinding activityStartBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityStartBinding = ActivityStartBinding.inflate(this.getLayoutInflater());
        setContentView(activityStartBinding.getRoot());

        activityStartBinding.textureViewBaseUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,MainActivity.class);
                intent.putExtra("pageFlag",1);
                StartActivity.this.startActivity(intent);
            }
        });
        activityStartBinding.textureViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,MainActivity.class);
                intent.putExtra("pageFlag",2);
                StartActivity.this.startActivity(intent);
            }
        });
    }
}