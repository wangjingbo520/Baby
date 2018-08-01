package com.sunbaby.app.common.api;

import com.sunbaby.app.MyApplication;
import com.sunbaby.app.bean.AddVipBean;
import com.sunbaby.app.bean.AdressBean;
import com.sunbaby.app.bean.AlipayBean;
import com.sunbaby.app.bean.Areabean;
import com.sunbaby.app.bean.CanReceiveBean;
import com.sunbaby.app.bean.CenterBean;
import com.sunbaby.app.bean.ClassificationBean;
import com.sunbaby.app.bean.EditAdressBean;
import com.sunbaby.app.bean.HomeBean;
import com.sunbaby.app.bean.JoinBean2;
import com.sunbaby.app.bean.PayBean;
import com.sunbaby.app.bean.PersonBean;
import com.sunbaby.app.bean.PesisongBean;
import com.sunbaby.app.bean.QueryGoodsByRandBean;
import com.sunbaby.app.bean.SearchHistoryBean;
import com.sunbaby.app.bean.SecondGoodsListBean;
import com.sunbaby.app.bean.SureBean;
import com.sunbaby.app.bean.UploadFile;
import com.sunbaby.app.bean.User;
import com.sunbaby.app.bean.VipBean;
import com.sunbaby.app.bean.WeChatPayBean;
import com.sunbaby.app.bean.YouerYuan;
import com.sunbaby.app.common.api.persistentcookiejar.AddCookiesInterceptor;
import com.sunbaby.app.common.api.persistentcookiejar.ClearableCookieJar;
import com.sunbaby.app.common.api.persistentcookiejar.PersistentCookieJar;
import com.sunbaby.app.common.api.persistentcookiejar.ReceivedCookiesInterceptor;
import com.sunbaby.app.common.api.persistentcookiejar.cache.SetCookieCache;
import com.sunbaby.app.common.api.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

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
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
//        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
//        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
//        builder.retryOnConnectionFailure(false);
//        //拦截器－添加公共字段
//        builder.addInterceptor(new CommonInterceptor());
//        builder.addNetworkInterceptor(new LoggingInterceptor());
//
//        OkHttpClient okHttpClient = builder.build();
//        mRetrofit = new Retrofit.Builder()
//                .baseUrl(URLs.SERVER_URL)
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        mServerApi = mRetrofit.create(ServerAPI.class);


        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(),
                        new SharedPrefsCookiePersistor(MyApplication.context));
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.cookieJar(cookieJar);
        //这里是cookie本地保存持久化
        builder.interceptors().add(new AddCookiesInterceptor());
        builder.interceptors().add(new ReceivedCookiesInterceptor());
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
    public Observable<AddVipBean> addOrder(String userId, String vipTypeId, String vipPriceId,
                                           String amount) {
        return mServerApi.addOrder(userId, vipTypeId, vipPriceId, amount)
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


    public Observable<Object> updatePersonal(String photo, String userName, String userId, String
            sex) {
        return mServerApi.updatePersonal(photo, userName, userId, sex)
                .map(new HttpResultFuc<Object>())
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
     * 短信验证
     *
     * @param mobile
     * @param code
     * @param scene
     * @return
     */
    public Observable<Object> updateMobilesVerify(String mobile, String code, String scene) {
        return mServerApi.updateMobilesVerify(mobile, code, scene)
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

    /**
     * 首页随机商品
     *
     * @param type 1 图书 2 玩具 0 全部
     * @return
     */
    public Observable<QueryGoodsByRandBean> queryGoodsByRand(String type) {
        return mServerApi.queryGoodsByRand(type)
                .map(new HttpResultFuc<QueryGoodsByRandBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 商品类型 一级分类
     *
     * @param type
     * @return
     */
    public Observable<ClassificationBean> queryGoodsType(String type) {
        return mServerApi.queryGoodsType(type)
                .map(new HttpResultFuc<ClassificationBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 商品二级类型
     *
     * @param type        商品二级类型id
     * @param scount_name 搜索值
     * @param User_id     用户id
     * @param currPage    currPage
     * @param pageSize    pageSize
     */
    public Observable<SecondGoodsListBean> querydayGoodsByRand(String type, String scount_name,
                                                               String User_id, int
                                                                       currPage, int pageSize) {
        return mServerApi.querydayGoodsByRand(type, scount_name, User_id, currPage, pageSize)
                .map(new HttpResultFuc<SecondGoodsListBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 历史搜索记录
     *
     * @param user_id
     * @param scount_name
     * @return
     */
    public Observable<SearchHistoryBean> queryAccountSearch(String user_id, String scount_name) {
        return mServerApi.queryAccountSearch(user_id, scount_name)
                .map(new HttpResultFuc<SearchHistoryBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 会员初始化
     *
     * @param userId
     * @return
     */
    public Observable<JoinBean2> applyRefundInit(String userId) {
        return mServerApi.applyRefundInit(userId)
                .map(new HttpResultFuc<JoinBean2>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 支付方式
     *
     * @return
     */
    public Observable<PayBean> queryPayMethod() {
        return mServerApi.queryPayMethod()
                .map(new HttpResultFuc<PayBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 微信支付
     *
     * @param userId
     * @param orderId
     * @return
     */
    public Observable<WeChatPayBean> wechatPayBefore(String userId, String orderId) {
        return mServerApi.wechatPayBefore(userId, orderId)
                .map(new HttpResultFuc<WeChatPayBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 支付宝支付
     *
     * @param userId
     * @param orderId
     * @return
     */
    public Observable<AlipayBean> alipayBefore(String userId, String orderId) {
        return mServerApi.alipayBefore(userId, orderId)
                .map(new HttpResultFuc<AlipayBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 上传单个 或多个 图片
     *
     * @param fileName
     * @param data
     * @return
     */
    public Observable<UploadFile> uploadFile(String fileName, String data) {
        return mServerApi.uploadFile(data)
                .map(new HttpResultFuc<UploadFile>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 配送箱列表
     *
     * @param user_id
     * @return
     */
    public Observable<PesisongBean> queryDispatching(String user_id) {
        return mServerApi.queryDispatching(user_id)
                .map(new HttpResultFuc<PesisongBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 删除配送箱商品
     *
     * @param user_id
     * @param dispatching_id
     * @return
     */
    public Observable<Object> deleteDispatching(String user_id, String dispatching_id) {
        return mServerApi.deleteDispatching(user_id, dispatching_id)
                .map(new HttpResultFuc<Object>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 添加到配送箱
     *
     * @param goodsId
     * @return
     */
    public Observable<Object> joinDistributionBox(String goodsId) {
        return mServerApi.joinDistributionBox(goodsId)
                .map(new HttpResultFuc<Object>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 配送箱确认
     *
     * @param user_id
     * @param dispatchingJson
     * @return
     */
    public Observable<SureBean> affirmDispatching(String user_id, String dispatchingJson) {
        return mServerApi.affirmDispatching(user_id, dispatchingJson)
                .map(new HttpResultFuc<SureBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 退出登录
     *
     * @return
     */
    public Observable<Object> logout() {
        return mServerApi.logout()
                .map(new HttpResultFuc<Object>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 修改手机号码原手机号码能接受验证码初始化
     *
     * @return
     */
    public Observable<CanReceiveBean> updateMobileInit() {
        return mServerApi.updateMobileInit()
                .map(new HttpResultFuc<CanReceiveBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 修改手机号码发送短信验证
     *
     * @param mobile
     * @param scene
     * @return
     */
    public Observable<Object> sendSmsUpdataPhoneNumber(String mobile, String scene) {
        return mServerApi.sendSmsUpdataPhoneNumber(mobile, scene)
                .map(new HttpResultFuc<Object>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 修改手机号码保存
     *
     * @param mobile
     * @param scene
     * @param code
     * @param password
     * @return
     */
    public Observable<Object> updateMobileSave(String mobile, String scene, String code, String
            password) {
        return mServerApi.updateMobileSave(mobile, scene, code, password)
                .map(new HttpResultFuc<Object>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 修改密码
     *
     * @param passWord
     * @param passWordNoe
     * @param passWordTwo
     * @return
     */
    public Observable<Object> updatePassword(String passWord, String passWordNoe, String
            passWordTwo) {
        return mServerApi.updatePassword(passWord, passWordNoe, passWordTwo)
                .map(new HttpResultFuc<Object>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}


