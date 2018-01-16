package com.why.yuekao.dingdan.Adpter;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.why.yuekao.DingdanActivity;
import com.why.yuekao.R;
import com.why.yuekao.dingdan.bean.MyDingdanBean;
import com.why.yuekao.dingdan.prester.MyDingdanPrester;
import com.why.yuekao.utils.API;
import com.why.yuekao.utils.OkHttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 小慧莹 on 2018/1/16.
 */

public class MyDingDanAdapter extends RecyclerView.Adapter<MyDingDanAdapter.ViewHolder> {
    private FragmentActivity activity;
    private List<MyDingdanBean.DataBean> list;
    private MyDingdanPrester myDingdanPrester;
    private int page;

    public MyDingDanAdapter(FragmentActivity activity, List<MyDingdanBean.DataBean> list, MyDingdanPrester myDingdanPrester, int page) {
        this.activity = activity;
        this.list = list;
        this.myDingdanPrester = myDingdanPrester;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_dingdan,parent, false);
         ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.text_title.setText(list.get(position).getTitle());
        holder.text_price.setText("价格: "+list.get(position).getPrice());
        holder.text_tame.setText(list.get(position).getCreatetime());

        if (list.get(position).getStatus()==0){
            holder.text_daizhifu.setText("待支付");
            holder.text_daizhifu.setTextColor(Color.RED);
            holder.text_btn.setText("取消订单");
            holder.text_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle("提示");
                    builder.setMessage("确定要取消订单吗?");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Map<String, String> params=new HashMap<>();
                            params.put("uid","11443");
                            params.put("orderId", String.valueOf(list.get(position).getOrderid()));
                            params.put("status", String.valueOf(2));
                            OkHttpUtils.doPost(API.UPDATEOrder_API, params, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {

                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    if (response.isSuccessful()){
                                        list.get(position).setStatus(2);
                                        ((DingdanActivity)activity).runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                notifyDataSetChanged();
                                            }
                                        });
//                                        myDingDanPrester.getDingdanPresterData(API.UPDATEOrder_API,page);
                                    }
                                }
                            });
                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();


                }
            });

        }else if (list.get(position).getStatus()==1){
            holder.text_daizhifu.setText("已支付");
            holder.text_daizhifu.setTextColor(Color.BLACK);
            holder.text_btn.setText("查看订单");
        }else {
            holder.text_daizhifu.setText("已取消");
            holder.text_daizhifu.setTextColor(Color.BLACK);
            holder.text_btn.setText("查看订单");
            holder.text_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle("提示");
                    builder.setMessage("确定收货吗?");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Map<String, String> params = new HashMap<>();
                            params.put("uid", "11443");
                            params.put("orderId", String.valueOf(list.get(position).getOrderid()));
                            params.put("status", String.valueOf(0));
                            OkHttpUtils.doPost(API.UPDATEOrder_API, params, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {

                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    if (response.isSuccessful()) {
                                        myDingdanPrester.getDingdanPresterData(API.GETORDER_PAI, page);
                                    }
                                }
                            });
                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView text_title;
        public TextView text_daizhifu;
        public TextView text_price;
        public  TextView text_tame;
        public TextView text_btn;

        public ViewHolder(View itemView) {
            super(itemView);
            //获取控件
            text_title = itemView.findViewById(R.id.text_title);
            text_daizhifu = itemView.findViewById(R.id.text_daizhifu);
            text_price = itemView.findViewById(R.id.text_price);
            text_tame = itemView.findViewById(R.id.text_tame);
            text_btn = itemView.findViewById(R.id.text_btn);
        }
    }
}
