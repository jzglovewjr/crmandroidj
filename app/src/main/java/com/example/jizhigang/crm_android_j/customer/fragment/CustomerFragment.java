package com.example.jizhigang.crm_android_j.customer.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.jizhigang.crm_android_j.R;
import com.example.jizhigang.crm_android_j.base.activity.TabbarActivity;
import com.example.jizhigang.crm_android_j.customer.activity.CustomerDetailActivity;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;


public class CustomerFragment extends Fragment implements View.OnClickListener {

    View contentView;
    SpringView springView;
    ListView listView;
    ListViewAdapter adapter;
    int num = 10;

    public CustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated( @Nullable Bundle savedInstanceState ) {
        super.onActivityCreated(savedInstanceState);

//        Button button = (Button)((TabbarActivity)getContext()).findViewById(R.id.btn);
//
//        button.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick( View v ) {
//                Intent intent = new Intent(getContext(), CustomerDetailActivity.class);
//                startActivityForResult(intent,3);
//            }
//        });
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {

        contentView = inflater.inflate(R.layout.fragment_customer_home, container, false);
        
        springView = contentView.findViewById(R.id.springView);
        springView.setHeader(new DefaultHeader(getContext()));
        springView.setFooter(new DefaultFooter(getContext()));

        listView = contentView.findViewById(R.id.customerListView);
        adapter = new ListViewAdapter();
        listView.setAdapter(adapter);

        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() { //下拉
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        num = 5;
                        adapter.notifyDataSetChanged();
                        springView.onFinishFreshAndLoad();
                    }
                },1000);
            }

            @Override
            public void onLoadmore() {//上拉
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        num += 5;
                        adapter.notifyDataSetChanged();
                        springView.onFinishFreshAndLoad();
                    }
                },1000);
            }
        });
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



    public void onClick(View view){
        Log.d("点击跳转按钮","");
        Intent intent = new Intent(getContext(), CustomerDetailActivity.class);
        startActivityForResult(intent,3);
    }



    public class ListViewAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return num;
        }

        @Override
        public Object getItem( int position ) {
            return 1;
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
