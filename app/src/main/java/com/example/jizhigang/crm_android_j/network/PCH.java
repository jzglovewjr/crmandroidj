package com.example.jizhigang.crm_android_j.network;

public class PCH {

    //用户登陆成功之后将cookie存储到sharePreferces中用此key标示
    public static final String userLoginCookieKey = "userLoginCookieKey";

    public static final String mHttpConnectError = "请检查网络"; //连接数据库失败或者从后台返回了不合法的数据
    public static final String mHttpParseError = "解析失败";//很有数据解析代码有问题

    public static final String mHttpRequestGet = "mHttpRequestGet"; //get网络请求
    public static final String mHttpRequestPost = "mHttpRequestPost"; //post网络请求


    public static final String BASE_URL = "http://47.93.31.175:8189/spoc-crm";

    public static final String requestLoginAction = BASE_URL + "/a/ws/sys/login"; //登陆
    public static final String requestUserInfoDataAction = BASE_URL + "/a/ws/sys/user/userInfoData"; //获取个人信息
    public static final String requestPubListPageAction = BASE_URL + "/a/ws/crm/crmCustomerPub/listPage"; //公共库列表
    public static final String requestMessageListAction = BASE_URL + "/a/ws/sys/sysNotification/listUserNotify?menuId=801&method=app";//消息列表




}
