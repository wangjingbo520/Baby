package com.sunbaby.app.common.utils;

import android.widget.Toast;

import com.sunbaby.app.MyApplication;

/**
 * com.sunbaby.app.common.utils
 *
 * @author 王静波
 * @date 2018/7/6
 * describe
 */
public class ToastUtil {
    private static Toast mToast;

    private static void init(){
        mToast= Toast.makeText(MyApplication.context,"", Toast.LENGTH_SHORT);
    }

    public static void show(String tip){
        if (mToast==null){
            init();
        }
        mToast.setText(tip);
        mToast.show();
    }
}
