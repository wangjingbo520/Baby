package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.AppData;
import com.sunbaby.app.bean.Areabean;
import com.sunbaby.app.bean.User;
import com.sunbaby.app.bean.YouerYuan;
import com.sunbaby.app.callback.IRegisterView;
import com.sunbaby.app.common.api.HttpResultFuc;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author 王静波
 * @date 2018/7/10
 * describe 注册
 */
public class RegisterPresenter extends BasePresenter {
    private IRegisterView iRegisterView;

    public RegisterPresenter(Context context, IRegisterView iRegisterView) {
        super(context);
        this.iRegisterView = iRegisterView;
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
                if (null != iRegisterView) {
                    iRegisterView.regionList(areabeans);
                }
            }
        });
    }

    /**
     * 获取验证码
     * @param mobile
     * @param scene
     */
    public void sendSms(String mobile, String scene) {
        mRequestClient.sendSms(mobile, scene).subscribe(new ProgressSubscriber<Object>
                (mContext) {
            @Override
            public void onNext(Object object) {
                if (null != iRegisterView) {
                    iRegisterView.onGetCodeSucceed();
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
                if (null != iRegisterView) {
                    iRegisterView.kindergarten(youerYuan);
                }
            }
        });
    }

    /**
     * 注册
     *
     * @param mobile            手机号码
     * @param smsCode           短信验证码
     * @param passWord          密码
     * @param rePassWord        确认密码
     * @param userName          孩子姓名
     * @param addr              详细地址
     * @param provinceId        省份id
     * @param citId             市id
     * @param district          district
     * @param areaId            areaId
     * @param kindergartenName  kindergartenName
     * @param kindergartenClass kindergartenClass
     * @return
     */
    public void register(String mobile, String smsCode, String passWord, String
            rePassWord, String userName, String addr, String provinceId, String
                                 citId, String district, String areaId, String
                                 kindergartenName, String kindergartenClass) {
        mRequestClient.register(mobile, smsCode, passWord, rePassWord, userName, addr,
                provinceId, citId, district, areaId, kindergartenName, kindergartenClass)
                .subscribe(new ProgressSubscriber<Object>(mContext) {
            @Override
            public void onNext(Object object) {
                if (null != iRegisterView) {
                    iRegisterView.onRegisterSucess(object);
                }
            }
        });
    }


}
