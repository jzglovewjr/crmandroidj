package com.example.jizhigang.crm_android_j.base.widge;

import android.util.Log;

public class JUtil {

    /**
     * 超长字符串打印，但是分段打印会有每段之间都换行的问题
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg){
        //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
        //  把4*1024的MAX字节打印长度改为2001字符数
        int max_str_length = 2001 - tag.length();
        //大于4000时
        while (msg.length() > max_str_length) {
            Log.i(tag, msg.substring(0, max_str_length));
            msg = msg.substring(max_str_length);
        }
        //剩余部分
        Log.i(tag, msg);

    }

}
