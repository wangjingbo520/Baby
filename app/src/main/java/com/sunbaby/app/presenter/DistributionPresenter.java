package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.callback.IExitLoginView;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author Wangjingbo
 * @date 2018/7/25  配送员
 * describe
 */
public class DistributionPresenter extends BasePresenter {
    private IExitLoginView iExitLoginView;

    public DistributionPresenter(Context context, IExitLoginView iExitLoginView) {
        super(context);
        this.iExitLoginView = iExitLoginView;
    }

    public void logout() {
        mRequestClient.logout().subscribe(new ProgressSubscriber<Object>
                (mContext) {
            @Override
            public void onNext(Object object) {
                if (null != iExitLoginView) {
                    iExitLoginView.logout();
                }
            }
        });
    }
}

