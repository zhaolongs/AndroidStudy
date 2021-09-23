package com.example.studyapp.common.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.studyapp.R;
import com.example.studyapp.common.fragment.BlankFragment1;
import com.example.studyapp.common.fragment.BlankFragment2;
import com.example.studyapp.common.fragment.BlankFragment3;
import com.example.studyapp.databinding.ActivityHomeBinding;

/**
 * 应用的首页面
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityHomeBinding activityHomeBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定 ViewBind
        activityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(activityHomeBinding.getRoot());


        activityHomeBinding.flNav1.setOnClickListener(this);
        activityHomeBinding.flNav2.setOnClickListener(this);
        activityHomeBinding.flNav3.setOnClickListener(this);
        activityHomeBinding.flNav4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fl_nav_1:
                //切换页面
                //获取 FragmentManager 管理者 低版本兼容
                FragmentManager fragmentManager = getSupportFragmentManager();
                //开启事务
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //替换当前视图
                fragmentTransaction.replace(R.id.fl_home_content, new BlankFragment1());
                //提交视图
                fragmentTransaction.commit();

                //选中状态
                activityHomeBinding.ivNav1.setImageResource(R.mipmap.nav_32);
                activityHomeBinding.tvNav1.setTextColor(ContextCompat.getColor(this,R.color.nav_select));

                //其他的为未选中
                activityHomeBinding.ivNav2.setImageResource(R.mipmap.nav_21);
                activityHomeBinding.tvNav2.setTextColor(ContextCompat.getColor(this,R.color.nav_normal));

                activityHomeBinding.ivNav3.setImageResource(R.mipmap.nav_11);
                activityHomeBinding.tvNav3.setTextColor(ContextCompat.getColor(this,R.color.nav_normal));

                activityHomeBinding.ivNav4.setImageResource(R.mipmap.nav_41);
                activityHomeBinding.tvNav4.setTextColor(ContextCompat.getColor(this,R.color.nav_normal));
                break;
            case R.id.fl_nav_2:

                //获取 FragmentManager 管理者 低版本兼容
                FragmentManager fragmentManager2 = getSupportFragmentManager();
                //开启事务
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                //替换当前视图
                fragmentTransaction2.replace(R.id.fl_home_content, new BlankFragment2());
                //提交视图
                fragmentTransaction2.commit();

                activityHomeBinding.ivNav1.setImageResource(R.mipmap.nav_31);
                activityHomeBinding.tvNav1.setTextColor(ContextCompat.getColor(this,R.color.nav_normal));

                //其他的为未选中
                activityHomeBinding.ivNav2.setImageResource(R.mipmap.nav_22);
                activityHomeBinding.tvNav2.setTextColor(ContextCompat.getColor(this,R.color.nav_select));

                activityHomeBinding.ivNav3.setImageResource(R.mipmap.nav_11);
                activityHomeBinding.tvNav3.setTextColor(ContextCompat.getColor(this,R.color.nav_normal));

                activityHomeBinding.ivNav4.setImageResource(R.mipmap.nav_41);
                activityHomeBinding.tvNav4.setTextColor(ContextCompat.getColor(this,R.color.nav_normal));
                break;
            case R.id.fl_nav_3:


                //获取 FragmentManager 管理者 低版本兼容
                FragmentManager fragmentManager3 = getSupportFragmentManager();
                //开启事务
                FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                //替换当前视图
                fragmentTransaction3.replace(R.id.fl_home_content, new BlankFragment3());
                //提交视图
                fragmentTransaction3.commit();

                activityHomeBinding.ivNav1.setImageResource(R.mipmap.nav_31);
                activityHomeBinding.tvNav1.setTextColor(ContextCompat.getColor(this,R.color.nav_normal));

                //其他的为未选中
                activityHomeBinding.ivNav2.setImageResource(R.mipmap.nav_21);
                activityHomeBinding.tvNav2.setTextColor(ContextCompat.getColor(this,R.color.nav_normal));

                activityHomeBinding.ivNav3.setImageResource(R.mipmap.nav_12);
                activityHomeBinding.tvNav3.setTextColor(ContextCompat.getColor(this,R.color.nav_select));

                activityHomeBinding.ivNav4.setImageResource(R.mipmap.nav_41);
                activityHomeBinding.tvNav4.setTextColor(ContextCompat.getColor(this,R.color.nav_normal));
                break;
            case R.id.fl_nav_4:

                //获取 FragmentManager 管理者 低版本兼容
                FragmentManager fragmentManager4 = getSupportFragmentManager();
                //开启事务
                FragmentTransaction fragmentTransaction4 = fragmentManager4.beginTransaction();
                //替换当前视图
                fragmentTransaction4.replace(R.id.fl_home_content, new BlankFragment3());
                //提交视图
                fragmentTransaction4.commit();

                activityHomeBinding.ivNav1.setImageResource(R.mipmap.nav_31);
                activityHomeBinding.tvNav1.setTextColor(ContextCompat.getColor(this,R.color.nav_normal));

                //其他的为未选中
                activityHomeBinding.ivNav2.setImageResource(R.mipmap.nav_21);
                activityHomeBinding.tvNav2.setTextColor(ContextCompat.getColor(this,R.color.nav_normal));

                activityHomeBinding.ivNav3.setImageResource(R.mipmap.nav_11);
                activityHomeBinding.tvNav3.setTextColor(ContextCompat.getColor(this,R.color.nav_normal));

                activityHomeBinding.ivNav4.setImageResource(R.mipmap.nav_42);
                activityHomeBinding.tvNav4.setTextColor(ContextCompat.getColor(this,R.color.nav_select));
                break;
        }
    }
}