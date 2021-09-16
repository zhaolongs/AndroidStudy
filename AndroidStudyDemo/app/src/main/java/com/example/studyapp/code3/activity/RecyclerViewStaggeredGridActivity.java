package com.example.studyapp.code3.activity;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.studyapp.R;
import com.example.studyapp.code2.activity.ListViewStudyActivity;
import com.example.studyapp.code2.activity.PhoneDetailsActivity;
import com.example.studyapp.code2.bean.PhoneBean;
import com.example.studyapp.code3.adapter.PhoneRecyImgAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 04 RecyclerView 瀑布流 数据展示
 */
public class RecyclerViewStaggeredGridActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<PhoneBean> phoneBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //第一步 布局文件中 引用 RecyclerView
        setContentView(R.layout.activity_recycler_view_study_list);
        //第二步 获取 RecyclerView
        recyclerView = findViewById(R.id.recy_use);
        //第三步 数据
        phoneBeanList = new ArrayList<>();
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
        PhoneRecyImgAdapter phoneListAdapter = new PhoneRecyImgAdapter(phoneBeanList, this, true);

        //设置子Item 点击事件回调
        phoneListAdapter.setmPhoneRecyInterface(new PhoneRecyImgAdapter.PhoneRecyInterface() {
            @Override
            public void onItemClick(PhoneBean phoneBean, int position) {

                String phone = phoneBean.phone;
                /**
                 * 参数一 当前 Activity 实例
                 * 参数二 将要打开 的页面
                 */
                Intent intent = new Intent(RecyclerViewStaggeredGridActivity.this, PhoneDetailsActivity.class);

                //参数
                intent.putExtra("phone",phone);

                //传递对象
                intent.putExtra("people",phoneBean);

                //开启一个新的Activity
                RecyclerViewStaggeredGridActivity.this.startActivity(intent);
            }
        });

        //多了一步 设置为 列表显示

        //线性
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //设置水平方向
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        //来个宫格
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);

        //来个瀑布流
        //参数一 列数
        //参数二 方向
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);


        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        //第五步 关联 ListView
        recyclerView.setAdapter(phoneListAdapter);


        ImageView topImageView = findViewById(R.id.iv_top);
        ImageView toTopImageView = findViewById(R.id.iv_to_top);
        ImageView toNextImageView = findViewById(R.id.iv_to_next);

        topImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollTopFunction();
            }
        });

        toTopImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollToTopFunction();
            }
        });
        toNextImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollToNextFunction();
            }
        });

        //设置滑动监听
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /**
             * 滑动状态改变
             * @param recyclerView
             * @param newState
             */
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                    //滑动停止
                    Log.e("RecyclerView","滑动停止");
                }else  if(newState==RecyclerView.SCROLL_STATE_SETTLING){
                    //滑动
                    Log.e("RecyclerView","自动开始滑动");
                }else{
                    Log.e("RecyclerView","手动开始滑动");
                }
            }

            /**
             * 滑动时的回调
             * @param recyclerView
             * @param dx
             * @param dy
             */
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                /**
                 * 竖直方向 上下滑动
                 * dy > 0 时为向上滚动
                 * dy < 0 时为向下滚动
                 */

                /**
                 * 水平方向 左右滑动
                 * dx > 0 时为向右滚动
                 * dx < 0 时为向左滚动
                 */
            }
        });



    }

    /**
     * 向下一个一个滑动
     */
    private void scrollToNextFunction() {
        //获取当前显示的View 的数据
        int childCount = recyclerView.getChildCount();
        //获取最后一具 Item 对应的View
        View childAt = recyclerView.getChildAt(childCount - 1);
        //获取当前军舰中显示的最后一个 Item 的位置  Postion
        int childLayoutPosition = recyclerView.getChildLayoutPosition(childAt);
        //如果不是最后一个就向下滑动
        if(childLayoutPosition<phoneBeanList.size()-2){
            recyclerView.smoothScrollToPosition(childLayoutPosition+1);
        }else{
            Toast.makeText(this, "已滑动到底部了", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 向上一个一个滑动
     */
    private void scrollToTopFunction() {

        //获取当前视图中显示的第一个 Item 的位置 Position
        int childLayoutPosition = recyclerView.getChildLayoutPosition(recyclerView.getChildAt(0));
        if (childLayoutPosition > 0) {
            recyclerView.smoothScrollToPosition(childLayoutPosition - 1);
        } else {
            Toast.makeText(this, "已滑动到顶部了", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * 滑动到顶部 置顶
     */
    private void scrollTopFunction() {
        recyclerView.smoothScrollToPosition(0);
    }


}