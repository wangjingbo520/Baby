package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.bean.DamageDetailBean;
import com.sunbaby.app.bean.DamageRecordBean;
import com.sunbaby.app.callback.IDamageRecordView;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author Wangjingbo
 * @date 2018/8/2
 * describe  损坏记录
 */
public class DamageRecordPresenter extends BasePresenter {
    private IDamageRecordView iDamageRecordView;

    public DamageRecordPresenter(Context context, IDamageRecordView iDamageRecordView) {
        super(context);
        this.iDamageRecordView = iDamageRecordView;
    }

    /**
     * 损坏记录列表
     *
     * @param userId
     * @param currPage
     * @param pageSize
     */
    public void damageList(String userId, int currPage, int pageSize) {
        mRequestClient.damageList(userId, currPage, pageSize).subscribe(new ProgressSubscriber<DamageRecordBean>
                (mContext, false) {
            @Override
            public void onNext(DamageRecordBean damageRecordBean) {
                if (null != iDamageRecordView) {
                    iDamageRecordView.damageList(damageRecordBean);
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (null != iDamageRecordView) {
                    iDamageRecordView.onFinish();
                }
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                if (null != iDamageRecordView) {
                    iDamageRecordView.onFinish();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (null != iDamageRecordView) {
                    iDamageRecordView.onFinish();
                }
            }


        });
    }

    /**
     * 损坏记录详情
     *
     * @param goodsDamageId
     */
    public void damageDetails(String goodsDamageId) {
        mRequestClient.damageDetails(goodsDamageId).subscribe(new ProgressSubscriber<DamageDetailBean>
                (mContext) {
            @Override
            public void onNext(DamageDetailBean damageDetailBean) {
                if (null != iDamageRecordView) {
                    iDamageRecordView.damageDetails(damageDetailBean);
                }
            }
        });
    }
}
