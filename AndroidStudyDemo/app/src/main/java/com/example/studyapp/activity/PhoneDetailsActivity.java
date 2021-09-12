package com.example.studyapp.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studyapp.R;
import com.example.studyapp.base.BaseActivity;
import com.example.studyapp.bean.PhoneBean;
import com.example.studyapp.interfac.PermissionInterface;

public class PhoneDetailsActivity extends BaseActivity implements View.OnClickListener {

    private String phone;
    private PhoneBean phoneBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_details);

        //获取上一个页面传的参数
        Intent intent = getIntent();

        phone = intent.getStringExtra("phone");

        phoneBean = (PhoneBean) intent.getSerializableExtra("people");

        TextView titleTextView = findViewById(R.id.tv_title);
        TextView phoneTextView = findViewById(R.id.tv_phone);
        LinearLayout phoneLayout = findViewById(R.id.lv_phone);


        //设置电话显示
        phoneTextView.setText(phone);
        //设置姓名显示
        titleTextView.setText(phoneBean.userName);

        //设置点击事件
        phoneLayout.setOnClickListener(this);
        phoneTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_phone:
                //检查是否有权限
                checkPermisson(new PermissionInterface() {
                    @Override
                    public void onPass(int requestCode) {
                        callPhone(phone);
                    }

                    @Override
                    public void noPass(int requestCode) {
                        Toast.makeText(PhoneDetailsActivity.this, "没有同意使用权限", Toast.LENGTH_LONG).show();
                    }
                }, Manifest.permission.CALL_PHONE, 101,true);

                break;
            case R.id.lv_phone:
                callDialPhone(phone);
                break;
        }

    }


    /**
     * Android 6.0 以上 需要动态申请权限
     * 直接拨打电话
     *
     * @param phone
     */
    private void callPhone(String phone) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        startActivity(intent);
    }

    /**
     * 跳转拨号
     *
     * @param phone
     */
    private void callDialPhone(String phone) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        startActivity(intent);
    }

}