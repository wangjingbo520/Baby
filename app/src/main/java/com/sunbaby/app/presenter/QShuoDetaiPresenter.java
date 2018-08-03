package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.bean.QShuoDetaiBean;
import com.sunbaby.app.callback.IQuhuoDetaiView;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author Wangjingbo
 * @date 2018/8/3
 * describe
 */
public class QShuoDetaiPresenter extends BasePresenter {
    private IQuhuoDetaiView iQuhuoDetaiView;

    public QShuoDetaiPresenter(Context context, IQuhuoDetaiView iQuhuoDetaiView) {
        super(context);
        this.iQuhuoDetaiView = iQuhuoDetaiView;
    }


    /**
     * （送货、取货）订单列表详情
     *
     * @param orderid
     * @param time
     * @param Delivery_status
     * @param GoodsId
     * @param DispatchingID
     */
    public void retrievingListdetails(String orderid, String time, String Delivery_status, String
            GoodsId, String DispatchingID) {
        mRequestClient.retrievingListdetails(orderid, time, Delivery_status, GoodsId,
                DispatchingID).subscribe(new ProgressSubscriber<QShuoDetaiBean>
                (mContext) {
            @Override
            public void onNext(QShuoDetaiBean qShuoDetaiBean) {
                if (null != iQuhuoDetaiView) {
                    iQuhuoDetaiView.retrievingListdetails(qShuoDetaiBean);
                }
            }
        });
    }

}