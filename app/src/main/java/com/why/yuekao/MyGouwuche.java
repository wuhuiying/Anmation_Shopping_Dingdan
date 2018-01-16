package com.why.yuekao;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.why.yuekao.adapter.MyGouAdapter;
import com.why.yuekao.bean.CountPriceBean;
import com.why.yuekao.bean.MyGouwuBean;
import com.why.yuekao.dingdan.bean.MyCreatDingdan;
import com.why.yuekao.prester.MyCreatDingdanPrester;
import com.why.yuekao.prester.MyGouPrester;
import com.why.yuekao.utils.API;
import com.why.yuekao.view.IMCreatView;
import com.why.yuekao.view.IMGouView;

import java.text.DecimalFormat;
import java.util.List;

public class MyGouwuche extends AppCompatActivity implements IMGouView,IMCreatView,View.OnClickListener {
  private RelativeLayout relative_progress;

    private CheckBox check_all;
    private TextView text_total;
    private TextView text_buy;
    private ExpandableListView expanble;
    private MyGouPrester myGouPrester;
    private MyGouAdapter myGouAdapter;
    private String code;
    private String msg;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                CountPriceBean countPriceBean = (CountPriceBean) msg.obj;
                text_total.setText("合计" + countPriceBean.getPriceString());
                text_buy.setText("去结算" + countPriceBean.getCount());
            }
        }
    };
    private MyCreatDingdanPrester myCreatDingdanPrester;

    private MyGouwuBean myGouwuBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gouwuche);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //获取控件
        findView();
        //创建p层
        myGouPrester = new MyGouPrester(this);
        myCreatDingdanPrester = new MyCreatDingdanPrester(this);

        //去掉默认的指示器
        expanble.setGroupIndicator(null);

        //全选设置点击事件
        check_all.setOnClickListener(this);
        text_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double price = 0;


                List<MyGouwuBean.DataBean> data = myGouwuBean.getData();
              Log.e("HHH=========",data.toString());
                for (int i = 0; i < myGouwuBean.getData().size(); i++) {
                    List<MyGouwuBean.DataBean.ListBean> list = myGouwuBean.getData().get(i).getList();
                    for (int j = 0; j < list.size(); j++) {
                        MyGouwuBean.DataBean.ListBean listBean = list.get(j);
                        //选中的时候计算数量和价格
                        if (listBean.getSelected() == 1) {
                            price += listBean.getBargainPrice() * listBean.getNum();

                        }
                    }
                }
                //给价钱格式转换一下，以免出现一串数字
                DecimalFormat decimalFormat = new DecimalFormat("0.00"); //使用java下的包
                String formatprice = decimalFormat.format(price);
               myCreatDingdanPrester.getCreatPresterData(API.CREATE_API,formatprice);




            }
        });


    }

    private void findView() {
        expanble = findViewById(R.id.expanble);
        relative_progress = findViewById(R.id.relative_progress);
        check_all = findViewById(R.id.check_all_01);
        text_total = findViewById(R.id.text_total);
        text_buy = findViewById(R.id.text_buy);
    }

    @Override
    protected void onResume() {
        super.onResume();
        myGouPrester.getPGouData(API.GETCARTS_API,"uid");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.check_all_01:
                //所有的子条目跟着条目的状态改变
                if (myGouAdapter != null) {
                    myGouAdapter.setAllChildChecked(check_all.isChecked());
                }
                break;
        }

    }

    @Override
    public void Onsuccess(final MyGouwuBean myGouwuBean) {
        this.myGouwuBean = myGouwuBean;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //获取数据成功，隐藏进度条
                relative_progress.setVisibility(View.GONE);
                if(myGouwuBean!=null){
                //根据一个组中二级列表是否选中，判断一级是否选中
                    for (int i = 0; i < myGouwuBean.getData().size(); i++) {
                        Log.e("why-----", myGouwuBean.toString());
                        if (isChildInGroupChecked(i, myGouwuBean)) {
                            myGouwuBean.getData().get(i).setGroup_check(true);
                        }
                    }
                    /**
                     * 判断所有的组是否选中
                     */
                    check_all.setChecked(isAllGroupChecked(myGouwuBean));
                    //设置 适配器
                    myGouAdapter = new MyGouAdapter(MyGouwuche.this,myGouwuBean,handler,myGouPrester,relative_progress);
                    expanble.setAdapter(myGouAdapter);

                    //展开所有的组
                    for (int i = 0; i < myGouwuBean.getData().size(); i++) {
                        //expandGroup是默认展开所有的组,ExpandableListView 默认是展开的
                        expanble.expandGroup(i);
                    }
                    //计算数量和价钱
                    myGouAdapter.setPriceAndCount();
                }else {
                    Toast.makeText(MyGouwuche.this,"您的购物车为空，请添加商品",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private boolean isAllGroupChecked(MyGouwuBean myGouwuBean) {
        for (int i = 0; i < myGouwuBean.getData().size(); i++) {
            //表示没有选中的组
            if (!myGouwuBean.getData().get(i).isGroup_check()) {
                return false;
            }
        }
        return  true;
    }

    /**
     * 当前组中的子条目是否选中
     * @param i
     * @param myGouwuBean
     * @return
     */
    private boolean isChildInGroupChecked(int i, MyGouwuBean myGouwuBean) {
        List<MyGouwuBean.DataBean.ListBean> list = myGouwuBean.getData().get(i).getList();
        for (int j = 0; j < list.size(); j++) {
            //未选中的条目
            if (list.get(j).getSelected() == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void Onsuccess(final MyCreatDingdan myCreatDingdan) {
   runOnUiThread(new Runnable() {
       @Override
       public void run() {
           code =myCreatDingdan.getCode();
           msg = myCreatDingdan.getMsg();

           if (code.equals("0")) {
               Intent intent = new Intent(MyGouwuche.this, DingdanActivity.class);
               startActivity(intent);
           } else if (code.equals("1")) {
               Toast.makeText(MyGouwuche.this, msg, Toast.LENGTH_SHORT).show();
           }

       }

   });
    }
}
