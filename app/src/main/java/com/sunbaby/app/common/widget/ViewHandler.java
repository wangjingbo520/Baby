package com.sunbaby.app.common.widget;

import android.view.View;

/**
 * @author 王静波
 * @date 2018/7/23
 * describe
 */
public interface ViewHandler {

    /**
     * 可以在本方法中对 view 进行处理
     *
     * @param viewType view 的类型
     * @param view
     */
    void handleView(int viewType, View view);
}