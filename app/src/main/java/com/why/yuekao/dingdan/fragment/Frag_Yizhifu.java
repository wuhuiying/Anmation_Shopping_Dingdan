package com.why.yuekao.dingdan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import com.why.yuekao.R;
import com.why.yuekao.dingdan.bean.MyDingdanBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 小慧莹 on 2018/1/15.
 */

public class Frag_Yizhifu extends Fragment{
    private List<MyDingdanBean.DataBean> list = new ArrayList<>();
    private View view;
    private RecyclerView recycler_daizhi;
    private int status = 0;

    private RelativeLayout relative_bar;
    //    private SmartRefreshLayout smart_refresh;
    private int page = 0;
    private int a = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragyizhifu, container, false);
        //获取控件
        recycler_daizhi = view.findViewById(R.id.recycler_daizhi);
        relative_bar = view.findViewById(R.id.relative_bar);
//        smart_refresh = view.findViewById(R.id.smart_refresh);
        return view;
    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//
//        //创建中间层
//        myDingDanPrester = new MyDingDanPrester(this);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        relative_bar.setVisibility(View.VISIBLE);
//        if (a == 1) {
//
//        }
//        myDingDanPrester.getDingdanPresterData(API.GETORDER_PAI, page);
//    }
//
//    @Override
//    public void Onsuccess(final MyDingdanBean myDingdanBean) {
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                list.addAll(myDingdanBean.getData());
//                //创建适配器
//                myAdapter();
//                //进度条隐藏
//                relative_bar.setVisibility(View.GONE);
//            }
//        });
//
//    }
//
//    private void myAdapter() {
//        recycler_daizhi.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//        MyDingDanAdapter adapter = new MyDingDanAdapter(getActivity(), list, myDingDanPrester, page);
//        recycler_daizhi.setAdapter(adapter);
//    }
}
