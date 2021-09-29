package com.example.studyapp.code4.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.studyapp.R;
import com.example.studyapp.common.fragment.ItemFragment;
import com.example.studyapp.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class NavBlankFragment1 extends Fragment {

    private FragmentHomeBinding fragmentHomeBinding;

    private String[] tabs = new String[]{
            "关注", "推荐", "热门"
    };


    public NavBlankFragment1() {
        Log.e("NavBlankFragment1", "构造函数");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater);
        Log.e("NavBlankFragment1", "onCreateView");
        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("NavBlankFragment1", "onActivityCreated");

        List<Fragment> fragmentList = new ArrayList<>();

        for (int i = 0; i < tabs.length; i++) {

            //创建Tab
            TabLayout.Tab tab = fragmentHomeBinding.tbHomeTop.newTab();
            //设置文本
            tab.setText(tabs[i]);
            //添加
            fragmentHomeBinding.tbHomeTop.addTab(tab);

            ItemFragment itemFragment = ItemFragment.newInstance(i);

            fragmentList.add(itemFragment);
        }

        //设置指示线不填充标签栏宽度
        fragmentHomeBinding.tbHomeTop.setTabIndicatorFullWidth(false);
        //设置Tablayout 显示模式
        //fixed是指固定个数， scrollable是使其可以横行滚动
        fragmentHomeBinding.tbHomeTop.setTabMode(TabLayout.MODE_SCROLLABLE);

        ViewPager viewPager = fragmentHomeBinding.vpHome;


        viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tabs[position];
            }
        });

        fragmentHomeBinding.tbHomeTop.setupWithViewPager(viewPager);

    }
}