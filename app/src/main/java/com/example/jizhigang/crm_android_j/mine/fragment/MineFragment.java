package com.example.jizhigang.crm_android_j.mine.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jizhigang.crm_android_j.R;
import com.example.jizhigang.crm_android_j.mine.activity.TongjiActivity;
import com.example.jizhigang.crm_android_j.mine.activity.TypeSeleActivity;

import java.util.ArrayList;


public class MineFragment extends Fragment {

    ArrayList<Con>listData=new ArrayList<>();
    MyListAdapter myListAdapter;
    View  contentView;



    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        // Inflate the layout for this fragment

        demo();
        contentView = inflater.inflate(R.layout.fragment_mine_home,container,false);
        myListAdapter = new MyListAdapter();
        ListView listView = (ListView)contentView.findViewById(R.id.mineListView);
        listView.setAdapter(myListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {

                if (position == 1){ //工作统计
                    Intent intent = new Intent(getContext(), TongjiActivity.class);
                    startActivityForResult(intent,3);
                }else if (position == 2){//选择状态
                    Intent intent = new Intent(getContext(),TypeSeleActivity.class);
                    startActivityForResult(intent,3);
                }else if (position == 3){//设置
                    Toast.makeText(getContext(),"设置",Toast.LENGTH_LONG).show();
                }


            }
        });

        return contentView;
//        return inflater.inflate(R.layout.fragment_mine_home, container, false);
    }


    @Override
    public void onAttach( Context context ) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onStart() {
        super.onStart();
    }


    /**
     * 数据初始化
     */
    private void demo(){

        listData.add(new Con(R.mipmap.tm_mine_tongji,"",false)); //填充头部数据
        listData.add(new Con(R.mipmap.tm_mine_tongji,"工作统计",true));
        listData.add(new Con(R.mipmap.tm_mine_status,"选择状态",true));
        listData.add(new Con(R.mipmap.tm_mine_setting,"设置",true));


    }


    /**
     * model
     */
    public class Con
    {
        public int leftImg;
        public String nameText;
        public Boolean showRightImg;
          public Con(int leftImg,String nameText, boolean showRightImg){
              this.leftImg = leftImg;
              this.nameText = nameText;
              this.showRightImg = showRightImg;
          }
    }


    /**
     * 适配器
     */
    public class MyListAdapter extends BaseAdapter {

        public static final int VALUE_TYPE_HEAD = 0; //头部布局
        public static final int VALUE_TYPE_OTHER = 1; //其他类型布局

        @Override
        public int getCount() {
            return listData.size();
        }

        @Override
        public Object getItem( int position ) {
            return listData.get(position);
        }

        @Override
        public long getItemId( int position ) {
            return position;
        }

        /**
         * 有多少种布局类型
         * @return
         */
        @Override
        public int getViewTypeCount() {
            return 2;
        }


        /**
         * 对应位置的布局
         * @param position
         * @return
         */
        @Override
        public int getItemViewType( int position ) {
            if (position == 0){
                return VALUE_TYPE_HEAD;
            }
            return VALUE_TYPE_OTHER;
        }

        @Override
        public View getView( int position, View convertView, ViewGroup parent ) {
            int type = getItemViewType(position);
            Con con = listData.get(position);
            HeaderHolder headerHolder = null;
            OtherHolder otherHolder = null;
            if (convertView == null){
                switch (type){
                    case VALUE_TYPE_HEAD:
                        headerHolder = new HeaderHolder();
                        convertView = getLayoutInflater().inflate(R.layout.mine_head_item,parent,false);
                        headerHolder.nameTextView = (TextView)convertView.findViewById(R.id.headerNameTextView);
                        headerHolder.typeTextView = (TextView)convertView.findViewById(R.id.typeTextView);
                        headerHolder.typeImageView = (ImageView)convertView.findViewById(R.id.typeImageView);
                        headerHolder.touxiangImageView = (ImageView)convertView.findViewById(R.id.rightImageView);

                        headerHolder.nameTextView.setText("张菁");
                        headerHolder.typeTextView.setText("请假");
                        headerHolder.typeImageView.setImageResource(R.mipmap.tm_mine_qingjia);
                        headerHolder.touxiangImageView.setImageResource(R.mipmap.zhangjingtouxiang);

                        convertView.setTag(headerHolder);
                        break;
                    case VALUE_TYPE_OTHER:
                        otherHolder = new OtherHolder();
                        convertView = getLayoutInflater().inflate(R.layout.mine_other_item,parent,false);

                        otherHolder.leftImage = (ImageView)convertView.findViewById(R.id.leftImageView);
                        otherHolder.textView = (TextView)convertView.findViewById(R.id.leftTextView);
                        otherHolder.rightImage = (ImageView)convertView.findViewById(R.id.rightImageview);

                        otherHolder.leftImage.setImageResource(con.leftImg);
                        otherHolder.textView.setText(con.nameText);
                        if (con.showRightImg){
                            otherHolder.rightImage.setVisibility(View.VISIBLE);
                        }else {
                            otherHolder.rightImage.setVisibility(View.INVISIBLE);
                        }
                        convertView.setTag(otherHolder);
                        break;
                        default:
                            break;
                }
            }else {
                switch (type){
                    case VALUE_TYPE_HEAD:
                        headerHolder = (HeaderHolder)convertView.getTag();
                        headerHolder.nameTextView.setText("张菁");
                        headerHolder.typeTextView.setText("请假");
                        headerHolder.typeImageView.setImageResource(R.mipmap.tm_mine_qingjia);
                        headerHolder.touxiangImageView.setImageResource(R.mipmap.zhangjingtouxiang);
                        break;
                    case VALUE_TYPE_OTHER:
                        otherHolder = (OtherHolder)convertView.getTag();

                        otherHolder.leftImage.setImageResource(con.leftImg);
                        otherHolder.textView.setText(con.nameText);
                        if (con.showRightImg){
                            otherHolder.rightImage.setVisibility(View.VISIBLE);
                        }else {
                            otherHolder.rightImage.setVisibility(View.INVISIBLE);
                        }
                        break;
                    default:
                        break;
                }
            }

            return convertView;

        }


        /**
         * 头部布局holder
         */
        class HeaderHolder{
            private TextView nameTextView; //姓名
            private TextView typeTextView; //接单状态
            private ImageView typeImageView; //接单状态的图标
            private ImageView touxiangImageView; //头像
        }


        /**
         * 其他布局holder
         */
        class OtherHolder{
            private ImageView leftImage;
            private TextView textView;
            private ImageView rightImage;
        }


    }

}
