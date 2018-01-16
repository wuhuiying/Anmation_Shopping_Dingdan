package com.why.yuekao;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.why.yuekao.dingdan.fragment.Frag_Daizhifu;
import com.why.yuekao.dingdan.fragment.Frag_Yiquxiao;
import com.why.yuekao.dingdan.fragment.Frag_Yizhifu;

public class DingdanActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView image_btn;
    private TextView text_daizhi;
    private TextView text_yizhi;
    private TextView text_yiqu;
    private FrameLayout frame_layout;
    private View item_popup;
    private View view;
    private PopupWindow popupWindow;
    private TextView btn_daizhi;
    private TextView btn_yizhi;
    private TextView btn_yiqu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan);

        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //获取控件
        findView();

        text_daizhi.setOnClickListener(this);
        text_yizhi.setOnClickListener(this);
        text_yiqu.setOnClickListener(this);
        Frag_Daizhifu frag_daizhifu = new Frag_Daizhifu();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, frag_daizhifu).commit();
        item_popup = View.inflate(this, R.layout.item_popup, null);
        view = View.inflate(this, R.layout.activity_dingdan, null);
        popupWindow = new PopupWindow(item_popup
                , ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);

        btn_daizhi = item_popup.findViewById(R.id.btn_daizhi);
        btn_yizhi = item_popup.findViewById(R.id.btn_yizhi);
        btn_yiqu = item_popup.findViewById(R.id.btn_yiqu);
        btn_daizhi.setOnClickListener(this);
        btn_yizhi.setOnClickListener(this);
        btn_yiqu.setOnClickListener(this);
        image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
                popupWindow.showAsDropDown(image_btn);
            }
        });

    }
    //获取控件
    private void findView() {
        image_btn = findViewById(R.id.image);
        text_daizhi = findViewById(R.id.text_daizhifu);
        text_yizhi = findViewById(R.id.text_yizhifu);
        text_yiqu = findViewById(R.id.text_yiquxiao);
        frame_layout = findViewById(R.id.frame_layout);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_daizhifu:
                Frag_Daizhifu frag_daizhifu = new Frag_Daizhifu();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, frag_daizhifu).commit();
                break;
            case R.id.text_yizhifu:
                Frag_Yizhifu frag_yizhifu = new Frag_Yizhifu();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, frag_yizhifu).commit();
                break;
            case R.id.text_yiquxiao:
                Frag_Yiquxiao frag_yiquxiao = new Frag_Yiquxiao();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, frag_yiquxiao).commit();
                break;


            case R.id.btn_daizhi:
                Frag_Daizhifu frag_daizhi2 = new Frag_Daizhifu();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, frag_daizhi2).commit();
                popupWindow.dismiss();
                break;
            case R.id.btn_yizhi:
                Frag_Yizhifu frag_yiZhi2 = new Frag_Yizhifu();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, frag_yiZhi2).commit();
                popupWindow.dismiss();
                break;
            case R.id.btn_yiqu:
                Frag_Yiquxiao frag_yiQu2 = new Frag_Yiquxiao();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, frag_yiQu2).commit();
                popupWindow.dismiss();
                break;
        }

    }
}
