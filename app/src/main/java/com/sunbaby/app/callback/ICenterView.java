package com.sunbaby.app.callback;

import com.sunbaby.app.bean.CenterBean;

/**
 * @author wangjingbo
 * @date 2018/7/17
 * describe 个人中心
 */
public interface ICenterView {
    void homePage(CenterBean centerBean);
    public void onFinish();
}
