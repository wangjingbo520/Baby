package com.sunbaby.app.callback;

import com.sunbaby.app.bean.Areabean;
import com.sunbaby.app.bean.EditAdressBean;
import com.sunbaby.app.bean.YouerYuan;

/**
 * @author wangjingbo
 * @date 2018/7/19
 * describe
 */
public interface IEditAddressView {
    void updateAddressInit(EditAdressBean editAdressBean);

    void regionList(Areabean areabeans);

    void kindergarten(YouerYuan youerYuan);
}
