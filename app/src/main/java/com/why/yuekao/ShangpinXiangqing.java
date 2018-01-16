package com.why.yuekao;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.why.yuekao.bean.MyShangpinBean;
import com.why.yuekao.prester.MyShangpinPrester;
import com.why.yuekao.utils.API;
import com.why.yuekao.view.IMShangpinView;

import java.util.ArrayList;
import java.util.List;

public class ShangpinXiangqing extends AppCompatActivity implements IMShangpinView{
    private TextView shangjianame;
    private ImageView shangpinimag;
    private TextView shangpinprice;
    private TextView shangpintitle;
    private Button addCart;
    private Button getCart;
    private List<String> images;
    private TextView barginprice;
    private MyShangpinPrester myShangpinPrester;
    private XBanner xbanner;
  private String pid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangpin_xiangqing);
        //获取控件
        findView();
        //定义中间者
        myShangpinPrester = new MyShangpinPrester(this);
        myShangpinPrester.getPShangpinData(API.DETAL_API);

        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShangpinXiangqing.this, "您的商品已经加入购物车", Toast.LENGTH_SHORT).show();
            }
        });

        getCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ShangpinXiangqing.this,MyGouwuche.class);
                startActivity(intent);
            }
        });
    }

    private void findView() {
        shangjianame = (TextView) findViewById(R.id.shangjianame);
        xbanner = findViewById(R.id.xbanner);
        shangpinprice = (TextView) findViewById(R.id.shangpinprice);
        barginprice = findViewById(R.id.barginprice);
        shangpintitle = (TextView) findViewById(R.id.shangpintitle);
        addCart = (Button) findViewById(R.id.addCart);
        getCart = (Button) findViewById(R.id.getCart);
    }

    @Override
    public void Onsuccess(final MyShangpinBean myShangpinBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                images = new ArrayList<>();
                String imageslist = myShangpinBean.getData().getImages();
//                Log.e("WHY____=+++=+", imageslist.toString());
                String[] split = imageslist.split("\\|");
                //图片循环添加集合
                for (String string : split) {
                    images.add(string);
                }
                xbanner.setData(images, null);
                xbanner.setPoinstPosition(XBanner.BOTTOM);
                xbanner.setmAdapter(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        Glide.with(ShangpinXiangqing.this).load(images.get(position)).into((ImageView) view);
                    }
                });
                shangpintitle.setText(myShangpinBean.getData().getTitle());
                shangpinprice.setText("原价" + myShangpinBean.getData().getBargainPrice());

                barginprice.setText("现在" + myShangpinBean.getData().getPrice());
                shangpinprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//中划线
            }
        });
    }
}
