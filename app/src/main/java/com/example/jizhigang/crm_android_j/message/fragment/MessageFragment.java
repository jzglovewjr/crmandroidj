package com.example.jizhigang.crm_android_j.message.fragment;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.jizhigang.crm_android_j.R;
import com.example.jizhigang.crm_android_j.base.activity.TabbarActivity;
import com.example.jizhigang.crm_android_j.base.dao.BaseDao;
import com.example.jizhigang.crm_android_j.message.dao.MessageListDao;
import com.example.jizhigang.crm_android_j.network.MyCallBack;
import com.example.jizhigang.crm_android_j.network.NetWorkTool;
import com.example.jizhigang.crm_android_j.network.PCH;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.io.IOException;
import java.util.EmptyStackException;
import java.util.zip.Inflater;

import okhttp3.Response;

public class MessageFragment extends Fragment {

    private SpringView springView; //列表的上拉和下拉组建
    private ListView listView; //列表
    private EditText searchEditText; //搜索框
    private MessageAdapter messageAdapter; //listView适配器
    private View contentView; //self.view

    private Handler handler = new Handler();

    private String searchTitle; //搜索框中的数据
    private int pageNo = 1; //默认从第一页开始请求数据
    private int pageSize = 10; //请求数据默认每页10条
    private MessageListDao mMessageListDao = new MessageListDao();

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {

        contentView = inflater.inflate(R.layout.fragment_message_home, container, false);
        springView = contentView.findViewById(R.id.springView);
        springView.setHeader(new DefaultHeader(getContext()));
        springView.setFooter(new DefaultFooter(getContext()));
        listView = contentView.findViewById(R.id.messageListView);
        searchEditText = contentView.findViewById(R.id.messageSearchEditText);
        searchEditText.addTextChangedListener(new TextWatcher() {//添加文本输入监听器
            @Override
            public void beforeTextChanged( CharSequence s, int start, int count, int after ) {
                Log.d("beforeTextChanged","s="+s.toString()+" start="+start+" count="+count+" after="+after);
            }

            @Override
            public void onTextChanged( CharSequence s, int start, int before, int count ) {
                Log.d("onTextChanged","s="+s.toString()+" start="+start+" count="+count);
            }

            @Override
            public void afterTextChanged( Editable s ) {
                Log.d("afterTextChanged","s="+s.toString());
            }
        });
        messageAdapter = new MessageAdapter();
        listView.setAdapter(messageAdapter);


        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                pageNo = 1;
                requestList();
                springView.setEnableFooter(true); //有上拉功能
            }

            @Override
            public void onLoadmore() {
                //如果目前没有数据，那么上拉可以作为下拉处理，请求第一页的数据
                if (mMessageListDao.getData().getList().size() == 0){
                    pageNo = 1;
                    requestList();
                }else {
                    pageNo += 1;
                    requestList();
                }
            }
        });



        requestList();
        // Inflate the layout for this fragment
        return contentView;
    }



    @Override
    public void onDetach() {
        super.onDetach();
    }


    /**
     * 列表数据请求
     */
    private void requestList(){

        String urlString = PCH.requestMessageListAction + "&pageNo=" + pageNo + "&pageSize=" + pageSize;
        if (searchTitle != null){ //有搜索内容
            urlString += "&title=" + searchTitle;
        }

        NetWorkTool.request(urlString, null, PCH.mHttpRequestGet, new MyCallBack<MessageListDao>() {
            @Override
            public void onSuccess( MessageListDao messageListDao, String responseString, Response response ) {
                Log.d("获取消息列表成功", String.valueOf(messageListDao));
                if (pageNo == 1){ //下拉
                    mMessageListDao = messageListDao;
                }else {//上拉
                    mMessageListDao.getData().setCount(messageListDao.getData().getCount());
                    mMessageListDao.getData().setPageCount(messageListDao.getData().getPageCount());
                    mMessageListDao.getData().setPageNo(messageListDao.getData().getPageNo());
                    mMessageListDao.getData().setPageSize(messageListDao.getData().getPageSize());
                    mMessageListDao.getData().loadMoreInsertList(messageListDao.getData().getList());
                }


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //刷新列表并停止loading
                        messageAdapter.notifyDataSetChanged();
                        springView.onFinishFreshAndLoad();
                    }
                },1);

                //请求完数据了
                if (pageNo >= mMessageListDao.getData().getPageCount()){
                    springView.setEnableFooter(false);
                }

            }

            @Override
            public void onError( String errString, IOException e ) {
                Log.d("error", errString);
            }
        });
    }


    public class MessageAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mMessageListDao.getData().getList().size();
        }

        @Override
        public Object getItem( int position ) {
            return null;
        }

        @Override
        public long getItemId( int position ) {
            return 0;
        }

        @Override
        public View getView( int position, View convertView, ViewGroup parent ) {
            convertView = getLayoutInflater().inflate(R.layout.message_view_item,parent,false);
            return convertView;
        }
    }


}
