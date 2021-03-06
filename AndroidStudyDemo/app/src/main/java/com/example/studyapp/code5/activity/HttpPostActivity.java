package com.example.studyapp.code5.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyapp.R;
import com.example.studyapp.code5.adapter.UserListRecyAdapter;
import com.example.studyapp.common.bean.UserBean;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpPostActivity extends AppCompatActivity {

    private String httpUrl = "http://192.168.40.200:8080/getUserList";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_post);



        //创建Client
        OkHttpClient okHttpClient = new OkHttpClient();

        //构建封装参数
        FormBody.Builder formBuild = new FormBody.Builder();
        formBuild.add("userName","李四");
        formBuild.add("userAge","28");
        FormBody formBody = formBuild.build();


        /**
         * 发送JSON数据
         */
        Map<String,Object> map = new HashMap<>();
        map.put("userName","王五");
        map.put("userAge",34);
        //将Map 转 JSON
        Gson gson = new Gson();
        String json = gson.toJson(map);

        RequestBody requestBody = FormBody.create(MediaType.parse("application/json;charset-utf-8"),json);

        //创建构建
        Request.Builder builder = new Request.Builder();
        //设置数据
        builder.url(httpUrl);
        Request build = builder.post(requestBody).build();

        //发起网络请求
        Call call = okHttpClient.newCall(build);
        //异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                //网络请求失败回调
                Log.e("http", "请求失败");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                //网络请求成功回调
                Log.d("http", "请求成功");
                //解析数据
                String responseString = response.body().string();
                //
                try {
                    JSONObject jsonObject = new JSONObject(responseString);
                    //获取对应的数据
                    int code = jsonObject.getInt("code");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }



}