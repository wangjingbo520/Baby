package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.bean.Areabean;
import com.sunbaby.app.bean.ClassificationBean;
import com.sunbaby.app.callback.IClassificationView;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author 王静波
 * @date 2018/7/20
 * describe 商品分类
 */
public class ClassificationPresenter extends BasePresenter {
    private IClassificationView iClassificationView;

    public ClassificationPresenter(Context context, IClassificationView iClassificationView) {
        super(context);
        this.iClassificationView = iClassificationView;
    }

    /**
     * 商品分类  一级
     *
     * @param type
     */
    public void queryGoodsType(String type) {
        mRequestClient.queryGoodsType(type).subscribe(new ProgressSubscriber<ClassificationBean>
                (mContext) {
            @Override
            public void onNext(ClassificationBean classificationBean) {
                if (null != iClassificationView) {
                    iClassificationView.queryGoodsType(classificationBean);
                }
            }
        });
    }
}
