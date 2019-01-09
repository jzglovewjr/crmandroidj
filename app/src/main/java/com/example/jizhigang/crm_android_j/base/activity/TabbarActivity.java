package com.example.jizhigang.crm_android_j.base.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.example.jizhigang.crm_android_j.base.BaseApplication;
import com.example.jizhigang.crm_android_j.base.dao.BaseDao;
import com.example.jizhigang.crm_android_j.base.fragment.TabbarFragment;
import com.example.jizhigang.crm_android_j.mine.activity.TongjiActivity;
import com.example.jizhigang.crm_android_j.network.MyCallBack;
import com.example.jizhigang.crm_android_j.network.NetWorkTool;
import com.example.jizhigang.crm_android_j.network.PCH;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.Response;


public class TabbarActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

        isShowTabbar(true); //首页显示tabbar
        createTitle("客户"); //设置标题
        setContentFragment(new TabbarFragment()); //给内容显示区域赋值



        Log.d("getClass().getName()",getClass().getName());

        SharedPreferences sharedPreferences = BaseApplication.getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PCH.userLoginCookieKey, "f26bf1fc7f9640fe8c8fa7926aebb414");
        editor.apply();


        /**
         *这个类可以做首页初始化完成之后需要处理的事情，
         * 首页显示的东西在TabbarActivity已经初始化完成，这里可以什么都不做 😂
         */
        HashMap<String,Object> para = new HashMap<>();
        para.put("username","minyang");
        para.put("password","123456");

//        NetWorkTool.request(PCH.requestLoginAction, para, PCH.mHttpRequestPost, new MyCallBack<BaseDao>() {
//
//            @Override
//            public void onSuccess( BaseDao baseDao, String responseString, Response response ) {
//                Log.d("网络请求成功",responseString);
//            }
//
//            @Override
//            public void onError( String errString, IOException e ) {
//                Log.d("网络请求失败",errString)
//                ;
//            }
//        });


    }
}
