package com.why.yuekao.dingdan.model;

import com.google.gson.Gson;
import com.why.yuekao.dingdan.bean.MyDingdanBean;
import com.why.yuekao.dingdan.prester.MyDingdanPrester;
import com.why.yuekao.utils.OkHttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 小慧莹 on 2018/1/16.
 */

public class MyDingdanModel {
    private MyDingdanPrester myDingdanPrester;

    public MyDingdanModel(MyDingdanPrester myDingdanPrester) {
        this.myDingdanPrester = myDingdanPrester;
    }

    public void getDingdanModelData(String getorderPai, int page) {
        Map<String, String> p=new HashMap<>();
        p.put("uid","11443");
        p.put("page",page+"");
        OkHttpUtils.doPost(getorderPai, p, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String string = response.body().string();
                    MyDingdanBean myDingdanBean=new Gson().fromJson(string,MyDingdanBean.class);
                    myDingdanPrester.Onsuccess(myDingdanBean);

                }

            }
        });
    }
}
