package com.example.jizhigang.crm_android_j.customer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jizhigang.crm_android_j.R;
import com.example.jizhigang.crm_android_j.base.activity.BaseActivity;
import com.example.jizhigang.crm_android_j.customer.fragment.CustomerDetailFragment;

public class CustomerDetailActivity extends BaseActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_customer_detail);
        initData();
        createUI();
    }


    /**
     * 数据初始化
     */
    private void initData(){

    }


    /**
     * 页面初始化
     */
    private void createUI(){
        createTitle("客户详情");
        createBackBtn();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container,new CustomerDetailFragment())
                .commit();
    }




    @Override
    public void leftBtnDidClicked( View view ) {
        super.leftBtnDidClicked(view);
        Intent intent = new Intent();
        setResult(3);
        finish();

    }
}
