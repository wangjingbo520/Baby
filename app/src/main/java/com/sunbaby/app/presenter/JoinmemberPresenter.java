package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.bean.AddVipBean;
import com.sunbaby.app.bean.Areabean;
import com.sunbaby.app.bean.VipBean;
import com.sunbaby.app.callback.IJoinView;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author wangjingbo
 * @date 2018/7/17
 * describe  加入会员
 */
public class JoinmemberPresenter extends BasePresenter {
    private IJoinView iJoinView;

    public JoinmemberPresenter(Context context, IJoinView iJoinView) {
        super(context);
        this.iJoinView = iJoinView;
    }

    /**
     * 查找对应的会员类型
     */
    public void queryVipType() {
        mRequestClient.queryVipType().subscribe(new ProgressSubscriber<VipBean>
                (mContext) {
            @Override
            public void onNext(VipBean vipBean) {
                if (null != iJoinView) {
                    iJoinView.queryVipType(vipBean);
                }
            }
        });
    }


    /**
     * 立即开通会员
     *
     * @param userId
     * @param vipTypeId
     * @param amount
     */
    public void addOrder(String userId, String vipTypeId, String vipPriceId,String amount) {
        mRequestClient.addOrder(userId,vipTypeId,vipPriceId,amount).subscribe(new ProgressSubscriber<AddVipBean>
                (mContext) {
            @Override
            public void onNext(AddVipBean addVipBean) {
                if (null != iJoinView) {
                    iJoinView.addOrder(addVipBean);
                }
            }
        });
    }
}
