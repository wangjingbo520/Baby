package com.sunbaby.app.callback;

import com.sunbaby.app.bean.PesisongBean;

/**
 * @author 王静波
 * @date 2018/7/23
 * describe
 */
public interface IPeisongView {
    void queryDispatching(PesisongBean pesisongBean);
    void deleteDispatching(int position);
}
