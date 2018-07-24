package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.bean.PayBean;
import com.sunbaby.app.bean.PersonBean;
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

}
