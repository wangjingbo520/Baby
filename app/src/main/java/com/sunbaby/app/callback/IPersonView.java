package com.sunbaby.app.callback;

import com.sunbaby.app.bean.PersonBean;

/**
 * @author wangjingbo
 * @date 2018/7/17
 * describe
 */
public interface IPersonView {
    void personalData(PersonBean personBean);
    void updatePersonal();
}
