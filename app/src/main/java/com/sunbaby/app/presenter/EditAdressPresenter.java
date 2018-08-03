package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.bean.Areabean;
import com.sunbaby.app.bean.EditAdressBean;
import com.sunbaby.app.bean.HomeBean;
import com.sunbaby.app.bean.YouerYuan;
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

    /**
     * 修改收货地址保存
     *
     * @param id
     * @param userId
     * @param mobile
     * @param status
     * @param provinceId
     * @param cityId
     * @param district
     * @param areaId
     * @param detailedAddress
     * @param name
     */
    public void updateSave(String id, String userId, String mobile, int status, String
            provinceId, String cityId, String district, String areaId, String detailedAddress,
                           String name) {
        mRequestClient.updateSave(id, userId, mobile, status, provinceId, cityId, district,
                areaId, detailedAddress, name).subscribe(new ProgressSubscriber<EditAdressBean>
                (mContext) {
            @Override
            public void onNext(EditAdressBean homeBean) {
                if (null != iEditAddressView) {
                    iEditAddressView.updateAddressInit(homeBean);
                }
            }

        });
    }


    /**
     * 注册地区查询
     *
     * @param type
     * @param parentId
     */
    public void regionList(String type, String parentId) {
        mRequestClient.regionList(type, parentId).subscribe(new ProgressSubscriber<Areabean>
                (mContext) {
            @Override
            public void onNext(Areabean areabeans) {
                if (null != iEditAddressView) {
                    iEditAddressView.regionList(areabeans);
                }
            }

        });
    }


    /**
     * 注册幼儿园查询
     *
     * @param type
     * @param parentId
     * @param kindergartenName
     */
    public void kindergarten(String type, String parentId, String kindergartenName) {
        mRequestClient.kindergarten(type, parentId, kindergartenName).subscribe(new ProgressSubscriber<YouerYuan>(mContext) {
            @Override
            public void onNext(YouerYuan youerYuan) {
                if (null != iEditAddressView) {
                    iEditAddressView.kindergarten(youerYuan);
                }
            }
        });
    }


}
