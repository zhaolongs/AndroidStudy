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

/**
 * 通话记录中心 ListView
 */
public class ListViewStudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("程序启动");
        //第一步 布局文件中 引用 ListView
        setContentView(R.layout.activity_list_view_study);
        //第二步 获取 ListView
        ListView listView = findViewById(R.id.listview);
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
        PhoneListAdapter phoneListAdapter = new PhoneListAdapter(phoneBeanList,this);

        //第五步 关联 ListView
        listView.setAdapter(phoneListAdapter);

        //第六步 设置点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             *
             * @param parent
             * @param view
             * @param position  位置
             * @param id   对应 Adapter 中的 getItemId
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("ListView","点击了ListView "+position);

                //获取列表对应的数据
                PhoneBean phoneBean = phoneBeanList.get(position);
                //获取电话号
                String phone = phoneBean.phone;



                /**
                 * 参数一 当前 Activity 实例
                 * 参数二 将要打开 的页面
                 */
                Intent intent = new Intent(ListViewStudyActivity.this,PhoneDetailsActivity.class);

                //参数
                intent.putExtra("phone",phone);

                //传递对象
                intent.putExtra("people",phoneBean);

                //开启一个新的Activity
                ListViewStudyActivity.this.startActivity(intent);

            }
        });
    }
}