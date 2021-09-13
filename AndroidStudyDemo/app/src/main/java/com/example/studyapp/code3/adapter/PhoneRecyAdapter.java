package com.example.studyapp.code3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyapp.R;
import com.example.studyapp.code2.bean.PhoneBean;
import com.example.studyapp.code3.activity.RecyclerViewStudyListActivity;

import java.util.List;

/**
 * recyclerView 使用数据适配器
 */
public class PhoneRecyAdapter extends RecyclerView.Adapter {
    private List<PhoneBean> mPhoneBeanList;
    private Context mContext;

    public PhoneRecyAdapter(List<PhoneBean> phoneBeanList, Context context) {
        mPhoneBeanList = phoneBeanList;
        mContext = context;
    }

    /**
     * 列表 子 Itme 显示 UI 布局
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //子Item 显示的布局
//        View inflate = View.inflate(mContext, R.layout.activity_list1_item, null);

        /**
         *  参数一 上下文对象
         */
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        /**
         * 将 Layout 文件 加载 View
         * 参数一 布局文件 ID
         * 参数二 不为null 时 会测量这个 parent 的大小来 作为 inflate 的父组件大小参考
         * 参数三 true 将加载的 layout 布局文件 自动添加到 parent 中去
         */
        View inflate = layoutInflater.inflate(R.layout.activity_list1_item, parent,false);
        CustomHolder customHolder = new CustomHolder(inflate);
        return customHolder;
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        CustomHolder customHolder = (CustomHolder) holder;

        //取出数据
        PhoneBean phoneBean = mPhoneBeanList.get(position);

        //设置数据显示
        customHolder.setData(phoneBean, position);
    }

    /**
     * 列表中 子 Item 数据
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mPhoneBeanList.size();
    }

    private static class CustomHolder extends RecyclerView.ViewHolder {

        TextView userNameTextView;
        TextView userAddrTextView;
        TextView userTimeTextView;

        public CustomHolder(@NonNull View itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.tv_user_name);
            userAddrTextView = itemView.findViewById(R.id.tv_user_addre);
            userTimeTextView = itemView.findViewById(R.id.tv_time);
        }

        /**
         * 设置数据显示
         *
         * @param phoneBean
         * @param position
         */
        public void setData(PhoneBean phoneBean, int position) {
            userNameTextView.setText(phoneBean.userName + " " + position);
            userAddrTextView.setText(phoneBean.userAddress);
            userTimeTextView.setText(phoneBean.createTime + "");
        }
    }
}
