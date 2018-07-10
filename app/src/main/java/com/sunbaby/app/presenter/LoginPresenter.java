package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.callback.ILoginView;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author 王静波
 * @date 2018/7/10
 * describe
 */
public class LoginPresenter extends BasePresenter {
    private ILoginView loginView;

    public LoginPresenter(Context context, ILoginView loginView) {
        super(context);
        this.loginView = loginView;
    }


//    public void login(String mobile, String password, int is_company) {
//        mRequestClient.login(mobile, password, is_company).subscribe(new
// ProgressSubscriber<User>(mContext) {
//            @Override
//            public void onNext(User user) {
//                if (null != loginView && null != user) {
//                    AppData.getInstance().setUser(user);
//                    loginView.onSucceed(user);
//                }
//            }
//        });
//    }
}
