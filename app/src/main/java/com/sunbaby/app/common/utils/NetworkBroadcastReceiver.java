package com.sunbaby.app.common.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.sunbaby.app.common.NetworkChangeEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * @author Wangjingbo
 * @date 2018/7/31
 * describe
 */
public class NetworkBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isConnected = NetworkUtils.isNetworkAvailable(context);
        EventBus.getDefault().post(new NetworkChangeEvent(isConnected));
    }
}
