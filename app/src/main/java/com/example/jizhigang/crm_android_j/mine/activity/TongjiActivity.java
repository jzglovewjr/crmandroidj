package com.example.jizhigang.crm_android_j.mine.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.jizhigang.crm_android_j.base.activity.BaseActivity;

public class TongjiActivity extends BaseActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tongji);
        initData();
        createUI();
        Log.d("getClass().getName()",getClass().getName());
    }

    private void initData(){

    }

    private void createUI(){
        createTitle("工作统计");
        createBackBtn();
    }


    @Override
    public void leftBtnDidClicked( View view ) {
        super.leftBtnDidClicked(view);
//        Intent intent = new Intent();
//        setResult(3);
        finish();
    }
}
