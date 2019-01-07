package com.example.jizhigang.crm_android_j.base.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jizhigang.crm_android_j.R;
import com.example.jizhigang.crm_android_j.base.fragment.TabbarFragment;
import com.example.jizhigang.crm_android_j.base.widge.BottomBar;

import java.lang.reflect.Field;


/**
 * activity的基类
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener {


    //整个activity的布局
    private RelativeLayout rootLayout;
    //整个navigationBar和statusBar的背景视图，实现navigationBar和statusbar连为一体的效果
    private RelativeLayout navBackViewLayout;
    //填充statusbar背景的view 设置为透明色
    private View statusBarBackView ;
    //navigationBar的背景布局
    private RelativeLayout navLayout;
    //navigationBar中间的标题
    private TextView titleTextView; //标题
    //navigationbar左侧按钮 （返回按钮，可设置图片和文字）
    private Button leftBtn;//返回按钮
    //navigationBar右侧按钮
    private Button rightBtn;//右侧按钮
    //导航栏分割线
    private View navSepLine;

    //底部tabbar的布局
    private RelativeLayout tabBarLayout;
    //tabbar上边的分割线
    private View tabbarTopLineView;
    //tabbar
    private BottomBar bottomBar;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initViews();
        initStatusBar();


    }


    /**
     * 给中间内容显示区域赋值
     * @param fragment
     */
    public void setContentFragment( Fragment fragment ){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();
        }
    }



    /**
     * 页面初始化
     */
    private void initViews(){
        //整个activity的布局
        rootLayout = (RelativeLayout)findViewById(R.id.main_root_layout);
        //整个navigationBar和statusBar的背景视图，实现navigationBar和statusbar连为一体的效果
        navBackViewLayout = (RelativeLayout)findViewById(R.id.navigationBarBackView);
        //填充statusbar背景的view 设置为透明色
        statusBarBackView = (View)findViewById(R.id.navigationBarTopClearView);
        //navigationBar的背景布局
        navLayout = (RelativeLayout)findViewById(R.id.navigationBar);
        //navigationBar中间的标题
        titleTextView = (TextView)findViewById(R.id.nav_text_title); //标题
        //navigationbar左侧按钮 （返回按钮，可设置图片和文字）
        leftBtn = (Button)findViewById(R.id.button_backward);//返回按钮
        //navigationBar右侧按钮
        rightBtn = (Button)findViewById(R.id.button_forward);//右侧按钮
        //导航栏分割线
        navSepLine = (View)findViewById(R.id.nav_bottom_line);

        //底部tabbar的布局
        tabBarLayout = (RelativeLayout)findViewById(R.id.bottomBarLayout);
        //tabbar上边的分割线
        tabbarTopLineView = (View)findViewById(R.id.bottomTopLineView);
        //tabbar
        bottomBar = (BottomBar)findViewById(R.id.bottomBar);
        isShowTabbar(false); //默认隐藏tabbar只有首页需要手动显示出来
    }


    /**
     * 根据实际情况适配navigationBar
     */
    private void initStatusBar(){
        //让布局扩展到statusbar后面
        View decorView  = getWindow().getDecorView();
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




    @Override
    public void onClick( View v ) {
        if (v.getId()==R.id.button_backward){//返回按钮
            leftBtnDidClicked(v);
        }else if (v.getId()==R.id.button_forward){//右侧按钮
            rightBtnDidClicked(v);
        }
    }


    /**
     * 点击左侧按钮的响应方法，在子类中做具体操作
     * @param view
     */
    public void leftBtnDidClicked(View view){

    }

    /**
     * 点击右侧按钮的响应方法 在子类中做具体操作
     * @param view
     */
    public void rightBtnDidClicked(View view){

    }


    /**
     * 显示navigationbar上的title信息
     * @param title
     */
    public void createTitle(String title){
        this.titleTextView.setText(title);
    }


    /**
     * 显示返回按钮
     */
    public void createBackBtn(){
        leftBtn.setVisibility(View.VISIBLE);
    }


    /**
     * 设置显示/隐藏navigationbar
     * @param isShow true显示 false隐藏
     */
    public void isShowNavigationBar( boolean isShow ){
        if (navBackViewLayout!=null){
            if (isShow){ //显示navigationbar
                navBackViewLayout.setVisibility(View.VISIBLE);
            }else {//隐藏navigationbar
                navBackViewLayout.setVisibility(View.GONE);
            }
        }
    }


    /**
     * 是否显示tabbar
     * @param isShow true显示tabbar false不显示tabbar
     */
    public void isShowTabbar( boolean isShow ){
        if (isShow){
            tabBarLayout.setVisibility(View.VISIBLE);
        }else {
            tabBarLayout.setVisibility(View.GONE);
        }
    }


    /**
     * 获取底部tabbar
     * @return
     */
    public BottomBar getBottomBar() {
        return bottomBar;
    }
}
