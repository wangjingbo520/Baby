package com.sunbaby.app.common.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.sunbaby.app.AppData;
import com.sunbaby.app.MyApplication;
import com.sunbaby.app.R;
import com.sunbaby.app.bean.User;
import com.sunbaby.app.common.NetworkChangeEvent;
import com.sunbaby.app.common.utils.DialogWithYesOrNoUtils;
import com.sunbaby.app.common.utils.NetworkBroadcastReceiver;
import com.sunbaby.app.common.utils.NetworkUtils;
import com.sunbaby.app.common.utils.ToastUtil;
import com.sunbaby.app.ui.activity.LoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author wangjingbo
 * @date 2018/7/19
 * describe 主要用来存储一些方法
 */
public class MyBaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected boolean mCheckNetWork = true;
    View mTipView;
    private TextView tvClick;
    WindowManager mWindowManager;
    WindowManager.LayoutParams mLayoutParams;
    public NetworkBroadcastReceiver netBroadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        MyApplication.getInstance().addActivity(this);
        netBroadcastReceiver = new NetworkBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netBroadcastReceiver, filter);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在无网络情况下打开APP时，系统不会发送网络状况变更的Intent，需要自己手动检查
        hasNetWork(NetworkUtils.isNetworkAvailable(mContext));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().removeActivity(this);
        unregisterReceiver(netBroadcastReceiver);
        EventBus.getDefault().unregister(this);
    }

    public User getUser() {
        return AppData.getInstance().getUser();
    }

    public String getUserId() {
        return AppData.getInstance().getUser().getUserId() + "";
    }

    public void startTo(Class c, boolean isFinish) {
        startActivity(new Intent(this, c));
        if (isFinish) {
            this.finish();
        }
    }

    public boolean userIsLogin(final boolean startToLogin) {
        User user = AppData.getInstance().getUser();
        if (null == user) {
            if (!((Activity) mContext).isFinishing()) {
                //show dialog
                DialogWithYesOrNoUtils.showDialog(this, "请先进行登录", new DialogWithYesOrNoUtils
                        .DialogCallBack() {
                    @Override
                    public void exectEvent() {
                        startTo(LoginActivity.class, startToLogin);
                    }
                });
            }
            return false;
        }
        return true;
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetworkChangeEvent(NetworkChangeEvent event) {
        hasNetWork(event.isConnected);
    }

    private void hasNetWork(boolean has) {
        if (isCheckNetWork()) {
            if (has) {
            } else {
                ToastUtil.showMessage("网络已断开,无法进行数据访问");
            }
        }
    }

    public void setCheckNetWork(boolean checkNetWork) {
        mCheckNetWork = checkNetWork;
    }

    public boolean isCheckNetWork() {
        return mCheckNetWork;
    }

    private void initTipView() {
//        LayoutInflater inflater = getLayoutInflater();
//        mTipView = inflater.inflate(R.layout.layout_network_tip, null);
//        tvClick = mTipView.findViewById(R.id.tvClick);
//        mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
//        mLayoutParams = new WindowManager.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.TYPE_APPLICATION,
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                PixelFormat.TRANSLUCENT);
//        //使用非CENTER时，可以通过设置XY的值来改变View的位置
//        mLayoutParams.gravity = Gravity.TOP;
//        mLayoutParams.x = 0;
//        mLayoutParams.y = 100;
    }
}
