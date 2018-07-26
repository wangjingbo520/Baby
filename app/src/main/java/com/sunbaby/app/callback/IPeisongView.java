package com.sunbaby.app.callback;

import com.sunbaby.app.bean.PesisongBean;
import com.sunbaby.app.bean.SureBean;

/**
 * @author wangjingbo
 * @date 2018/7/23
 * describe
 */
public interface IPeisongView {
    void queryDispatching(PesisongBean pesisongBean);

    void deleteDispatching(int position);

    void affirmDispatching(SureBean sureBean);

    void onFinish();
}
