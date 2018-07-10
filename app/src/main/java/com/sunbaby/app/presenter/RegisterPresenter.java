package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.callback.IRegisterView;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author 王静波
 * @date 2018/7/10
 * describe 注册
 */
public class RegisterPresenter extends BasePresenter {
    private IRegisterView iRegisterView;

    public RegisterPresenter(Context context, IRegisterView iRegisterView) {
        super(context);
        this.iRegisterView = iRegisterView;
    }


//    public void registerOPT102(String userName, String mobile, String smsCode, String password, String confirmPassword, String recommendCode, String email, String emailCode, String registerType, boolean readandagree) {
//        mRequestClient.registerOPT102(userName, mobile, smsCode, password, confirmPassword, recommendCode, email, emailCode, registerType, readandagree).subscribe(new ProgressSubscriber<User>(mContext) {
//            @Override
//            public void onNext(User user) {
//                if (null != view) {
//                    AppData.getInstance().setUser(user);
//                    view.onRegisterSucceed(user);
//                }
//            }
//        });
//    }
//
//    public void getCodeOPT104(String email, String scene, int is_company) {
//
//        mRequestClient.getCodeOPT104(email, scene, is_company).subscribe(new ProgressSubscriber<Object>(mContext) {
//            @Override
//            public void onNext(Object object) {
//                if (null != view) {
//                    view.onGetCodeSucceed();
//                }
//            }
//        });
//    }

}
