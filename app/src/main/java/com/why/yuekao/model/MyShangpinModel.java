package com.why.yuekao.model;

import com.google.gson.Gson;
import com.why.yuekao.bean.MyShangpinBean;
import com.why.yuekao.prester.MyShangpinPrester;
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

public class MyShangpinModel {
    private MyShangpinPrester myShangpinPrester;

    public MyShangpinModel(MyShangpinPrester myShangpinPrester) {
        this.myShangpinPrester = myShangpinPrester;
    }

    public void getMShangpinData(String s) {

        Map<String, String> p=new HashMap<>();
        p.put("pid","34");
        p.put("source","android");

        OkHttpUtils.doPost(s,p,new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 if(response.isSuccessful()){
                     String string = response.body().string();
                     MyShangpinBean myShangpinBean=new Gson().fromJson(string,MyShangpinBean.class);
                     myShangpinPrester.Onsuccess(myShangpinBean);
                 }
            }
        });
    }
}
