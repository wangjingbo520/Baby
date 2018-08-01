package com.sunbaby.app.callback;

import com.sunbaby.app.bean.ProductBean;

/**
 * @author Wangjingbo
 * @date 2018/8/1
 * describe
 */
public interface IProductView {
    void queryGoodsDetails(ProductBean productBean);
    void joinDistributionBox(Object object);
}
