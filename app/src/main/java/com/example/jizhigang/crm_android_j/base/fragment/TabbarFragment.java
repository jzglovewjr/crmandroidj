package com.example.jizhigang.crm_android_j.base.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jizhigang.crm_android_j.R;
import com.example.jizhigang.crm_android_j.base.FragmentEntity;
import com.example.jizhigang.crm_android_j.base.activity.TabbarActivity;
import com.example.jizhigang.crm_android_j.base.widge.BottomBar;
import com.example.jizhigang.crm_android_j.base.widge.BottomBarTab;
import com.example.jizhigang.crm_android_j.base.widge.CustomerViewPager;
import com.example.jizhigang.crm_android_j.customer.fragment.CustomerFragment;
import com.example.jizhigang.crm_android_j.message.fragment.MessageFragment;
import com.example.jizhigang.crm_android_j.mine.adapter.MyFragmentAdapter;
import com.example.jizhigang.crm_android_j.mine.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;


public class TabbarFragment extends Fragment {


    //viewPager
    private CustomerViewPager viewPager;
    private View contentView; //fragment
    private BottomBarTab bottomBarTab1;//客户
    private BottomBarTab bottomBarTab2;//消息
    private BottomBarTab bottomBarTab3;//我的



    public TabbarFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {

        contentView = inflater.inflate(R.layout.fragment_tabbar, container, false);
        //viewPager
        viewPager = (CustomerViewPager)contentView.findViewById(R.id.viewPager);

        bottomBarTab1 = new BottomBarTab(getContext(),R.mipmap.customer_unselected,R.mipmap.customer,"宝贝");
        bottomBarTab2 = new BottomBarTab(getContext(),R.mipmap.message,R.mipmap.message_selected,"消息");
        bottomBarTab3 = new BottomBarTab(getContext(),R.mipmap.mine,R.mipmap.mine_selected,"我的");

        CustomerFragment customerFragment = new CustomerFragment();
        MessageFragment messageFragment = new MessageFragment();
        MineFragment mineFragment = new MineFragment();

        List<FragmentEntity> mListFragmentEntity = new ArrayList<FragmentEntity>();
        mListFragmentEntity.add(getFragmentEntity(customerFragment,"CustomerFragment"));
        mListFragmentEntity.add(getFragmentEntity(messageFragment,"MessageFragment"));
        mListFragmentEntity.add(getFragmentEntity(mineFragment,"MineFragment"));

        viewPager.setAdapter(new MyFragmentAdapter((TabbarActivity)getContext(),mListFragmentEntity));
        viewPager.setCurrentItem(0);
        viewPager.setCanScroll(false); //不要左右滚动
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled( int i, float v, int i1 ) {

            }

            @Override
            public void onPageSelected( int i ) {
                for (int j = 0; j < ((TabbarActivity) getContext()).getBottomBar().getBottomBarTabs().size(); j++){
                    ((TabbarActivity) getContext()).getBottomBar().getBottomBarTabs().get(j).setSelected(false);
                }
                ((TabbarActivity) getContext()).getBottomBar().getBottomBarTabs().get(i).setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged( int i ) {

            }

        });







        ((TabbarActivity) getContext()).getBottomBar().addItem(bottomBarTab1).addItem(bottomBarTab2).addItem(bottomBarTab3);
        ((TabbarActivity) getContext()).getBottomBar().setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            /**
             * 点击tab时调用（点击的和之前选中的不是同一个时执行）
             * @param position 当前选中的索引
             * @param prePosition 被取消的索引
             */
            @Override
            public void onTabSelected( int position, int prePosition ) {
                Log.d("onTabSelected","position="+position+"prePosition="+prePosition);
                viewPager.setCurrentItem(position);
                switch (position){
                    case 0:
                        ((TabbarActivity) getContext()).createTitle("客户");
                        ((TabbarActivity) getContext()).isShowNavigationBar(true);
                        break;
                    case 1:
                        ((TabbarActivity) getContext()).createTitle("消息");
                        ((TabbarActivity) getContext()).isShowNavigationBar(true);
                        break;
                    case 2:
                        ((TabbarActivity) getContext()).createTitle("我的");
                        ((TabbarActivity) getContext()).isShowNavigationBar(true);
                        break;
                }
            }


            /**
             * tab取消选中（点击的和之前选中的不是同一个时执行）
             * @param position 取消选中tab的索引值
             */
            @Override
            public void onTabUnselected( int position ) {
                Log.d("onTabUnselected","position="+position);
            }

            /**
             * 两次点击同一个tab时调用
             * @param position 点击tab的索引
             */
            @Override
            public void onTabReselected( int position ) {
                Log.d("onTabReselected","position="+position);
            }
        });

        //设置默认选中的值
        ((TabbarActivity) getContext()).getBottomBar().getBottomBarTabs().get(0).setSelected(true);

        // Inflate the layout for this fragment
        return contentView;
    }


    /**
     * 将fragment封装为entity
     * @param fragment
     * @param fLabel
     * @return
     */
    private FragmentEntity getFragmentEntity( Fragment fragment, String fLabel ){
        FragmentEntity entity = new FragmentEntity();
        entity.setFragmentLabel(fLabel);
        entity.setmFragment(fragment);
        return entity;
    }


    @Override
    public void onAttach( Context context ) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
