package com.why.yuekao.model;

import com.google.gson.Gson;
import com.why.yuekao.dingdan.bean.MyCreatDingdan;
import com.why.yuekao.prester.MyCreatDingdanPrester;
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

public class MyCretDingdanModel {


    private MyCreatDingdanPrester myCreatDingdanPrester;

    public MyCretDingdanModel(MyCreatDingdanPrester myCreatDingdanPrester) {
        this.myCreatDingdanPrester = myCreatDingdanPrester;
    }

    public void getCreatModelData(String createApi, String formatprice) {
        Map<String, String> p=new HashMap<>();
        p.put("uid","11443");
        p.put("price",formatprice);
        OkHttpUtils.doPost(createApi, p, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String string = response.body().string();
                    MyCreatDingdan myCreatDingdan=new Gson().fromJson(string,MyCreatDingdan.class);
                    myCreatDingdanPrester.Onsuccess(myCreatDingdan);
                }

            }
        });
    }
}
