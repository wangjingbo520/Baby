package com.sunbaby.app.callback;

import com.sunbaby.app.bean.SongQuhuoBean;

/**
 * @author Wangjingbo
 * @date 2018/8/2
 * describe
 */
public interface ISQuoView {
    void retrievingList(SongQuhuoBean songQuhuoBean);

    void onFinish();

}
