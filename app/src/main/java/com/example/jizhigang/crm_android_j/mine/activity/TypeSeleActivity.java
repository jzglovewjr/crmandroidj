package com.example.jizhigang.crm_android_j.mine.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.jizhigang.crm_android_j.R;
import com.example.jizhigang.crm_android_j.base.activity.BaseActivity;
import com.example.jizhigang.crm_android_j.mine.fragment.TypeSeleFragment;

public class TypeSeleActivity extends BaseActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_type_sele);
        initData();
        createUI();

        Log.d("getClass().getName()",getClass().getName());
    }

    private void initData(){

    }
    private void createUI(){
        isShowNavigationBar(false);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container,new TypeSeleFragment())
                .commit();

    }


    @Override
    public void leftBtnDidClicked( View view ) {
        super.leftBtnDidClicked(view);
        finish();
    }
}
