package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.bean.AddVipBean;
import com.sunbaby.app.bean.JoinBean2;
import com.sunbaby.app.bean.VipBean;
import com.sunbaby.app.callback.IJoinView;
import com.sunbaby.app.callback.IJoinView2;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author wangjingbo
 * @date 2018/7/17
 * describe  加入会员
 */
public class JoinmemberPresenter2 extends BasePresenter {
    private IJoinView2 iJoinView2;

    public JoinmemberPresenter2(Context context, IJoinView2 iJoinView2) {
        super(context);
        this.iJoinView2 = iJoinView2;
    }


    /**
     * 加入会员初始化
     *
     * @param userId
     */
    public void applyRefundInit(String userId) {
        mRequestClient.applyRefundInit(userId).subscribe(new ProgressSubscriber<JoinBean2>
                (mContext) {
            @Override
            public void onNext(JoinBean2 joinBean2) {
                if (null != iJoinView2) {
                    iJoinView2.applyRefundInit(joinBean2);
                }
            }
        });
    }

    /**
     * 查找对应的会员类型
     */
    public void queryVipType() {
        mRequestClient.queryVipType().subscribe(new ProgressSubscriber<VipBean>
                (mContext) {
            @Override
            public void onNext(VipBean vipBean) {
                if (null != iJoinView2) {
                    iJoinView2.queryVipType(vipBean);
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
                if (null != iJoinView2) {
                    iJoinView2.addOrder(addVipBean);
                }
            }
        });
    }
}
