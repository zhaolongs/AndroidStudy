package com.example.studyapp.common.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.studyapp.R;
import com.example.studyapp.code4.fragment.NavBlankFragment1;
import com.example.studyapp.code4.fragment.NavBlankFragment2;
import com.example.studyapp.code4.fragment.NavBlankFragment3;
import com.example.studyapp.code4.fragment.NavBlankFragment4;
import com.example.studyapp.databinding.ActivityHome3Binding;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * 应用的首页面
 */
public class HomeActivity extends AppCompatActivity {

    private ActivityHome3Binding activityHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定 ViewBind
        activityHomeBinding = ActivityHome3Binding.inflate(getLayoutInflater());
        
        setContentView(activityHomeBinding.getRoot());


        activityHomeBinding.navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                onSelectClick(item.getItemId());
                return true;
            }
        });


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


    public void onSelectClick(int id) {
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
        switch (id) {
            case R.id.navigation_home:
                //切换页面
                if (nav1Fragment == null) {
                    //创建
                    nav1Fragment = new NavBlankFragment1();
                    //替换当前视图
                    fragmentTransaction.add(R.id.fl_home_content, nav1Fragment, "nav_1");
                }
                //显示当前
                fragmentTransaction.show(nav1Fragment);

                break;
            case R.id.navigation_message:


                if (nav2Fragment == null) {
                    //创建
                    nav2Fragment = new NavBlankFragment2();
                    //替换当前视图
                    fragmentTransaction.add(R.id.fl_home_content, nav2Fragment, "nav_2");
                }
                //显示当前
                fragmentTransaction.show(nav2Fragment);

                break;
            case R.id.navigation_dashboard:

                //获取 FragmentManager 管理者 低版本兼容

                if (nav3Fragment == null) {
                    //创建
                    nav3Fragment = new NavBlankFragment3();
                    //替换当前视图
                    fragmentTransaction.add(R.id.fl_home_content, nav3Fragment, "nav_3");
                }
                //显示当前
                fragmentTransaction.show(nav3Fragment);


                break;
            case R.id.navigation_notifications:

                if (nav4Fragment == null) {
                    //创建
                    nav4Fragment = new NavBlankFragment4();
                    //替换当前视图
                    fragmentTransaction.add(R.id.fl_home_content, nav4Fragment, "nav_4");
                }
                //显示当前
                fragmentTransaction.show(nav4Fragment);

                break;
        }

        //提交视图
        fragmentTransaction.commit();
    }
}