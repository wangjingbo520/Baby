package com.sunbaby.app.callback;

import com.sunbaby.app.bean.AdressBean;

/**
 * @author wangjingbo
 * @date 2018/7/19
 * describe
 */
public interface IAdressView {
    void addressList(AdressBean adressBean);
    void deleteById(int position);
    void defaultAddress(int position);
}
