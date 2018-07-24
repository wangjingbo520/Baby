package com.sunbaby.app.callback;

import com.sunbaby.app.bean.AddVipBean;
import com.sunbaby.app.bean.VipBean;

/**
 * @author wangjingbo
 * @date 2018/7/17
 * describe
 */
public interface IJoinView {
    public void queryVipType(VipBean vipBean);

    public void addOrder(AddVipBean addVipBean);
}
