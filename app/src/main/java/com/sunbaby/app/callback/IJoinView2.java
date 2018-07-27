package com.sunbaby.app.callback;

import com.sunbaby.app.bean.AddVipBean;
import com.sunbaby.app.bean.JoinBean2;
import com.sunbaby.app.bean.VipBean;

/**
 * @author wangjingbo
 * @date 2018/7/23
 * describe
 */
public interface IJoinView2 {
    void applyRefundInit(JoinBean2 joinBean2);

    public void queryVipType(VipBean vipBean);

    public void addOrder(AddVipBean addVipBean);
}
