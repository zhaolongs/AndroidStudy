package com.example.studyapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.studyapp.R;
import com.example.studyapp.adapter.PhoneListAdapter;
import com.example.studyapp.bean.PhoneBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("程序启动");
        //第一步 布局文件中 引用 ListView
        setContentView(R.layout.activity_list1);
        //第二步 获取 ListView

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


        //第五步 关联 ListView


        //第六步 设置点击事件





    }
}