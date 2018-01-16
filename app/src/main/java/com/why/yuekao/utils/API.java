package com.why.yuekao.utils;

/**
 * Created by 小慧莹 on 2017/12/26.
 */

public class API {
    //登录
//    public static final String LOGIN_API="https://www.zhaoapi.cn/user/login";
    public static final String LOGIN_API="https://www.zhaoapi.cn/user/login";
    //注册
    public static final String RES_API="https://www.zhaoapi.cn/user/reg";
    //上传头像
    public static final String HEAD_API="http://120.27.23.105/user/upload";
    //获取用户信息
    public static final String YONGHU_API="http://120.27.23.105/user/getUserInfo?uid=";
    //修改昵称
    public static final String NICHENG_API="http://120.27.23.105/user/updateNickName";
    //首页广告接口
    public  static  final String Lunbo_API="https://www.zhaoapi.cn/ad/getAd";
    //商品分类接口
    //分类左侧
    public  static  final String SHOPPING_API="https://www.zhaoapi.cn/product/getCatagory";
    //商品子分类接口
    public  static  final String SHOP_API="https://www.zhaoapi.cn/product/getProductCatagory?cid=";
    //商品详情接口
    public  static  final String DETAL_API="http://120.27.23.105/product/getProductDetail";
    //当前子分类下的商品列表
    public  static  final String ZIFENLEI_API="http://120.27.23.105/product/getProducts";
    //根据关键词搜索商品
    public  static  final String GUANJIAN_API="https://www.zhaoapi.cn/product/searchProducts";
    //添加购物车接口
    public static final String ADDCART_API="http://120.27.23.105/product/addCart";
    //删除购物车
    public static String deleteCartUrl ="https://www.zhaoapi.cn/product/deleteCart";
    //查询购物车
    public static final String GETCARTS_API="http://120.27.23.105/product/product/getCarts";
    //更新购物车
    public static final String UPDATECARTS_API="https://www.zhaoapi.cn/product/updateCarts";
    //修改订单状态
    public static final String UPDATEOrder_API="https://www.zhaoapi.cn/product/updateOrder";
    //创建订单
    public static final String CREATE_API="https://www.zhaoapi.cn/product/createOrder";
    //订单列表
    public static final String GETORDER_PAI="http://120.27.23.105/product/getOrders";
    //常用收货地址列表
    public static final String GETADD_API="http://120.27.23.105/user/getAddrs";
    //添加常用收获地址
    public static final String ADDAddR_API="http://120.27.23.105/user/addAddr?uid=71&addr=北京市昌平区金域国际1-1-1&mobile=18612991023&name=kson";
    //修改常用收货地址
    public static final String UPDATEADD_API="http://120.27.23.105/user/updateAddr?uid=71&addrid=2";
    //设置默认地址
    public static final String SETADDR_API="http://120.27.23.105/user/setAddr?uid=71&addrid=3&status=1";
    //获取默认地址
    public static final String GETDefaultAddr_API="http://120.27.23.105/user/getDefaultAddr?uid=71";
}


