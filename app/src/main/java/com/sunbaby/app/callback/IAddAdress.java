package com.sunbaby.app.callback;

import com.sunbaby.app.bean.Areabean;
import com.sunbaby.app.bean.YouerYuan; /**
 * @author wangjingbo
 * @date 2018/7/19
 * describe
 */
public interface IAddAdress {
    void insertAddress();

    void regionList(Areabean areabeans);

    void kindergarten(YouerYuan youerYuan);
}
