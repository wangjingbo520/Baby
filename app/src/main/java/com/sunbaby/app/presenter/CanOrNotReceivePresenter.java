package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.bean.CanReceiveBean;
import com.sunbaby.app.callback.ICanReceiveView;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author Wangjingbo
 * @date 2018/8/1
 * describe
 */
public class CanOrNotReceivePresenter extends BasePresenter {
    private ICanReceiveView receiveView;

    public CanOrNotReceivePresenter(Context context, ICanReceiveView receiveView) {
        super(context);
        this.receiveView = receiveView;
    }

    /**
     * 修改手机号码原手机号码能接受验证码初始化
     */
    public void updateMobileInit() {
        mRequestClient.updateMobileInit().subscribe(new ProgressSubscriber<CanReceiveBean>
                (mContext) {
            @Override
            public void onNext(CanReceiveBean canReceiveBean) {
                if (null != receiveView) {
                    receiveView.updateMobileInit(canReceiveBean);
                }
            }
        });
    }

    /**
     * 修改手机号码发送短信验证
     *
     * @param mobile
     * @param scene
     */
    public void sendSmsUpdataPhoneNumber(String mobile, String scene) {
        mRequestClient.sendSmsUpdataPhoneNumber(mobile, scene).subscribe(new ProgressSubscriber<Object>
                (mContext) {
            @Override
            public void onNext(Object object) {
                if (null != receiveView) {
                    receiveView.sendSmsUpdataPhoneNumber(object);
                }
            }
        });
    }


    /**
     * 修改手机号码短信验证验证
     *
     * @param mobile
     * @param scene
     * @param code
     */
    public void updateMobilesVerify(String mobile, String code, String scene) {
        mRequestClient.updateMobilesVerify(mobile, code, scene).subscribe(new ProgressSubscriber<Object>
                (mContext) {
            @Override
            public void onNext(Object object) {
                if (null != receiveView) {
                    receiveView.updateMobilesVerify(object);
                }
            }
        });
    }


    /**
     * @param mobile
     * @param scene
     * @param code
     * @param password 原手机号码能接受短信，不传，原手机号码不能接受短信的时候必传
     */
    public void updateMobileSave(String mobile, String scene, String code, String password) {
        mRequestClient.updateMobileSave(mobile, scene, code,password).subscribe(new ProgressSubscriber<Object>
                (mContext) {
            @Override
            public void onNext(Object object) {
                if (null != receiveView) {
                    receiveView.updateMobileSave(object);
                }
            }
        });
    }


}
