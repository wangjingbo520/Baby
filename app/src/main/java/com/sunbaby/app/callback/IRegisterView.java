package com.sunbaby.app.callback;

import com.sunbaby.app.bean.Areabean;
import com.sunbaby.app.bean.YouerYuan;

import java.util.List;

/**
 * @author 王静波
 * @date 2018/7/10
 * describe 注册
 */
public interface IRegisterView {
    public void onGetCodeSucceed();

    public void regionList(Areabean areabean);

    public void kindergarten(YouerYuan youerYuan);

    public void onRegisterSucess(Object object);
}
