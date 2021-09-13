package com.example.studyapp.code3.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import com.example.studyapp.R;
import com.example.studyapp.code2.adapter.PhoneListAdapter;
import com.example.studyapp.code2.bean.PhoneBean;
import com.example.studyapp.code3.adapter.PhoneRecyAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecyclerViewStudyListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //第一步 布局文件中 引用 RecyclerView
        setContentView(R.layout.activity_recycler_view_study_list);
        //第二步 获取 RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recy_use);
        //第三步 数据
        List<PhoneBean> phoneBeanList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            PhoneBean phoneBean = new PhoneBean();
            phoneBean.userName="早起的年轻人 "+i;
            phoneBean.userAddress="中图 北京";
            phoneBean.phone="133454545";
            phoneBean.createTime = new Date().getTime();
            phoneBeanList.add(phoneBean);
        }
        //第四步 创建适配器
        PhoneRecyAdapter phoneListAdapter = new PhoneRecyAdapter(phoneBeanList,this);

        //多了一步 设置为 列表显示
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //第五步 关联 ListView
        recyclerView.setAdapter(phoneListAdapter);
    }
}