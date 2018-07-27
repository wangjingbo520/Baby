package com.sunbaby.app.callback;

import com.sunbaby.app.bean.AlipayBean;
import com.sunbaby.app.bean.PayBean;
import com.sunbaby.app.bean.WeChatPayBean;

/**
 * @author wangjingbo
 * @date 2018/7/23
 * describe
 */
public interface IMypayView {
    void queryPayMethod(PayBean payBean);
    void wechatPayBefore(WeChatPayBean weChatPayBean);
    void alipayBefore(AlipayBean alipayBean);
}
