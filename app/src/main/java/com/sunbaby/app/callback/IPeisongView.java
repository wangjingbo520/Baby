package com.sunbaby.app.callback;

import com.sunbaby.app.bean.PesisongBean;

/**
 * @author wangjingbo
 * @date 2018/7/23
 * describe
 */
public interface IPeisongView {
    void queryDispatching(PesisongBean pesisongBean);

    void deleteDispatching(int position);

    void affirmDispatching(Object object);
}
