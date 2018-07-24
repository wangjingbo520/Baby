package com.sunbaby.app.callback;

import com.sunbaby.app.bean.HomeBean;
import com.sunbaby.app.bean.QueryGoodsByRandBean;

/**
 * @author 王静波
 * @date 2018/7/18
 * describe
 */
public interface IHomeView {
    void queryContentAdvertisementsByHome(HomeBean homeBean);
    void queryGoodsByRand(QueryGoodsByRandBean queryGoodsByRandBean);
    void joinDistributionBox(Object object);
}
