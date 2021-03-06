package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.bean.PayBean;
import com.sunbaby.app.bean.PesisongBean;
import com.sunbaby.app.bean.SureBean;
import com.sunbaby.app.callback.IMypayView;
import com.sunbaby.app.callback.IPeisongView;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author wangjingbo
 * @date 2018/7/23
 * describe
 */
public class PeisongPresenter extends BasePresenter {
    private IPeisongView iPeisongView;

    public PeisongPresenter(Context context, IPeisongView iPeisongView) {
        super(context);
        this.iPeisongView = iPeisongView;
    }

    /**
     * 配送箱列表
     */
    public void queryDispatching(String user_id) {
        mRequestClient.queryDispatching(user_id).subscribe(new ProgressSubscriber<PesisongBean>
                (mContext) {
            @Override
            public void onNext(PesisongBean pesisongBean) {
                if (null != iPeisongView) {
                    iPeisongView.queryDispatching(pesisongBean);
                }
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                iPeisongView.onFinish();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                iPeisongView.onFinish();
            }

        });
    }

    /**
     * 删除配送箱商品
     * @param user_id
     * @param dispatching_id
     */
    public void deleteDispatching(final int position, String user_id, String dispatching_id) {
        mRequestClient.deleteDispatching(user_id,dispatching_id).subscribe(new ProgressSubscriber<Object>
                (mContext) {
            @Override
            public void onNext(Object object) {
                if (null != iPeisongView) {
                    iPeisongView.deleteDispatching(position);
                }
            }
        });
    }

    public void affirmDispatching(String user_id, String dispatchingJson) {
        mRequestClient.affirmDispatching(user_id,dispatchingJson).subscribe(new ProgressSubscriber<SureBean>
                (mContext) {
            @Override
            public void onNext(SureBean sureBean) {
                if (null != iPeisongView) {
                    iPeisongView.affirmDispatching(sureBean);
                }
            }
        });
    }

}
