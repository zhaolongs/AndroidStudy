package com.example.videolistapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ClipData;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //默认显示第一个
        //获取 FragmentManager 管理者 低版本兼容
        FragmentManager fragmentManager = getSupportFragmentManager();
        //开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //创建
        Fragment nav1Fragment = ItemFragment.newInstance(10);
        //替换当前视图
        fragmentTransaction.add(R.id.fl_home_content, nav1Fragment, "nav_1");
        //显示当前
        fragmentTransaction.show(nav1Fragment);
        fragmentTransaction.commit();
    }
}