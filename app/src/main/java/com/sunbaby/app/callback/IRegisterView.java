package com.sunbaby.app.callback;

import com.sunbaby.app.bean.User;

/**
 * @author 王静波
 * @date 2018/7/10
 * describe 注册
 */
public interface IRegisterView {
    public void onGetCodeSucceed();

    public void onRegisterSucceed(User user);
}
