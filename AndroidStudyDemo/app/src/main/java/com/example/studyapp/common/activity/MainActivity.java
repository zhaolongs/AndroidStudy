package com.example.studyapp.common.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.studyapp.R;
import com.example.studyapp.code2.activity.ListViewStudyActivity;
import com.example.studyapp.code3.activity.RecyclerViewStudyActivity;
import com.example.studyapp.common.acapter.CatalogueListAdapter;
import com.example.studyapp.common.bean.CatalogueBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<CatalogueBean> mCatalogueBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("程序启动");
        //第一步 布局文件中 引用 ListView
        setContentView(R.layout.activity_list1);
        //第二步 获取 ListView
        ListView listView = findViewById(R.id.listview);
        //第三步 数据
        mCatalogueBeanList = new ArrayList<>();

        addPageDataFunction();


        //第四步 创建适配器
        CatalogueListAdapter phoneListAdapter = new CatalogueListAdapter(mCatalogueBeanList, this);

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

                Log.d("ListView", "点击了ListView " + position);

                //获取列表对应的数据
                CatalogueBean catalogueBean = mCatalogueBeanList.get(position);
                /**
                 * 参数一 当前 Activity 实例
                 * 参数二 将要打开 的页面
                 */
                Intent intent = new Intent(MainActivity.this, catalogueBean.aClass);
                //开启一个新的Activity
                MainActivity.this.startActivity(intent);

            }
        });
    }

    /**
     * 添加数据
     */
    private void addPageDataFunction() {

        CatalogueBean  listBean = new CatalogueBean("通话记录 - ListView ", ListViewStudyActivity.class);
        CatalogueBean recyBean = new CatalogueBean("通话记录 - RecyclerView ", RecyclerViewStudyActivity.class);

        mCatalogueBeanList.add(listBean);
        mCatalogueBeanList.add(recyBean);
    }
}