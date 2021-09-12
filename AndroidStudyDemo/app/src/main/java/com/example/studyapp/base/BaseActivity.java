package com.example.studyapp.base;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;


import com.example.studyapp.interfac.PermissionInterface;

public class BaseActivity extends AppCompatActivity {
    private Context mContext;
    /**
     * 子Activity 权限申请使用的回调
     */
    private PermissionInterface mPermissionInterface;

    /**
     * 权限没有通过时 true 显示一个弹框再确认
     */
    private boolean isShowPermissionAlert;
    private String showPermissionMessage = "权限申请没有通过";
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }


    /**
     * 子 Activity 调用申请权限功能
     *
     * @param permissionInterface
     * @return
     */
    protected boolean checkPermisson(PermissionInterface permissionInterface, String permission, int requestCode) {
        return this.checkPermisson(permissionInterface, permission, requestCode, false);
    }

    /**
     * 子 Activity 调用申请权限功能
     *
     * @param permissionInterface
     * @return
     */
    protected boolean checkPermisson(PermissionInterface permissionInterface, String permission, int requestCode, boolean showAlert) {
        mPermissionInterface = permissionInterface;
        isShowPermissionAlert = showAlert;
        //检查是否有权限
        int checkSelfPermission = ContextCompat.checkSelfPermission(this, permission);
        if (checkSelfPermission == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            //申请
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
            return false;
        }
    }

    /**
     * 权限申请的回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mPermissionInterface != null) {

            if (permissions.length != 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                if (isShowPermissionAlert) {
                    //显示弹框
                    showAlertFunction();
                } else {
                    //没有通过权限
                    mPermissionInterface.noPass(requestCode);
                }

            } else {
                //申请通过
                mPermissionInterface.onPass(requestCode);
            }

        }
    }

    private void showAlertFunction() {
        //创建构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        alertDialog = builder.setTitle("温馨提示").setMessage(showPermissionMessage).setNegativeButton("知道了 暂不使用", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //没有通过权限申请的弹框
                mPermissionInterface.noPass(0);
                alertDialog.dismiss();
            }
        }).setPositiveButton("去设置中心", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.fromParts("package", mContext.getPackageName(), null));
                mContext.startActivity(intent);
                alertDialog.dismiss();
            }
        }).create();
        //显示弹框
        alertDialog.show();
    }
}