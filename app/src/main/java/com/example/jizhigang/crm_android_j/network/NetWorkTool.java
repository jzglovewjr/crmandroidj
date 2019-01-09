package com.example.jizhigang.crm_android_j.network;

import android.util.Log;

import com.example.jizhigang.crm_android_j.base.activity.BaseActivity;
import com.example.jizhigang.crm_android_j.base.dao.BaseDao;
import com.example.jizhigang.crm_android_j.base.widge.JUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * 网络请求方法
 */
public class NetWorkTool {



    /**
     *
     * @param url 地址
     * @param paraDic 参数
     * @param method post/get
     * @param baseActivity 请求网络请求的context
     * @param isLoading 是否显示loading
     * @param myCallBack 回调
     * @param <T>
     */
    public static <T extends BaseDao> void request( final String url, final HashMap<String,Object> paraDic, String method, final BaseActivity baseActivity, final boolean isLoading, final MyCallBack<T> myCallBack){


        /**
         * 通过NetWorkManager.getInstance(url,paraDic,method)获取一个call对象进行异步网络请求
         */
        Call call = NetWorkManager.getInstance(url,paraDic,method);
//        if (url == ""){ //可以根据具体url设置不同超时时间😂
//            NetWorkManager.setConnectTimeout(15000); //可以设置本次网络请求的超时时间
//        }



        if (isLoading){ //网络请求出结果了，当前activity的loading引用计数 +1
            loadingManager(baseActivity,true);
        }


        call.enqueue(new Callback() {

            /**
             * 网络请求失败 没有访问到服务器
             * @param call
             * @param e
             */
            @Override
            public void onFailure( Call call, IOException e ) {

                if (isLoading){ //网络请求出错了，当前activity的loading引用计数 -1
                    loadingManager(baseActivity,false);
                }


                /**
                 * 打印出本次网络请求的错误信息，开发环境使用，生产环境可以注释掉
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


                if (isLoading){ //网络请求出结果了，当前activity的loading引用计数 -1
                    loadingManager(baseActivity,false);
                }




                /**
                 * 在NetWorkManager的单例对象中保留当前网络请求的所有call对象，
                 * 当本次网络请求结束（成功、失败）以后删除此call对象
                 */
                NetWorkManager.removeCall(call);

                //response.body().string()只能执行一次
                // 否则会报AndroidRuntime: FATAL EXCEPTION: OkHttp Dispatcher错误
                String jsonStr = response.body().string();

                /**
                 * 打印出本次网络请求的信息
                 */
                try {
                    //格式化输出获取的数据,调试使用，生产环境可以注释掉
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
                }catch (Exception e){
                    Log.d("解析错误","json解析错误");
                }





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
                    if (t.getStatusCode() == 200){ //请求成功了 状态码200表示成功
                        myCallBack.onSuccess(t,jsonStr,response);
                    }else if (t.getStatusCode() == 401){ //用户未登录 602表示用户不是销售
                        myCallBack.onError(PCH.mHttpConnectError,null);
                    }else if (t.getStatusCode() == 600){ //升级
                        myCallBack.onError(PCH.mHttpConnectError,null);
                    }else { //请求失败
                        myCallBack.onError(PCH.mHttpConnectError,null);
                    }
                }else {
                    myCallBack.onError(PCH.mHttpConnectError,null);
                }
            }
        });
    }














    /**
     * loading队列对象类
     */
    private static class  LoadingCountDownManager{
        private BaseActivity activity; //进行网络请求的activity对象
        private int countDown = 0; //当前activity的loading引用计数，如果>0那么显示loading，否则不显示loading


        public int getCountDown() { //获取当前activity对象的loading引用计数值
            return countDown;
        }

        /**
         * 赋值
         * @param activity
         */
        public void setActivity( BaseActivity activity ) {
            this.activity = activity;
        }

        public BaseActivity getActivity() {
            return activity;
        }

        /**
         * 开始或者结束loading
         * @param isAdd
         * true当前activity对象开始了一次Loading
         * true当前activity对象结束了一次Loading
         */
        public void addOne( boolean isAdd){
            if (isAdd){
                countDown += 1;
            }else {
                countDown -= 1;
                if (countDown < 0){
                    countDown = 0;
                }
            }

            if (activity != null){
                if (countDown > 0){ //当前activity显示loading
                    activity.showLoadingView(true);
                }else { //当前activity不显示loading
                    activity.showLoadingView(false);
                }
            }
        }
    }

    //loading队列
    private static ArrayList<LoadingCountDownManager> loadingArrayList = new ArrayList<LoadingCountDownManager>();


    /**
     * 为了解决同一个页面多个网络请求对loading显示效果的影响，使用引用计数的方法来管理每个Activity中的loading
     * @param activity
     * @param isBeginLoading
     */
    private static void loadingManager( BaseActivity activity, boolean isBeginLoading ){

        if (activity != null){
            int exitIndex = -1; //如果当前activity已经存在于loading队列中，那么获取其位置
            for (int i=0; i<loadingArrayList.size(); i++){
                if (loadingArrayList.get(i).getActivity() != null){
                    if (loadingArrayList.get(i).getActivity().getClass().getName() == activity.getClass().getName()){
                        loadingArrayList.get(i).addOne(isBeginLoading);
                        exitIndex = i;
                    }
                }
            }

            if (exitIndex >= 0){//当前activity已经存在于loading队列中，那么获取其位置
                if (loadingArrayList.get(exitIndex).getCountDown() == 0){ //对应activity的loading要消失
                    loadingArrayList.remove(exitIndex); //将没有loading的activity移除队列
                }
            }else {//当前activity是第一次开始loading，需要插入队列
                LoadingCountDownManager loadingCountDownManager = new LoadingCountDownManager();
                loadingCountDownManager.setActivity(activity);
                loadingCountDownManager.addOne(true);
                loadingArrayList.add(loadingCountDownManager);
            }
        }
    }


}
