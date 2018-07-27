package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.bean.AlipayBean;
import com.sunbaby.app.bean.PayBean;
import com.sunbaby.app.bean.PersonBean;
import com.sunbaby.app.bean.WeChatPayBean;
import com.sunbaby.app.callback.IMypayView;
import com.sunbaby.app.callback.IPersonView;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author wangjingbo
 * @date 2018/7/23
 * describe
 */
public class MyPayPresenter extends BasePresenter {
    private IMypayView iMypayView;

    public MyPayPresenter(Context context, IMypayView iMypayView) {
        super(context);
        this.iMypayView = iMypayView;
    }


    /**
     * 支付方式
     */
    public void queryPayMethod() {
        mRequestClient.queryPayMethod().subscribe(new ProgressSubscriber<PayBean>
                (mContext) {
            @Override
            public void onNext(PayBean payBean) {
                if (null != iMypayView) {
                    iMypayView.queryPayMethod(payBean);
                }
            }
        });
    }

    /**
     * 微信订单支付
     *
     * @param userId
     * @param orderId
     */
    public void wechatPayBefore(String userId, String orderId) {
        mRequestClient.wechatPayBefore(userId, orderId).subscribe(new ProgressSubscriber<WeChatPayBean>
                (mContext) {
            @Override
            public void onNext(WeChatPayBean weChatPayBean) {
                if (null != iMypayView) {
                    iMypayView.wechatPayBefore(weChatPayBean);
                }
            }
        });
    }

    /**
     * 支付宝支付
     * @param userId
     * @param orderId
     */
    public void alipayBefore(String userId, String orderId) {
        mRequestClient.alipayBefore(userId, orderId).subscribe(new ProgressSubscriber<AlipayBean>
                (mContext) {
            @Override
            public void onNext(AlipayBean alipayBean) {
                if (null != iMypayView) {
                    iMypayView.alipayBefore(alipayBean);
                }
            }
        });
    }


}
