package com.example.studyapp.common.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studyapp.R;
import com.example.studyapp.common.acapter.MyItemRecyclerViewAdapter;
import com.example.studyapp.common.bean.UserBean;
import com.example.studyapp.common.bean.VideoBean;
import com.example.studyapp.common.fragment.placeholder.PlaceholderContent;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class ItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<VideoBean> videoBeanList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            UserBean userBean = new UserBean();
            userBean.setUserId(i);
            userBean.setUserName("??????????????????");
            userBean.setUserImage("https://gimg2.baidu.com/image_search/src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180520%2F0473e00bdfd2476fbe0c228a45a1652c.jpeg&refer=http%3A%2F%2F5b0988e595225.cdn.sohucs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1635338531&t=2dccf620a0c5dad30b432a37a8661d03");

            VideoBean videoBean = new VideoBean();
            videoBean.setUserBean(userBean);
            videoBean.setCollectNumber(100);
            videoBean.setPariseNumber(2903);
            videoBean.setMessageNumber(333);

            videoBean.setTitle("Android RecyclerView ????????????????????? RecyclerView ???????????????");
            videoBean.setVideoUrl("");
            videoBeanList.add(videoBean);
        }

        recyclerView.setAdapter(new MyItemRecyclerViewAdapter(videoBeanList,getContext()));
    }
}