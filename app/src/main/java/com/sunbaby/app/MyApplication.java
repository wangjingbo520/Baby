package com.sunbaby.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.sunbaby.app.ui.activity.LoginActivity;

import java.util.ArrayList;
import java.util.List;

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
    private List<Activity> activities;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appContext = this;
        activities = new ArrayList<>();
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public void extiApp() {
        for (Activity activity : activities) {
            activity.finish();
        }
    }

    public void extiLoginApp() {
        for (Activity activity : activities) {
            if (activity.equals(LoginActivity.class)) {
                continue;
            }
            activity.finish();
        }
    }

    public static MyApplication getInstance() {
        if (appContext == null) {
        }
        return appContext;
    }
}
