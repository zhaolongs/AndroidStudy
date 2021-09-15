package com.example.studyapp.code3.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyapp.R;
import com.example.studyapp.code2.bean.PhoneBean;
import com.example.studyapp.code3.adapter.PhoneRecyImgAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 03 RecyclerView 九宫格数据展示
 */
public class RecyclerViewGridActivity extends AppCompatActivity {

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
            phoneBean.userName = "早起的年轻人 " + i;
            phoneBean.userAddress = "中图 北京";
            phoneBean.phone = "133454545";
            if (i % 3 == 0) {
                phoneBean.image = R.mipmap.banner01;
            } else if (i % 3 == 1) {
                phoneBean.image = R.mipmap.banner02;
            } else {
                phoneBean.image = R.mipmap.banner03;
            }
            phoneBean.createTime = new Date().getTime();
            phoneBeanList.add(phoneBean);
        }
        //第四步 创建适配器
        PhoneRecyImgAdapter phoneListAdapter = new PhoneRecyImgAdapter(phoneBeanList, this, false);

        //多了一步 设置为 列表显示

        //线性
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //设置水平方向
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        //来个宫格

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3) {
            @Override
            public boolean canScrollHorizontally() {
                //false  禁止水平左右滑动
                return super.canScrollHorizontally();
            }

            @Override
            public boolean canScrollVertically() {
                //false 禁止竖直方向的滑动
                return super.canScrollVertically();
            }
        };

        //个性化配置
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //每行显示的列数 = spanCount /spanSize
                if (position == 0) {
                    //第一行显示 1列  3/3
                    return 3;
                }
                //其他的显示 3 列   3/1 = 3
                return 1;
            }
        });

        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(gridLayoutManager);

        //第五步 关联 ListView
        recyclerView.setAdapter(phoneListAdapter);
    }
}