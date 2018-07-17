package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.bean.CenterBean;
import com.sunbaby.app.bean.VipBean;
import com.sunbaby.app.callback.ICenterView;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author 王静波
 * @date 2018/7/17
 * describe  个人中心
 */
public class CenterPresenter extends BasePresenter {
    private ICenterView iCenterView;

    public CenterPresenter(Context context, ICenterView iCenterView) {
        super(context);
        this.iCenterView = iCenterView;
    }


    /**
     * 个人中心
     *
     * @param userId
     */
    public void homePage(String userId) {
        mRequestClient.homePage(userId).subscribe(new ProgressSubscriber<CenterBean>
                (mContext) {
            @Override
            public void onNext(CenterBean centerBean) {
                if (null != iCenterView) {
                    iCenterView.homePage(centerBean);
                }
            }
        });
    }

}
