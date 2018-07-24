package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.bean.SecondGoodsListBean;
import com.sunbaby.app.callback.ISecondaryListView;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;
import com.sunbaby.app.common.utils.ToastUtil;

/**
 * @author wangjingbo
 * @date 2018/7/20
 * describe
 */
public class SecondaryListPresenter extends BasePresenter {
    private ISecondaryListView iSecondaryListView;
    public SecondaryListPresenter(Context context, ISecondaryListView iSecondaryListView) {
        super(context);
        this.iSecondaryListView = iSecondaryListView;
    }

    /**
     * 商品二级类型
     *
     * @param type        商品二级类型id
     * @param scount_name 搜索值
     * @param User_id     用户id
     * @param currPage    currPage
     * @param pageSize    pageSize
     */
    public void querydayGoodsByRand(String type, String scount_name, String User_id, int
            currPage, int pageSize) {
        mRequestClient.querydayGoodsByRand(type, scount_name, User_id, currPage, pageSize)
                .subscribe(new ProgressSubscriber<SecondGoodsListBean>
                        (mContext) {
                    @Override
                    public void onNext(SecondGoodsListBean secondGoodsListBean) {
                        if (null != iSecondaryListView) {
                            iSecondaryListView.querydayGoodsByRand(secondGoodsListBean);
                        }
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

}
