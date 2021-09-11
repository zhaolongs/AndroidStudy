package com.example.studyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studyapp.bean.PhoneBean;

public class PhoneDetailsActivity extends AppCompatActivity implements View.OnClickListener {

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
                boolean permisson = checkPermisson();
                if (permisson) {
                    callPhone(phone);
                }
                break;
            case R.id.lv_phone:
                callDialPhone(phone);
                break;
        }

    }

    private boolean checkPermisson() {
        //检查是否有权限
        int checkSelfPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (checkSelfPermission == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            //申请
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 101);
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==101){
            if(permissions.length!=0&&grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                //没有通过权限
                Toast.makeText(this,"没有拨打电话的权限",Toast.LENGTH_LONG).show();
            }else{
                callPhone(phone);
            }
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