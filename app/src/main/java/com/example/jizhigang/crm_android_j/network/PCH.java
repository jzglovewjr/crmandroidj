package com.example.jizhigang.crm_android_j.network;

public class PCH {

    //用户登陆成功之后将cookie存储到sharePreferces中用此key标示
    public static final String userLoginCookieKey = "userLoginCookieKey";

    public static final String mHttpConnectError = "请检查网络"; //连接数据库失败或者从后台返回了不合法的数据
    public static final String mHttpParseError = "解析失败";//很有数据解析代码有问题

    public static final String mHttpRequestGet = "mHttpRequestGet"; //get网络请求
    public static final String mHttpRequestPost = "mHttpRequestPost"; //post网络请求


    public static final String BASE_URL = "nizjijidedizhi";

    public static final String requestLoginAction = BASE_URL + "nizjijidedizhi"; //登陆
    public static final String requestUserInfoDataAction = BASE_URL + "nizjijidedizhi"; //获取个人信息
    public static final String requestPubListPageAction = BASE_URL + "nizjijidedizhi"; //公共库列表
    public static final String requestMessageListAction = BASE_URL + "/nizjijidedizhi";//消息列表




}
