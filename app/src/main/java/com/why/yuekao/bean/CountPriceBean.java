package com.why.yuekao.bean;

/**
 * Created by 小慧莹 on 2018/1/16.
 */

public class CountPriceBean {
    private String PriceString;
    private int count;

    public CountPriceBean(String priceString, int count) {
        this.PriceString = priceString;
        this.count = count;
    }

    public String getPriceString() {
        return PriceString;
    }

    public void setPriceString(String priceString) {
        PriceString = priceString;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
