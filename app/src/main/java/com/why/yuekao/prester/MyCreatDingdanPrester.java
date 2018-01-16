package com.why.yuekao.prester;

import com.why.yuekao.MyGouwuche;
import com.why.yuekao.dingdan.bean.MyCreatDingdan;
import com.why.yuekao.model.MyCretDingdanModel;

/**
 * Created by 小慧莹 on 2018/1/16.
 */

public class MyCreatDingdanPrester implements IMCreatDingdanPrester {
    private MyGouwuche myGouwuche;
    private String createApi;
    private String formatprice;
    private final MyCretDingdanModel myCretDingdanModel;

    public MyCreatDingdanPrester(MyGouwuche myGouwuche) {
        this.myGouwuche = myGouwuche;
        myCretDingdanModel = new MyCretDingdanModel(this);

    }

    public void getCreatPresterData(String createApi, String formatprice) {
      myCretDingdanModel.getCreatModelData(createApi,formatprice);
    }

    @Override
    public void Onsuccess(MyCreatDingdan myCreatDingdan) {
        myGouwuche.Onsuccess(myCreatDingdan);

    }
}
