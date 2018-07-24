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
}
