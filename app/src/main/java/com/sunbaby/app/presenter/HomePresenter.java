package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.bean.CenterBean;
import com.sunbaby.app.bean.HomeBean;
import com.sunbaby.app.callback.IHomeView;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author 王静波
 * @date 2018/7/18
 * describe
 */
public class HomePresenter extends BasePresenter {
    private IHomeView iHomeView;

    public HomePresenter(Context context, IHomeView iHomeView) {
        super(context);
        this.iHomeView = iHomeView;
    }

    /**
     * 首页图片
     */
    public void queryContentAdvertisementsByHome() {
        mRequestClient.queryContentAdvertisementsByHome().subscribe(new ProgressSubscriber<HomeBean>
                (mContext) {
            @Override
            public void onNext(HomeBean homeBean) {
                if (null != iHomeView) {
                    iHomeView.queryContentAdvertisementsByHome(homeBean);
                }
            }
        });
    }


}
