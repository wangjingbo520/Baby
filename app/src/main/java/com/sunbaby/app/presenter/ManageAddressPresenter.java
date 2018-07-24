package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.AppData;
import com.sunbaby.app.bean.AdressBean;
import com.sunbaby.app.bean.User;
import com.sunbaby.app.callback.IAdressView;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author wangjingbo
 * @date 2018/7/19
 * describe
 */
public class ManageAddressPresenter extends BasePresenter {
    private IAdressView iAdressView;

    public ManageAddressPresenter(Context context, IAdressView iAdressView) {
        super(context);
        this.iAdressView = iAdressView;
    }

    /**
     * 收货地址列表
     *
     * @param userId
     */
    public void addressList(String userId) {
        mRequestClient.addressList(userId).subscribe(new ProgressSubscriber<AdressBean>(mContext) {
            @Override
            public void onNext(AdressBean adressBean) {
                if (null != iAdressView) {
                    iAdressView.addressList(adressBean);
                }
            }
        });
    }

    /**
     * 删除收货地址
     *
     * @param id
     */
    public void deleteById(String id, final int position) {
        mRequestClient.deleteById(id).subscribe(new ProgressSubscriber<Object>(mContext) {
            @Override
            public void onNext(Object adressBean) {
                if (null != iAdressView) {
                    iAdressView.deleteById(position);
                }
            }
        });
    }

    /**
     * 设置默认地址
     * @param id
     */
    public void defaultAddress(String id, final int position) {
        mRequestClient.defaultAddress(id).subscribe(new ProgressSubscriber<Object>(mContext) {
            @Override
            public void onNext(Object adressBean) {
                if (null != iAdressView) {
                    iAdressView.deleteById(position);
                }
            }
        });
    }
}
