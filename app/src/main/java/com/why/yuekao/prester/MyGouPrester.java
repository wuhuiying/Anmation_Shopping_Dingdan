package com.why.yuekao.prester;

import com.why.yuekao.MyGouwuche;
import com.why.yuekao.bean.MyGouwuBean;
import com.why.yuekao.model.MyGouModel;

/**
 * Created by 小慧莹 on 2018/1/16.
 */

public class MyGouPrester implements IMGouPrester {
    private MyGouwuche myGouwuche;
    private String getcartsApi;
    private final MyGouModel myGouModel;

    public MyGouPrester(MyGouwuche myGouwuche) {
        this.myGouwuche = myGouwuche;
        myGouModel = new MyGouModel(this);
    }

    public void getPGouData(String getcartsApi,String uid) {

        myGouModel.getMGouData(getcartsApi,uid);
    }

    @Override
    public void Onsuccess(MyGouwuBean myGouwuBean) {
        myGouwuche.Onsuccess(myGouwuBean);
    }
}
