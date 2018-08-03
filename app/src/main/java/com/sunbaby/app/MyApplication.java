package com.sunbaby.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ping.greendao.gen.DaoMaster;
import com.ping.greendao.gen.DaoSession;
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
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appContext = this;
        activities = new ArrayList<>();
        //配置数据库
        setupDatabase();
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
    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //创建数据库shop.db
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "baby.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }

}
