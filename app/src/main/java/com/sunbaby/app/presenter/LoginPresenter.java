package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.AppData;
import com.sunbaby.app.bean.User;
import com.sunbaby.app.callback.ILoginView;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author wangjingbo
 * @date 2018/7/10
 * describe
 */
public class LoginPresenter extends BasePresenter {
    private ILoginView loginView;

    public LoginPresenter(Context context, ILoginView loginView) {
        super(context);
        this.loginView = loginView;
    }

    /**
     * 登录
     *
     * @param userName
     * @param pwd
     */
    public void login(String userName, String pwd) {
        mRequestClient.login(userName, pwd).subscribe(new ProgressSubscriber<User>(mContext) {
            @Override
            public void onNext(User user) {
                if (null != loginView && null != user) {
                    AppData.getInstance().setUser(user);
                    loginView.onLoginSucceed(user);
                }
            }
        });
    }
}
