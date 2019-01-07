package com.example.jizhigang.crm_android_j.base.widge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomerViewPager extends ViewPager {

    private boolean isCanScroll = false;



    //这两个构造方法都不能少
    public CustomerViewPager( @NonNull Context context ) {
        super(context);
    }
    public CustomerViewPager(Context context, AttributeSet attrs){
        super(context,attrs);
    }


    public void setCanScroll( boolean canScroll ) {
        isCanScroll = canScroll;
    }

    @Override
    public boolean onInterceptTouchEvent( MotionEvent ev ) {
        return isCanScroll && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent( MotionEvent ev ) {
        return isCanScroll && super.onTouchEvent(ev);
    }
}
