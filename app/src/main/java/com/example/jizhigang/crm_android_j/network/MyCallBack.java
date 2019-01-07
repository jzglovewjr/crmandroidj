package com.example.jizhigang.crm_android_j.network;


import java.io.IOException;

import okhttp3.Response;


/**
 * 网络请求相关的回调
 * @param <T>
 */
public interface MyCallBack<T>{


    /**
     * 网络请求成功的回调
     * @param t 格式化以后的数据
     * @param responseString response.body().string
     * @param response response所有数据
     */
    void onSuccess(T t, String responseString, Response response );


    /**
     * 网络请求错误的回调
     * @param errString 网络请求具体的错误信息
     * @param e IOException错误的所有信息
     */
    void onError( String errString, IOException e );
}

