package com.example.studyapp.code3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyapp.R;
import com.example.studyapp.code2.bean.PhoneBean;

import java.util.List;

/**
 * recyclerView 使用数据适配器
 */
public class PhoneRecyImgAdapter extends RecyclerView.Adapter {

    public interface PhoneRecyInterface {
        void onItemClick(PhoneBean phoneBean, int position);
    }

    /**
     * 点击事件回调
     */
    private  PhoneRecyInterface mPhoneRecyInterface;
    private List<PhoneBean> mPhoneBeanList;
    private Context mContext;
    /**
     * true 时为瀑布流
     */
    private boolean mIsStaggere;

    public PhoneRecyImgAdapter(List<PhoneBean> phoneBeanList, Context context, boolean isStaggere) {
        mPhoneBeanList = phoneBeanList;
        mContext = context;
        mIsStaggere = isStaggere;
    }

    /**
     * 点击事件
     * @param mPhoneRecyInterface
     */
    public void setmPhoneRecyInterface(PhoneRecyInterface mPhoneRecyInterface) {
        this.mPhoneRecyInterface = mPhoneRecyInterface;
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
        int layoutId = R.layout.activity_list2_item;
        if (mIsStaggere) {
            //瀑布流使用 需要填充
            layoutId = R.layout.activity_list2_item;
        }
        View inflate = layoutInflater.inflate(layoutId, parent, false);
        CustomHolder customHolder = new CustomHolder(inflate, mIsStaggere);
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
        customHolder.setData(phoneBean, position,mIsStaggere,mPhoneRecyInterface);
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
        ImageView userImageView;

        public CustomHolder(@NonNull View itemView, boolean isStaggere) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.tv_user_name);
            userImageView = itemView.findViewById(R.id.iv_user_image);
        }

        /**
         * 设置数据显示
         *  @param phoneBean
         * @param position
         * @param mPhoneRecyInterface
         */
        public void setData(PhoneBean phoneBean, int position, boolean isStaggere, PhoneRecyInterface mPhoneRecyInterface) {
            userNameTextView.setText(phoneBean.userName + " " + position);
            userImageView.setImageResource(phoneBean.image);

            if (isStaggere) {
                //计算一个随机的高度
                ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
                layoutParams.height = (int) (400 + Math.random() * 400);
            }
            if(mPhoneRecyInterface!=null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPhoneRecyInterface.onItemClick(phoneBean,position);
                    }
                });
            }
        }
    }
}
