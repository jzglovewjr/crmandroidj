package com.example.jizhigang.crm_android_j.network;

import android.util.Log;

import com.example.jizhigang.crm_android_j.base.dao.BaseDao;
import com.example.jizhigang.crm_android_j.base.widge.JUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * 网络请求方法
 */
public class NetWorkTool {


    /**
     * 网络请求
     * @param url 地址
     * @param paraDic 地址
     * @param method GET/POST
     * @param myCallBack 回调
     */
    public static <T extends BaseDao> void request( final String url, final HashMap<String,Object> paraDic, String method, final MyCallBack<T> myCallBack){


        /**
         * 通过NetWorkManager.getInstance(url,paraDic,method)获取一个call对象进行异步网络请求
         */
        Call call = NetWorkManager.getInstance(url,paraDic,method);
//        if (url == ""){ //可以根据具体url设置不同超时时间😂
//            NetWorkManager.setConnectTimeout(15000); //可以设置本次网络请求的超时时间
//        }
        call.enqueue(new Callback() {

            /**
             * 网络请求失败 没有访问到服务器
             * @param call
             * @param e
             */
            @Override
            public void onFailure( Call call, IOException e ) {

                /**
                 * 打印出本次网络请求的错误信息
                 */
                Log.d(" "," ");Log.d(" "," ");Log.d(" "," ");
                Log.d("网络请求失败url=",url);
                Log.d("requestHeader=", String.valueOf(call.request().headers()));
                Log.d("requestPara=", String.valueOf(paraDic));
                JUtil.i("response==", String.valueOf(e));
                Log.d("","--------------------------------------------------------------------------------------------------------");
                Log.d(" "," ");Log.d(" "," ");Log.d(" "," ");
                /**
                 * 在NetWorkManager的单例对象中保留当前网络请求的所有call对象，
                 * 当本次网络请求结束（成功、失败）以后删除此call对象
                 */
                NetWorkManager.removeCall(call);
                myCallBack.onError(PCH.mHttpConnectError,e);
            }


            /**
             * 网络请求访问到服务器了，分析服务器数据得到想要的结果
             * 如果数据很简单不需要解析为model可以直接传入BaseDao，然后获取
             * myCallBack.onSuccess(t,jsonStr,response)中的jsonStr自己做解析
             *
             * @param call
             * @param response
             * @throws IOException
             */
            @Override
            public void onResponse( Call call, Response response ) throws IOException {


                //response.body().string()只能执行一次
                // 否则会报AndroidRuntime: FATAL EXCEPTION: OkHttp Dispatcher错误
                String jsonStr = response.body().string();

                /**
                 * 打印出本次网络请求的信息
                 */

                //格式化输出获取的数据
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                HashMap<String,Object> jsonModel = gson.fromJson(jsonStr,new TypeToken<HashMap<String,Object>>(){}.getType());
                String outputJsonStr = gson.toJson(jsonModel,new TypeToken<HashMap<String,Object>>(){}.getType());
                Log.d(" "," ");Log.e(" "," ");Log.i(" "," ");
                Log.d("网络请求成功url=",url);
                Log.d("requestHeader=", String.valueOf(call.request().headers()));
                Log.d("requestPara=", String.valueOf(paraDic));
                JUtil.i("response==",outputJsonStr);
                Log.d("","--------------------------------------------------------------------------------------------------------");
                Log.d(" "," ");Log.e(" "," ");Log.i(" "," ");



                //设计思想： 由上而下，层层细化： ，传入要解析对象，返回解析实体对象。
                T t = null;
                // 解析对象过程
                //获得泛型集合
                //实体类型
                Class<? extends MyCallBack> callBackClass = myCallBack.getClass();
                String name = callBackClass.getSimpleName();
                Type[] interfaces = callBackClass.getGenericInterfaces(); //获取接口类型
                if (!(interfaces[0] instanceof ParameterizedType)){ //MyCall中的范型T不能为空，如果不需要解析为model可以传BaseDao
                    myCallBack.onError(PCH.mHttpParseError,null);
                    return;
                }
                ParameterizedType parameterizedType = (ParameterizedType) (interfaces[0]);
                Type type = parameterizedType.getActualTypeArguments()[0];
                Class<T> entityClass = (Class<T>) (type);

                t = NetUtil.parse(jsonStr,entityClass);

                if (t != null){
                    /**
                     * 在NetWorkManager的单例对象中保留当前网络请求的所有call对象，
                     * 当本次网络请求结束（成功、失败）以后删除此call对象
                     */
                    NetWorkManager.removeCall(call);
                    if (t.getStatusCode() == 200){ //请求成功了 状态码200表示成功
                        myCallBack.onSuccess(t,jsonStr,response);
                    }else if (t.getStatusCode() == 401){ //用户未登录 602表示用户不是销售

                    }else if (t.getStatusCode() == 600){ //升级

                    }else { //请求失败
                        myCallBack.onError(PCH.mHttpConnectError,null);
                    }
                }else {
                    myCallBack.onError(PCH.mHttpConnectError,null);
                }
            }
        });
    }

}
