package com.sunbaby.app.callback;

import com.sunbaby.app.bean.DamageDetailBean;
import com.sunbaby.app.bean.DamageRecordBean;

/**
 * @author Wangjingbo
 * @date 2018/8/2
 * describe
 */
public interface IDamageRecordView {
    void damageList(DamageRecordBean damageRecordBean);

    void damageDetails(DamageDetailBean damageDetailBean);

    void onFinish();
}
