package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.bean.SongQuhuoBean;
import com.sunbaby.app.callback.ISQuoView;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author Wangjingbo
 * @date 2018/8/2
 * describe 送货 取货
 */
public class SQuoPresenter extends BasePresenter {
    private ISQuoView isQuoView;

    public SQuoPresenter(Context context, ISQuoView isQuoView) {
        super(context);
        this.isQuoView = isQuoView;
    }

    /**
     * 送货  取货
     *
     * @param type
     * @param user_id
     * @param currPage
     * @param pageSize
     */
    public void retrievingList(String type, String user_id, int currPage, int pageSize) {
        mRequestClient.retrievingList(type, user_id, currPage, pageSize).subscribe(new ProgressSubscriber<SongQuhuoBean>
                (mContext, true) {
            @Override
            public void onNext(SongQuhuoBean songQuhuoBean) {
                if (null != isQuoView) {
                    isQuoView.retrievingList(songQuhuoBean);
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (null != isQuoView) {
                    isQuoView.onFinish();
                }
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                if (null != isQuoView) {
                    isQuoView.onFinish();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (null != isQuoView) {
                    isQuoView.onFinish();
                }
            }
        });
    }
}
