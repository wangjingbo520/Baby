package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.bean.Areabean;
import com.sunbaby.app.bean.YouerYuan;
import com.sunbaby.app.callback.IAddAdress;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author wangjingbo
 * @date 2018/7/19
 * describe 增加收货地址
 */
public class AddNewAddressPresenter extends BasePresenter {
    private IAddAdress iAddAdress;


    public AddNewAddressPresenter(Context context, IAddAdress iAddAdress) {
        super(context);
        this.iAddAdress = iAddAdress;
    }

    /**
     * 增加收货地址
     *
     * @param mobile          手机号码
     * @param status          是否默认收货地址		 0 默认地址 1非默认地址
     * @param provinceId      省id
     * @param cityId          市id
     * @param district        区域id
     * @param areaId          幼儿园id
     * @param detailedAddress 详细地址
     * @param name            收货人姓名
     */
    public void insertAddress(String userId,String mobile, String status, String provinceId, String cityId,
                              String district
            , String areaId, String detailedAddress, String name) {
        mRequestClient.insertAddress(userId,mobile, status, provinceId, cityId, district, areaId,
                detailedAddress, name).subscribe(new ProgressSubscriber<Object>
                (mContext) {
            @Override
            public void onNext(Object object) {
                if (null != iAddAdress) {
                    iAddAdress.insertAddress();
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
                if (null != iAddAdress) {
                    iAddAdress.regionList(areabeans);
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
                if (null != iAddAdress) {
                    iAddAdress.kindergarten(youerYuan);
                }
            }
        });
    }


}
