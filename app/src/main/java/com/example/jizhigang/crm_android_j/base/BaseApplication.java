package com.example.jizhigang.crm_android_j.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.jizhigang.crm_android_j.R;
import com.example.jizhigang.crm_android_j.network.PCH;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;

public class BaseApplication extends Application {


    private static int kScreenWidth; //屏幕宽度
    private static int kScreenHeight; //屏幕高度
    private static SharedPreferences sharedPreferences;
    private static BaseApplication baseApplication; //application对象
    public static BaseApplication getBaseApplication() {
        return baseApplication;
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }


    /**
     *app启动时执行一次，推送、统计等可以在这里初始化
     */
    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        getResourceScreen(); //获取屏幕宽高，只在app启动时执行一次
    }


    /**
     * 获取屏幕宽高
     */
    private void getResourceScreen(){
        kScreenWidth = getResources().getDisplayMetrics().widthPixels;
        kScreenHeight = getResources().getDisplayMetrics().heightPixels;
        sharedPreferences = getSharedPreferences("BaseApplication",Context.MODE_PRIVATE);
    }

    /**
     * 执行顺序早于onCreate，如果有需要提前处理的可以放到这里
     * @param base
     */
    @Override
    protected void attachBaseContext( Context base ) {
        super.attachBaseContext(base);
        //执行顺序早于onCreate，如果有需要提前处理的可以放到这里
    }


}
