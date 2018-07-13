package com.sunbaby.app.common.api;

import android.content.Context;


import com.sunbaby.app.common.utils.NetworkUtils;
import com.sunbaby.app.common.utils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

public abstract class ProgressSubscriber<T> extends Subscriber<T> implements ProgressDialogListener {

    /**
     * 上下文
     */
    private Context context;

    private ProgressDialogHandler mProgressDialogHandler;

    private boolean showDialog = true;

    public ProgressSubscriber(Context context) {
        this.context = context;
        this.mProgressDialogHandler = new ProgressDialogHandler(false, this, context);
    }

    public ProgressSubscriber(Context context, boolean showDialog) {
        this.context = context;
        this.mProgressDialogHandler = new ProgressDialogHandler(false, this, context);
        this.showDialog = showDialog;
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }


    @Override
    public void onStart() {

        if (!NetworkUtils.isNetworkAvailable(context)) {
            ToastUtil.showMessage("网络中断，请检查您的网络状态");
            if (!this.isUnsubscribed()) {
                this.unsubscribe();
            }
            onFinish();
            return;
        }
        if (showDialog) {
            showProgressDialog();
        }
    }


    @Override
    public void onCompleted() {
        dismissProgressDialog();
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof SocketTimeoutException) {
            ToastUtil.showMessage("网络中断，请检查您的网络状态");
        } else if (e instanceof ConnectException) {
            ToastUtil.showMessage("网络中断，请检查您的网络状态");
        } else if (e instanceof ApiException) {
            ApiException ae = (ApiException) e;
            String type = ae.type;
            if ("-100".equals(type)) {
                //弹出互踢对话框，另外处理
//                DropOutActivity.startDropOutActivity(e.getMessage(), context);
            } else {
                ToastUtil.showMessage(e.getMessage());
            }
        } else {
            ToastUtil.showMessage("服务器异常！！！");
        }
        dismissProgressDialog();
        onFinish();
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

    //    add by lijun 2017/8/10 16:25      处理因网络请求状态异常而不能关闭列表刷新状态的问题
    public void onFinish() {
        //根据具体业务场景重写该方法以实现自己的需求
    }

}
