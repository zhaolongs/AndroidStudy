package com.example.studyapp.code3.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.studyapp.R;

/***
 * RecyclerView 主目录
 */
public class RecyclerViewStudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_study);

        findViewById(R.id.bt_recy_list1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerViewStudyActivity.this.startActivity(new Intent(RecyclerViewStudyActivity.this, RecyclerViewStudyListActivity.class));
            }
        });
        findViewById(R.id.bt_recy_list2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerViewStudyActivity.this.startActivity(new Intent(RecyclerViewStudyActivity.this, RecyclerViewHorizontalActivity.class));
            }
        });
        findViewById(R.id.bt_recy_list3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerViewStudyActivity.this.startActivity(new Intent(RecyclerViewStudyActivity.this, RecyclerViewGridActivity.class));
            }
        });
        findViewById(R.id.bt_recy_list4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerViewStudyActivity.this.startActivity(new Intent(RecyclerViewStudyActivity.this, RecyclerViewStaggeredGridActivity.class));
            }
        });



    }
}