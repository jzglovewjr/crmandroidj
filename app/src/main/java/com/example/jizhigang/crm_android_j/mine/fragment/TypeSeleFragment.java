package com.example.jizhigang.crm_android_j.mine.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jizhigang.crm_android_j.R;

import java.lang.reflect.Field;

public class TypeSeleFragment extends Fragment {

    //整个activity的布局
    public RelativeLayout rootLayout;
    //整个navigationBar和statusBar的背景视图，实现navigationBar和statusbar连为一体的效果
    public RelativeLayout navBackViewLayout;
    //填充statusbar背景的view 设置为透明色
    public View statusBarBackView ;
    //navigationBar的背景布局
    public RelativeLayout navLayout;
    //navigationBar中间的标题
    public TextView titleTextView; //标题
    //navigationbar左侧按钮 （返回按钮，可设置图片和文字）
    public Button leftBtn;//返回按钮
    //navigationBar右侧按钮
    public Button rightBtn;//右侧按钮
    //导航栏分割线
    public View navSepLine;


    View  contentView;


    public TypeSeleFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {

        contentView = inflater.inflate(R.layout.fragment_type_sele,container,false);
        //整个activity的布局
        rootLayout = (RelativeLayout)contentView.findViewById(R.id.main_root_layout);
        //整个navigationBar和statusBar的背景视图，实现navigationBar和statusbar连为一体的效果
        navBackViewLayout = (RelativeLayout)contentView.findViewById(R.id.navigationBarBackView);
        //填充statusbar背景的view 设置为透明色
        statusBarBackView = (View)contentView.findViewById(R.id.navigationBarTopClearView);
        //navigationBar的背景布局
        navLayout = (RelativeLayout)contentView.findViewById(R.id.navigationBar);
        //navigationBar中间的标题
        titleTextView = (TextView)contentView.findViewById(R.id.nav_text_title); //标题
        //navigationbar左侧按钮 （返回按钮，可设置图片和文字）
        leftBtn = (Button)contentView.findViewById(R.id.button_backward);//返回按钮
        leftBtn.setVisibility(View.VISIBLE);
        //navigationBar右侧按钮
        rightBtn = (Button)contentView.findViewById(R.id.button_forward);//右侧按钮
        //导航栏分割线
        navSepLine = (View)contentView.findViewById(R.id.nav_bottom_line);


        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                if (v.getId() == R.id.button_backward){
                    getActivity().finish();
                }
            }
        });


        initStatusBar();
        // Inflate the layout for this fragment
        return contentView;
    }


    @Override
    public void onAttach( Context context ) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }





    /**
     * 根据实际情况适配navigationBar
     */
    private void initStatusBar(){
        //让布局扩展到statusbar后面
        View decorView  = getActivity().getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);


        //根据实际设备动态修改statusbar背景填充区域高度，使得navigationbar能正确显示
        Class c = null;
        int statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = this.getResources().getDimensionPixelSize(x);
            Log.d("状态栏绝对高度为（单位px）", String.valueOf(statusBarHeight));

        } catch (Exception e) {
            e.printStackTrace();
            statusBarHeight = 20;
        }

        //设置状态栏背景填充高度
        ViewGroup.LayoutParams layoutParams = statusBarBackView.getLayoutParams();
        layoutParams.height = statusBarHeight;
        statusBarBackView.setLayoutParams(layoutParams);

        ViewGroup.LayoutParams navLayoutPara = navBackViewLayout.getLayoutParams();
        navLayoutPara.height = navLayout.getLayoutParams().height + statusBarHeight + navSepLine.getLayoutParams().height;
        navBackViewLayout.setLayoutParams(navLayoutPara);
    }


}
