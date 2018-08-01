package com.sunbaby.app.callback;

import com.sunbaby.app.bean.CanReceiveBean;

/**
 * @author Wangjingbo
 * @date 2018/8/1
 * describe
 */
public interface ICanReceiveView {
    void updateMobileInit(CanReceiveBean canReceiveBean);

    void sendSmsUpdataPhoneNumber(Object o);

    void updateMobilesVerify(Object o);

    void updateMobileSave(Object o);
}
