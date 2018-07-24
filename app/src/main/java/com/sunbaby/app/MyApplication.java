package com.sunbaby.app;

import android.app.Application;
import android.content.Context;

/**
 * com.sunbaby.app
 *
 * @author wangjingbo
 * @date 2018/7/6
 * describe
 */
public class MyApplication extends Application {
    public static Context context;
    public static MyApplication appContext;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appContext = this;
    }

    public static MyApplication getInstance() {
        if (appContext == null) {
        }
        return appContext;
    }
}
