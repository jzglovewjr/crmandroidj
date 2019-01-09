package com.example.jizhigang.crm_android_j.network;

import android.content.SharedPreferences;

import com.example.jizhigang.crm_android_j.base.BaseApplication;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


/**
 * 一、网络请求相关的配置，包括超时时间、请求头等
 * 二、生成OkHttpClient单例对象，并且以次单例对象生成Call对象进行网络请求
 */
public class NetWorkManager {

    private static int mConnectTimeout = 10000; //默认连接超时时间10秒=10000毫秒
    private static int mReadTimeout = 10000; //默认读取超时时间10秒=10000毫秒
    private static int mWriteTimeout = 10000; //默认写入超时时间10秒=10000毫秒

    /**
     * 存放当前正在网络请求的call们，以备后续进行取消等操作
     */
    private static ArrayList<Call> callList = new ArrayList<Call>();


    /**
     * 声明OkHttp的客户端类
     */
    private static volatile OkHttpClient mOkHttpClient = null;


    /**
     * 私有化构造方法防止外部创建
     */
    private NetWorkManager(){}


    /**
     * 定义类型变量（不初始化，不使用final关键字，使用volatile保证了多线程访问时mNetWorkManager变量的可见性，
     * 避免了mNetWorkManager初始化时其他变量属性还没有赋值完时被另外线程调用）
     */
    private static volatile NetWorkManager mNetWorkManager = null;




    /**
     * 通过OkHttpClient的单例对象创造一个call对象进行本次网络请求
     * @param url 地址
     * @param map 参数map
     * @param method GET/POST
     * @return
     */
    public static Call getInstance( String url, HashMap<String,Object> map, String method){

        /**
         * 对象实例化时调用，不使用同步代码块，mNetWorkManager!=null时直接返回对象，提高运行效率
         */
        if (mNetWorkManager == null){
            synchronized (NetWorkManager.class){
                //未初始化时，初始化mNetWorkManager对象
                if (mNetWorkManager == null){
                    mNetWorkManager = new NetWorkManager();
                    mOkHttpClient = new OkHttpClient
                            .Builder()
                            .connectTimeout(mConnectTimeout,TimeUnit.MILLISECONDS)
                            .readTimeout(mReadTimeout,TimeUnit.MILLISECONDS)
                            .writeTimeout(mWriteTimeout,TimeUnit.MILLISECONDS)
                            .build();
                }
            }
        }


        /**
         * 如果通过setConnectTimeout方法修改了连接超时时间，
         * 那么再次进行网络请求时要修改为默认的连接超时时间
         */
        if (mOkHttpClient.connectTimeoutMillis() != mConnectTimeout){
            try {
                Field connectTimeoutField = mOkHttpClient.getClass().getDeclaredField("connectTimeout");
                connectTimeoutField.setAccessible(true);
                connectTimeoutField.setInt(mOkHttpClient,mConnectTimeout);
            }catch (NoSuchFieldException e){
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


        /**
         * 如果通过setReadTimeout方法修改了连接超时时间，
         * 那么再次进行网络请求时要修改为默认的读取超时时间
         */
        if (mOkHttpClient.readTimeoutMillis() != mReadTimeout){
            try {
                Field readTimeoutField = mOkHttpClient.getClass().getDeclaredField("readTimeout");
                readTimeoutField.setAccessible(true);
                readTimeoutField.setInt(mOkHttpClient,mReadTimeout);
            }catch (NoSuchFieldException e){
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


        /**
         * 如果通过setWriteTimeout方法修改了连接超时时间，
         * 那么再次进行网络请求时要修改为默认的写入超时时间
         */
        if (mOkHttpClient.writeTimeoutMillis() != mWriteTimeout){ //通过其他方法修改过写入超时时间
            try {
                Field writeTimeoutField = mOkHttpClient.getClass().getDeclaredField("writeTimeout");
                writeTimeoutField.setAccessible(true);
                writeTimeoutField.setInt(mOkHttpClient,mWriteTimeout);
            }catch (NoSuchFieldException e){
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


        //将参数字段转化为json字符串
        Gson gson = new Gson();
        String json = gson.toJson(map);

        //设置请求参数的格式为utf-8
        MediaType JSON = MediaType.parse("application/json;charset=UTF-8");
        RequestBody body = RequestBody.create(JSON,json);
        Request request;
        //请求头
        Headers headers;
        String cookie = BaseApplication.getSharedPreferences().getString(PCH.userLoginCookieKey,null);
        if (cookie != null){
            headers = new Headers.Builder()
                    .add("cookie","spoc.session.id=" + cookie)
                    .add("os","ios")
                    .add("appname","crm")
                    .add("version","2.9.1")
                    .add("Content-Type","application/json;charset=UTF-8")
                    .build();
        }else {
            headers = new Headers.Builder()
                    .add("os","ios")
                    .add("appname","crm")
                    .add("version","2.9.1")
                    .add("Content-Type","application/json;charset=UTF-8")
                    .build();
        }


        if (method.equals(PCH.mHttpRequestPost)){ //post请求
            request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .headers(headers)
                    .build();
        }else {//get请求
            request = new Request.Builder()
                    .url(url)
                    .get()
                    .headers(headers)
                    .build();
        }


        Call call = mOkHttpClient.newCall(request);
        callList.add(call);
        return call;
    }


    /**
     * 如果当前网络请求已经成功或者失败，那么要将当前call从callList中删除
     * @param call
     */
    public static void removeCall(Call call){
        callList.remove(call);
    }



    /**
     * 单独修改本次网络请求的连接超时时间
     * @param connectTimeout
     */
    public static void setConnectTimeout(int connectTimeout){
        if (mOkHttpClient != null && mOkHttpClient.connectTimeoutMillis() != connectTimeout){
            try {
                Field connectTimeoutField = mOkHttpClient.getClass().getDeclaredField("connectTimeout");
                connectTimeoutField.setAccessible(true);
                connectTimeoutField.setInt(mOkHttpClient,connectTimeout);
            }catch (NoSuchFieldException e){
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 单独修改本次网络请求的读取超时时间
     * @param readTimeout
     */
    public static void setReadTimeout(int readTimeout){
        if (mOkHttpClient != null && mOkHttpClient.readTimeoutMillis() != readTimeout){
            try {
                Field readTimeoutField = mOkHttpClient.getClass().getDeclaredField("readTimeout");
                readTimeoutField.setAccessible(true);
                readTimeoutField.setInt(mOkHttpClient,readTimeout);
            }catch (NoSuchFieldException e){
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 单独修改本次网络请求的读取超时时间
     * @param writeTimeout
     */
    public static void setWriteTimeout( int writeTimeout ) {
        if (mOkHttpClient != null && mOkHttpClient.readTimeoutMillis() != writeTimeout){
            try {
                Field writeTimeoutField = mOkHttpClient.getClass().getDeclaredField("writeTimeout");
                writeTimeoutField.setAccessible(true);
                writeTimeoutField.setInt(mOkHttpClient,writeTimeout);
            }catch (NoSuchFieldException e){
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
