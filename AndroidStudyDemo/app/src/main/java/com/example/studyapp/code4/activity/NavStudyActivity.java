package com.example.studyapp.code4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studyapp.R;
import com.example.studyapp.code3.activity.RecyclerViewGridActivity;
import com.example.studyapp.code3.activity.RecyclerViewHorizontalActivity;
import com.example.studyapp.code3.activity.RecyclerViewStaggeredGridActivity;
import com.example.studyapp.code3.activity.RecyclerViewStudyListActivity;

/***
 * 底部导航 主目录
 */
public class NavStudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_study);

        findViewById(R.id.bt_nav_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavStudyActivity.this.startActivity(new Intent(NavStudyActivity.this, NavHomeActivity1.class));
            }
        });
        findViewById(R.id.bt_nav_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavStudyActivity.this.startActivity(new Intent(NavStudyActivity.this, NavHomeActivity2.class));
            }
        });
        findViewById(R.id.bt_nav_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavStudyActivity.this.startActivity(new Intent(NavStudyActivity.this, NavHomeActivity3.class));
            }
        });

    }
}