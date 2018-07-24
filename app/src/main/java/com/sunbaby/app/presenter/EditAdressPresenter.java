package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.bean.EditAdressBean;
import com.sunbaby.app.bean.HomeBean;
import com.sunbaby.app.callback.ICenterView;
import com.sunbaby.app.callback.IEditAddressView;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author wangjingbo
 * @date 2018/7/19
 * describe
 */
public class EditAdressPresenter extends BasePresenter {
    private IEditAddressView iEditAddressView;

    public EditAdressPresenter(Context context, IEditAddressView iEditAddressView) {
        super(context);
        this.iEditAddressView = iEditAddressView;
    }

    /**
     * 编辑收货地址
     *
     * @param id
     */
    public void updateAddressInit(String id) {
        mRequestClient.updateAddressInit(id).subscribe(new ProgressSubscriber<EditAdressBean>
                (mContext) {
            @Override
            public void onNext(EditAdressBean homeBean) {
                if (null != iEditAddressView) {
                    iEditAddressView.updateAddressInit(homeBean);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);

            }
        });
    }


}
