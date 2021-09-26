package com.example.studyapp.code4.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studyapp.R;
import com.example.studyapp.databinding.ActivityHome2Binding;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Random;


public class NavHomeActivity2 extends AppCompatActivity {

    private ActivityHome2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHome2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //获取 BottomNavigationView
        BottomNavigationView navView = findViewById(R.id.nav_view);
        //navView.setItemIconTintList(null);
        //获取 Fragment对应的配置
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

        //Fragment 与 BottomNavigationView 的绑定
        //加载 Fragment 控制
        //Fragment的跳转与退出、动画、监听当前Fragment信息 Fragment
        //获取方法
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home2);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //BottomNavigationView 与 Fragment 绑定联动
        NavigationUI.setupWithNavController(binding.navView, navController);


        //添加小圆点
        //获取每个菜单选项
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navView.getChildAt(0);

        //获取菜单选项中的Tab
        BottomNavigationItemView tabView = (BottomNavigationItemView) menuView.getChildAt(0);

        //加载小圆点
        View textView = LayoutInflater.from(this).inflate(R.layout.nav_tips_layout, menuView, false);

        //设置数据
        TextView tipsTextView = textView.findViewById(R.id.tv_nav_tips);
        //数据 String 类型
        tipsTextView.setText("34");
        //Tab中添加小圆点
        tabView.addView(textView);
    }





}