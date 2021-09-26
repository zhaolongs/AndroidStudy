package com.example.studyapp.code4.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.studyapp.R;
import com.example.studyapp.code4.fragment.NavBlankFragment1;
import com.example.studyapp.code4.fragment.NavBlankFragment2;
import com.example.studyapp.code4.fragment.NavBlankFragment3;
import com.example.studyapp.code4.fragment.NavBlankFragment4;
import com.example.studyapp.common.fragment.BlankFragment1;
import com.example.studyapp.common.fragment.BlankFragment2;
import com.example.studyapp.common.fragment.BlankFragment3;
import com.example.studyapp.common.fragment.BlankFragment4;
import com.example.studyapp.databinding.ActivityHomeBinding;

/**
 * 应用的首页面
 */
public class NavHomeActivity1 extends AppCompatActivity implements View.OnClickListener {

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

        //默认显示第一个
        //获取 FragmentManager 管理者 低版本兼容
        FragmentManager fragmentManager = getSupportFragmentManager();
        //开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //创建
        Fragment nav1Fragment = new NavBlankFragment1();
        //替换当前视图
        fragmentTransaction.add(R.id.fl_home_content, nav1Fragment, "nav_1");
        //显示当前
        fragmentTransaction.show(nav1Fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onClick(View v) {
        //获取 FragmentManager 管理者 低版本兼容
        FragmentManager fragmentManager = getSupportFragmentManager();
        //开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //隐藏所有的Fragment
        Fragment nav1Fragment = fragmentManager.findFragmentByTag("nav_1");
        Fragment nav2Fragment = fragmentManager.findFragmentByTag("nav_2");
        Fragment nav3Fragment = fragmentManager.findFragmentByTag("nav_3");
        Fragment nav4Fragment = fragmentManager.findFragmentByTag("nav_4");
        if (nav1Fragment != null) {
            fragmentTransaction.hide(nav1Fragment);
        }
        if (nav2Fragment != null) {
            fragmentTransaction.hide(nav2Fragment);
        }
        if (nav3Fragment != null) {
            fragmentTransaction.hide(nav3Fragment);
        }
        if (nav4Fragment != null) {
            fragmentTransaction.hide(nav4Fragment);
        }
        switch (v.getId()) {
            case R.id.fl_nav_1:
                //切换页面
                if (nav1Fragment == null) {
                    //创建
                    nav1Fragment = new NavBlankFragment1();
                    //替换当前视图
                    fragmentTransaction.add(R.id.fl_home_content, nav1Fragment, "nav_1");
                }
                //显示当前
                fragmentTransaction.show(nav1Fragment);

                //选中状态
                activityHomeBinding.ivNav1.setImageResource(R.mipmap.nav_32);
                activityHomeBinding.tvNav1.setTextColor(ContextCompat.getColor(this, R.color.nav_select));

                //其他的为未选中
                activityHomeBinding.ivNav2.setImageResource(R.mipmap.nav_21);
                activityHomeBinding.tvNav2.setTextColor(ContextCompat.getColor(this, R.color.nav_normal));

                activityHomeBinding.ivNav3.setImageResource(R.mipmap.nav_11);
                activityHomeBinding.tvNav3.setTextColor(ContextCompat.getColor(this, R.color.nav_normal));

                activityHomeBinding.ivNav4.setImageResource(R.mipmap.nav_41);
                activityHomeBinding.tvNav4.setTextColor(ContextCompat.getColor(this, R.color.nav_normal));
                break;
            case R.id.fl_nav_2:


                if (nav2Fragment == null) {
                    //创建
                    nav2Fragment = new NavBlankFragment2();
                    //替换当前视图
                    fragmentTransaction.add(R.id.fl_home_content, nav2Fragment, "nav_2");
                }
                //显示当前
                fragmentTransaction.show(nav2Fragment);

                activityHomeBinding.ivNav1.setImageResource(R.mipmap.nav_31);
                activityHomeBinding.tvNav1.setTextColor(ContextCompat.getColor(this, R.color.nav_normal));

                //其他的为未选中
                activityHomeBinding.ivNav2.setImageResource(R.mipmap.nav_22);
                activityHomeBinding.tvNav2.setTextColor(ContextCompat.getColor(this, R.color.nav_select));

                activityHomeBinding.ivNav3.setImageResource(R.mipmap.nav_11);
                activityHomeBinding.tvNav3.setTextColor(ContextCompat.getColor(this, R.color.nav_normal));

                activityHomeBinding.ivNav4.setImageResource(R.mipmap.nav_41);
                activityHomeBinding.tvNav4.setTextColor(ContextCompat.getColor(this, R.color.nav_normal));
                break;
            case R.id.fl_nav_3:


                //获取 FragmentManager 管理者 低版本兼容

                if (nav3Fragment == null) {
                    //创建
                    nav3Fragment = new NavBlankFragment3();
                    //替换当前视图
                    fragmentTransaction.add(R.id.fl_home_content, nav3Fragment, "nav_3");
                }
                //显示当前
                fragmentTransaction.show(nav3Fragment);

                activityHomeBinding.ivNav1.setImageResource(R.mipmap.nav_31);
                activityHomeBinding.tvNav1.setTextColor(ContextCompat.getColor(this, R.color.nav_normal));

                //其他的为未选中
                activityHomeBinding.ivNav2.setImageResource(R.mipmap.nav_21);
                activityHomeBinding.tvNav2.setTextColor(ContextCompat.getColor(this, R.color.nav_normal));

                activityHomeBinding.ivNav3.setImageResource(R.mipmap.nav_12);
                activityHomeBinding.tvNav3.setTextColor(ContextCompat.getColor(this, R.color.nav_select));

                activityHomeBinding.ivNav4.setImageResource(R.mipmap.nav_41);
                activityHomeBinding.tvNav4.setTextColor(ContextCompat.getColor(this, R.color.nav_normal));
                break;
            case R.id.fl_nav_4:

                if (nav4Fragment == null) {
                    //创建
                    nav4Fragment = new NavBlankFragment4();
                    //替换当前视图
                    fragmentTransaction.add(R.id.fl_home_content, nav4Fragment, "nav_4");
                }
                //显示当前
                fragmentTransaction.show(nav4Fragment);

                activityHomeBinding.ivNav1.setImageResource(R.mipmap.nav_31);
                activityHomeBinding.tvNav1.setTextColor(ContextCompat.getColor(this, R.color.nav_normal));

                //其他的为未选中
                activityHomeBinding.ivNav2.setImageResource(R.mipmap.nav_21);
                activityHomeBinding.tvNav2.setTextColor(ContextCompat.getColor(this, R.color.nav_normal));

                activityHomeBinding.ivNav3.setImageResource(R.mipmap.nav_11);
                activityHomeBinding.tvNav3.setTextColor(ContextCompat.getColor(this, R.color.nav_normal));

                activityHomeBinding.ivNav4.setImageResource(R.mipmap.nav_42);
                activityHomeBinding.tvNav4.setTextColor(ContextCompat.getColor(this, R.color.nav_select));
                break;
        }

        //提交视图
        fragmentTransaction.commit();
    }
}