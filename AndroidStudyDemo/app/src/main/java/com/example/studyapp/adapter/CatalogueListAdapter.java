package com.example.studyapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.studyapp.R;
import com.example.studyapp.bean.CatalogueBean;

import java.util.List;

/**
 * 导航目录使用
 */
public class CatalogueListAdapter extends BaseAdapter {
    //列表数据
    List<CatalogueBean> mPhoneBeanList;
    Context mContext;

    public CatalogueListAdapter(List<CatalogueBean> phoneBeanList, Activity mainActivity) {
        mPhoneBeanList = phoneBeanList;
        mContext = mainActivity;
    }

    /**
     * 列表条目个数
     *
     * @return
     */
    @Override
    public int getCount() {
        return mPhoneBeanList.size();
    }

    /**
     * 列表中每个条目的数据
     *
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return mPhoneBeanList.get(position);
    }

    /**
     * 每个条目的唯一标识
     *
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 每个条目使用的布局
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载layout
        View inflate;

        ListViewHolder listViewHolder;
        if (convertView == null) {
            Log.d("ListView","新创建 View "+position);

            inflate = View.inflate(mContext, R.layout.catalogue_list_item, null);
            listViewHolder = new ListViewHolder();
            listViewHolder.catalogueTextView = inflate.findViewById(R.id.tv_catalog_name);
            listViewHolder.catalogueIndexTextView = inflate.findViewById(R.id.tv_catalog_index);
            inflate.setTag(listViewHolder);
        } else {
            Log.e("ListView","复用 View "+position);
            inflate = convertView;
            listViewHolder = (ListViewHolder) inflate.getTag();
        }
        //获取当前条目 数据
        CatalogueBean phoneBean = mPhoneBeanList.get(position);

        listViewHolder.setDataFunction(phoneBean, position);


        return inflate;
    }

    private class ListViewHolder {
        TextView catalogueTextView;
        TextView catalogueIndexTextView;

        public void setDataFunction(CatalogueBean catalogueBean, int position) {
            catalogueTextView.setText(catalogueBean.pageName + " " );
            catalogueIndexTextView.setText((position+1)+"");
        }
    }

}
