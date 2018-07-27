package com.sunbaby.app.callback;

import com.sunbaby.app.bean.HomeBean;
import com.sunbaby.app.bean.QueryGoodsByRandBean;

/**
 * @author wangjingbo
 * @date 2018/7/18
 * describe
 */
public interface IHomeView {
    void queryContentAdvertisementsByHome(HomeBean homeBean);

    /**
     * 随机
     *
     * @param queryGoodsByRandBean
     * @param type
     */
    void queryGoodsByRand(QueryGoodsByRandBean queryGoodsByRandBean, String type);

    void joinDistributionBox(Object object);

    void onFinish();
}
