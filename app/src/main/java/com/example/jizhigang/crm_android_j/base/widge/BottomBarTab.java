package com.example.jizhigang.crm_android_j.base.widge;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jizhigang.crm_android_j.R;

/**
 * 作者：weidingqiang on 2017/9/7 16:27
 * 邮箱：dqwei@iflytek.com
 */

public class BottomBarTab extends LinearLayout {
    private ImageView mIcon;
    private TextView mTvTitle;
    private Context mContext;
    private int mTabPosition = -1;

//    private TextView mTvUnreadCount;

    private int sicon;
    private int icon;
    private boolean mIsSelected;

    public BottomBarTab( Context context, @DrawableRes int icon, CharSequence title) {
        this(context, null, icon, title);
    }

    //icon unselected sicon selected
    public BottomBarTab( Context context, @DrawableRes int icon, @DrawableRes int sicon, CharSequence title) {
        this(context, null, icon, title);
        this.sicon = sicon;
        this.icon = icon;
    }

    public void setSicon( @DrawableRes int icon, @DrawableRes int sicon) {
        this.sicon = sicon;
        this.icon = icon;
        if (mIsSelected) {
            mIcon.setImageResource(sicon);
        } else {
            mIcon.setImageResource(icon);
        }
    }

    public BottomBarTab( Context context, AttributeSet attrs, int icon, CharSequence title) {
        this(context, attrs, 0, icon, title);
    }

    public BottomBarTab( Context context, AttributeSet attrs, int defStyleAttr, int icon, CharSequence title) {
        super(context, attrs, defStyleAttr);
        init(context, icon, title);
    }

    private void init( Context context, int icon, CharSequence title) {
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(new int[]{R.attr.selectableItemBackgroundBorderless});
        Drawable drawable = typedArray.getDrawable(0);
        setBackgroundDrawable(drawable);
        typedArray.recycle();

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.bottom_bar_tab, this, true);

        mIcon = (ImageView) this.findViewById(R.id.mIcon);

        mTvTitle = (TextView) this.findViewById(R.id.mTvTitle);
        mTvTitle.setText(title);

    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        mIsSelected = selected;
        if (selected) {
            mIcon.setImageResource(sicon);

//            mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.colorPrimary));
            mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.bottom_text_selected));
        } else {
//            mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.tab_unselect));
            mIcon.setImageResource(icon);
            mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.bottom_text_unselected));
        }
    }

    public void setTabPosition(int position) {
        mTabPosition = position;
        setSelected(false);

    }

    public int getTabPosition() {
        return mTabPosition;
    }

}