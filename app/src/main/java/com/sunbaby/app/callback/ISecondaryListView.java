package com.sunbaby.app.callback;

import com.sunbaby.app.bean.SecondGoodsListBean;

/**
 * @author wangjingbo
 * @date 2018/7/20
 * describe
 */
public interface ISecondaryListView {
    void querydayGoodsByRand(SecondGoodsListBean secondGoodsListBean);
    void onFinish();
}
