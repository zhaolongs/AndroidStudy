package com.example.studyapp.code00.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.studyapp.R;

import java.io.File;

/**
 * ContextCompat 使用说明
 */
public class ContextCompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_compat);

        //动态检查相机权限
        int selfPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        //检查结果
        if (selfPermission == PackageManager.PERMISSION_GRANTED) {
        //有许可
        } else {
            //无
        }

        //缓存文件的目录的路径
        File codeCacheDir = ContextCompat.getCodeCacheDir(this);

        //获取 应用程序的私有文件的目录的绝对路径。
        File dataDir = ContextCompat.getDataDir(this);

        //外部存储设备上应用程序特定目录
        File[] cacheDirs = ContextCompat.getExternalCacheDirs(this);

        //加载资源ID
        int color = ContextCompat.getColor(this,R.color.purple_200);
        //加载 Drawable
        Drawable drawable = ContextCompat.getDrawable(this,R.drawable.bg_bottom_tips_shape);
    }
}