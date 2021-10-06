package com.example.studyapp.code5.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.studyapp.R;
import com.example.studyapp.code5.adapter.UserListRecyAdapter;
import com.example.studyapp.code5.bean.Res;
import com.example.studyapp.code5.bean.ResList;
import com.example.studyapp.common.bean.UserBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class UserListActivity extends AppCompatActivity {

    private String httpUrl = "http://192.168.40.200:8080/getUserList";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);


        recyclerView = findViewById(R.id.rv_user_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //创建Client
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建构建
        Request.Builder builder = new Request.Builder();
        //设置数据
        builder.url(httpUrl);
        Request build = builder.get().build();

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
                    //获取列表数据
                    JSONArray data = jsonObject.getJSONArray("data");
                    //解析列表数据
                    List<UserBean> userBeanList = new ArrayList<>();
                    for (int i = 0; i < data.length(); i++) {
                        //获取每一个对象
                        JSONObject itemObj = (JSONObject) data.get(i);
                        //转化为自定义对象
                        UserBean userBean = new UserBean();
                        userBean.setUserName(itemObj.getString("userName"));
                        userBean.setId(itemObj.getLong("id"));

                        userBeanList.add(userBean);
                    }

                    //设置数据显示
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            setData(userBeanList);
                        }
                    });



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private Handler mHandler = new Handler(Looper.getMainLooper());
    private void setData(List<UserBean> userBeanList) {
        //创建列表适配器
        UserListRecyAdapter userListRecyAdapter = new UserListRecyAdapter(userBeanList,this);
        //设置数据显示
        recyclerView.setAdapter(userListRecyAdapter);
    }


}