package com.why.yuekao.dingdan.prester;

import com.why.yuekao.dingdan.bean.MyDingdanBean;
import com.why.yuekao.dingdan.fragment.Frag_Daizhifu;
import com.why.yuekao.dingdan.model.MyDingdanModel;

/**
 * Created by 小慧莹 on 2018/1/16.
 */

public class MyDingdanPrester implements IMDingdanPrester{
    private Frag_Daizhifu frag_daizhifu;
    private final MyDingdanModel myDingdanModel;

    public MyDingdanPrester(Frag_Daizhifu frag_daizhifu) {

        this.frag_daizhifu = frag_daizhifu;
        myDingdanModel = new MyDingdanModel(this);
    }

    public void getDingdanPresterData(String getorderPai, int page) {
        myDingdanModel.getDingdanModelData(getorderPai,page);
    }

    @Override
    public void Onsuccess(MyDingdanBean myDingdanBean) {
        frag_daizhifu.Onsuccess(myDingdanBean);
    }
}
