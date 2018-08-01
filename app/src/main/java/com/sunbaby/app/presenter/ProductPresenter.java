package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.bean.ProductBean;
import com.sunbaby.app.callback.IProductView;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author Wangjingbo
 * @date 2018/8/1
 * describe
 */
public class ProductPresenter extends BasePresenter {
    private IProductView iProductView;

    public ProductPresenter(Context context, IProductView iProductView) {
        super(context);
        this.iProductView = iProductView;
    }

    /**
     * 商品详情
     *
     * @param goods_id
     */
    public void queryGoodsDetails(String goods_id) {
        mRequestClient.queryGoodsDetails(goods_id).subscribe(new ProgressSubscriber<ProductBean>
                (mContext) {
            @Override
            public void onNext(ProductBean productBean) {
                if (null != iProductView) {
                    iProductView.queryGoodsDetails(productBean);
                }
            }
        });
    }


    /**
     * 添加到配送箱
     *
     * @param goodsId
     */
    public void joinDistributionBox(String goodsId) {
        mRequestClient.joinDistributionBox(goodsId).subscribe(new ProgressSubscriber<Object>
                (mContext) {
            @Override
            public void onNext(Object object) {
                if (null != iProductView) {
                    iProductView.joinDistributionBox(object);
                }
            }
        });
    }
}

