package com.sunbaby.app.common.base;

import android.content.Context;

import com.sunbaby.app.common.api.RequestClient;

/**
 * com.sunbaby.app.common.base
 *
 * @author 王静波
 * @date 2018/7/6
 * describe
 */
public class BasePresenter {
    /**
     * 上下文
     */
    protected Context mContext;

    /**
     * 网络请求
     */
    protected RequestClient mRequestClient;

    public BasePresenter(Context context){
        mContext = context;
        mRequestClient = RequestClient.getInstance();
    }
}
