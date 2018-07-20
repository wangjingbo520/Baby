package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.AppData;
import com.sunbaby.app.bean.User;
import com.sunbaby.app.callback.IForgetView;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author 王静波
 * @date 2018/7/19
 * describe
 */
public class ForgetpasswordPresenter extends BasePresenter {
    private IForgetView iForgetView;

    public ForgetpasswordPresenter(Context context, IForgetView iForgetView) {
        super(context);
        this.iForgetView = iForgetView;
    }

    /**
     * 获取短信验证码
     *
     * @param mobile
     * @param scene
     */
    public void sendSms(String mobile, String scene) {
        mRequestClient.sendSms(mobile, scene).subscribe(new ProgressSubscriber<Object>
                (mContext) {
            @Override
            public void onNext(Object object) {
                if (null != iForgetView) {
                    iForgetView.onGetCodeSucceed();
                }
            }
        });
    }

    /**
     * @param mobile      手机号
     * @param code        验证码
     * @param scene       场景
     * @param passWordNoe 密码
     * @param passWordTwo 确认密码
     */
    public void forgetPassword(String mobile, String code, String scene, String passWordNoe,
                               String passWordTwo) {
        mRequestClient.forgetPassword(mobile, code, scene, passWordNoe, passWordTwo).subscribe
                (new ProgressSubscriber<Object>(mContext) {
                    @Override
                    public void onNext(Object object) {
                        if (null != iForgetView) {
                            iForgetView.forgetPassword();
                        }
                    }
                });
    }

    /**
     * 短信验证
     * @param mobile
     * @param code
     * @param scene
     */
     public void updateMobilesVerify(String mobile, String code, String scene) {
        mRequestClient.updateMobilesVerify(mobile, code, scene).subscribe
                (new ProgressSubscriber<Object>(mContext) {
                    @Override
                    public void onNext(Object object) {
                        if (null != iForgetView) {
                            iForgetView.updateMobilesVerify();
                        }
                    }
                });
    }





}
