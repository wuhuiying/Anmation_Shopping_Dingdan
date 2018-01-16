package com.why.yuekao.prester;

import com.why.yuekao.ShangpinXiangqing;
import com.why.yuekao.bean.MyShangpinBean;
import com.why.yuekao.model.MyShangpinModel;

/**
 * Created by 小慧莹 on 2018/1/16.
 */

public class MyShangpinPrester implements IMPrester{
    private ShangpinXiangqing shangpinXiangqing;
    private final MyShangpinModel myShangpinModel;

    public MyShangpinPrester(ShangpinXiangqing shangpinXiangqing) {
        this.shangpinXiangqing = shangpinXiangqing;
        myShangpinModel = new MyShangpinModel(this);
    }

    public void getPShangpinData(String s) {
        myShangpinModel.getMShangpinData(s);

    }

    @Override
    public void Onsuccess(MyShangpinBean myShangpinBean) {
        shangpinXiangqing.Onsuccess(myShangpinBean);
    }
}
