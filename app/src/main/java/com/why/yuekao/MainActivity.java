package com.why.yuekao;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    int time=0;
    private ImageView iv;
    Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time++;
            if (time == 3) {
                Intent intent = new Intent(MainActivity.this, ShangpinXiangqing.class);
                startActivity(intent);
                finish();
            } else {
                handler.sendEmptyMessageDelayed(0, 1000);

            }
        }


    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //获取控件
        iv = findViewById(R.id.iv);

        ObjectAnimator moveIn = ObjectAnimator.ofFloat(iv, "translationY", -500f, 0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(iv, "rotation", 0f, 360f);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(iv, "alpha", 0f, 1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(iv,"scaleX",2f,1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(iv,"scaleY",2f,1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(moveIn).with(fadeInOut).with(rotate).with(scaleX).with(scaleY);
        animSet.setDuration(3000);
        animSet.start();
        handler.sendEmptyMessageDelayed(0,1000);

    }
}
