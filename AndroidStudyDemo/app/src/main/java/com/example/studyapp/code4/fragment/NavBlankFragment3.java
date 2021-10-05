package com.example.studyapp.code4.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.studyapp.R;
import com.example.studyapp.code00.activity.AllViewStudyActivity;
import com.example.studyapp.code2.activity.ListViewStudyActivity;
import com.example.studyapp.code3.activity.RecyclerViewStudyActivity;
import com.example.studyapp.code4.activity.NavStudyActivity;
import com.example.studyapp.common.acapter.CatalogueListAdapter;
import com.example.studyapp.common.activity.MainActivity;
import com.example.studyapp.common.bean.CatalogueBean;

import java.util.ArrayList;
import java.util.List;


public class NavBlankFragment3 extends Fragment {

    private ListView listView;

    public NavBlankFragment3() {

    }
    private List<CatalogueBean> mCatalogueBeanList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //第一步 布局文件中 引用 ListView
        View inflate = inflater.inflate(R.layout.fragment_blank3, container, false);
        //第二步 获取 ListView
        listView = inflate.findViewById(R.id.listview);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //第三步 数据
        mCatalogueBeanList = new ArrayList<>();
        addPageDataFunction();


        //第四步 创建适配器
        CatalogueListAdapter phoneListAdapter = new CatalogueListAdapter(mCatalogueBeanList, NavBlankFragment3.this.getActivity());

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
                Intent intent = new Intent(NavBlankFragment3.this.getActivity(), catalogueBean.aClass);
                //开启一个新的Activity
                NavBlankFragment3.this.getActivity().startActivity(intent);

            }
        });
    }

    /**
     * 添加数据
     */
    private void addPageDataFunction() {

        CatalogueBean  listBean = new CatalogueBean("通话记录 - ListView ", ListViewStudyActivity.class);
        CatalogueBean recyBean = new CatalogueBean("通话记录 - RecyclerView ", RecyclerViewStudyActivity.class);
        CatalogueBean navBean = new CatalogueBean("底部导航栏 - RecyclerView ", NavStudyActivity.class);


        CatalogueBean allViewBean = new CatalogueBean("组件专题  ", AllViewStudyActivity.class);


        mCatalogueBeanList.add(listBean);
        mCatalogueBeanList.add(recyBean);
        mCatalogueBeanList.add(navBean);
        mCatalogueBeanList.add(allViewBean);
    }
}