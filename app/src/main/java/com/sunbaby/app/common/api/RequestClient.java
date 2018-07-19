package com.sunbaby.app.common.api;

import com.sunbaby.app.bean.AddVipBean;
import com.sunbaby.app.bean.AdressBean;
import com.sunbaby.app.bean.Areabean;
import com.sunbaby.app.bean.CenterBean;
import com.sunbaby.app.bean.EditAdressBean;
import com.sunbaby.app.bean.HomeBean;
import com.sunbaby.app.bean.PersonBean;
import com.sunbaby.app.bean.User;
import com.sunbaby.app.bean.VipBean;
import com.sunbaby.app.bean.YouerYuan;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：liuyuanqi on 17/7/17 16:02
 * 邮箱：liuyuanqi@eims.com.cn
 */

public class RequestClient {

    /**
     * 超时时间(秒)
     */
    public static final int DEFAULT_TIMEOUT = 60;
    public static final int CONNECT_TIMEOUT = 10;

    /**
     * 单例
     */
    private static RequestClient requestClient;

    private Retrofit mRetrofit;

    private ServerAPI mServerApi;

    private RequestClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(false);
        //拦截器－添加公共字段
        builder.addInterceptor(new CommonInterceptor());
        builder.addNetworkInterceptor(new LoggingInterceptor());

        OkHttpClient okHttpClient = builder.build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(URLs.SERVER_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mServerApi = mRetrofit.create(ServerAPI.class);
    }

    public static RequestClient getInstance() {
        if (null == requestClient) {
            requestClient = new RequestClient();
        }
        return requestClient;
    }

    /**************************************************************************************************/

    /**
     * 注册地区查询
     *
     * @param type
     * @param parentId
     * @return
     */
    public Observable<Areabean> regionList(String type, String parentId) {
        return mServerApi.regionList(type, parentId)
                .map(new HttpResultFuc<Areabean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 2、注册幼儿园查询
     *
     * @param type
     * @param parentId
     * @param kindergartenName
     * @return
     */
    public Observable<YouerYuan> kindergarten(String type, String parentId, String
            kindergartenName) {
        return mServerApi.kindergarten(type, parentId, kindergartenName)
                .map(new HttpResultFuc<YouerYuan>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取短信验证码
     *
     * @param mobile
     * @param scene
     * @return
     */
    public Observable<Object> sendSms(String mobile, String scene) {
        return mServerApi.sendSms(mobile, scene)
                .map(new HttpResultFuc<Object>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * @param mobile
     * @param smsCode
     * @param passWord
     * @param rePassWord
     * @param userName
     * @param addr
     * @param provinceId
     * @param citId
     * @param district
     * @param areaId
     * @param kindergartenName
     * @param kindergartenClass
     * @return
     */
    public Observable<Object> register(String mobile, String smsCode, String passWord, String
            rePassWord, String userName, String addr, String provinceId, String
                                               citId, String district, String areaId, String
                                               kindergartenName, String kindergartenClass) {
        return mServerApi.register(mobile, smsCode, passWord, rePassWord, userName, addr,
                provinceId, citId, district, areaId, kindergartenName, kindergartenClass)
                .map(new HttpResultFuc<Object>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 查找对应的会员类型
     *
     * @return
     */
    public Observable<VipBean> queryVipType() {
        return mServerApi.queryVipType()
                .map(new HttpResultFuc<VipBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 立即开通会员
     *
     * @param userId
     * @param vipTypeId
     * @param amount
     * @return
     */
    public Observable<AddVipBean> addOrder(String userId, String vipTypeId, String amount) {
        return mServerApi.addOrder(userId, vipTypeId, amount)
                .map(new HttpResultFuc<AddVipBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 个人中心
     *
     * @param userId
     * @return
     */
    public Observable<CenterBean> homePage(String userId) {
        return mServerApi.homePage(userId)
                .map(new HttpResultFuc<CenterBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 个人资料
     *
     * @param userId
     * @return
     */
    public Observable<PersonBean> personalData(String userId) {
        return mServerApi.personalData(userId)
                .map(new HttpResultFuc<PersonBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 首页图片
     *
     * @return
     */
    public Observable<HomeBean> queryContentAdvertisementsByHome() {
        return mServerApi.queryContentAdvertisementsByHome()
                .map(new HttpResultFuc<HomeBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 首页图片
     *
     * @return
     */
    public Observable<User> login(String userName, String pwd) {
        return mServerApi.login(userName, pwd)
                .map(new HttpResultFuc<User>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 忘记密码
     *
     * @param mobile
     * @param code
     * @param scene
     * @param passWordNoe
     * @param passWordTwo
     * @return
     */
    public Observable<Object> forgetPassword(String mobile, String code, String scene, String
            passWordNoe,
                                             String passWordTwo) {
        return mServerApi.forgetPassword(mobile, code, scene, passWordNoe, passWordTwo)
                .map(new HttpResultFuc<Object>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 收货地址列表
     *
     * @param userId
     * @return
     */
    public Observable<AdressBean> addressList(String userId) {
        return mServerApi.addressList(userId)
                .map(new HttpResultFuc<AdressBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 删除收货地址
     *
     * @param id
     * @return
     */
    public Observable<Object> deleteById(String id) {
        return mServerApi.deleteById(id)
                .map(new HttpResultFuc<Object>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 设置为默认地址
     *
     * @param id
     * @return
     */
    public Observable<Object> defaultAddress(String id) {
        return mServerApi.defaultAddress(id)
                .map(new HttpResultFuc<Object>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 新增地址
     *
     * @param mobile
     * @param status
     * @param provinceId
     * @param cityId
     * @param district
     * @param areaId
     * @param detailedAddress
     * @param name
     * @return
     */
    public Observable<Object> insertAddress(String userId, String mobile, String status, String
            provinceId,
                                            String cityId,
                                            String district
            , String areaId, String detailedAddress, String name) {
        return mServerApi.insertAddress(userId, mobile, status, provinceId, cityId, district,
                areaId,
                detailedAddress, name)
                .map(new HttpResultFuc<Object>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 编辑收货地址
     *
     * @param id
     * @return
     */
    public Observable<EditAdressBean> updateAddressInit(String id) {
        return mServerApi.updateAddressInit(id)
                .map(new HttpResultFuc<EditAdressBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}


