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

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 请求－接口
 * 作者：liuyuanqi on 17/7/17 11:45
 * 邮箱：liuyuanqi@eims.com.cn
 */
public interface ServerAPI {

    /**
     * 注册地区查询
     *
     * @param type
     * @param parentId
     * @return
     */
    @POST("Register/regionList")
    @FormUrlEncoded
    Observable<HttpResult<Areabean>> regionList(@Field("type") String type,
                                                @Field("parentId") String parentId);

    /**
     * 注册幼儿园查询
     *
     * @param type
     * @param parentId
     * @return
     */
    @POST("Register/kindergarten")
    @FormUrlEncoded
    Observable<HttpResult<YouerYuan>> kindergarten(@Field("type") String type,
                                                   @Field("parentId") String parentId,
                                                   @Field("kindergartenName") String
                                                           kindergartenName);

    /**
     * 发送短信
     *
     * @param mobile
     * @param scene
     * @return
     */
    @POST("Register/sendSms")
    @FormUrlEncoded
    Observable<HttpResult<Object>> sendSms(@Field("mobile") String mobile,
                                           @Field("scene") String scene
    );

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
    @POST("Register/register")
    @FormUrlEncoded
    Observable<HttpResult<Object>> register(@Field("mobile") String mobile,
                                            @Field("smsCode") String smsCode,
                                            @Field("passWord") String passWord,
                                            @Field("rePassWord") String rePassWord,
                                            @Field("userName") String userName,
                                            @Field("addr") String addr,
                                            @Field("provinceId") String provinceId,
                                            @Field("citId") String citId,
                                            @Field("district") String district,
                                            @Field("areaId") String areaId,
                                            @Field("kindergartenName") String kindergartenName,
                                            @Field("kindergartenClass") String kindergartenClass);


    /**
     * 查找对应的会员类型
     *
     * @return
     */
    @POST("account/queryVipType")
    Observable<HttpResult<VipBean>> queryVipType();


    /**
     * 立即开通会员
     *
     * @param userId
     * @param vipTypeId
     * @param amount
     * @return
     */
    @POST("order/addOrder")
    @FormUrlEncoded
    Observable<HttpResult<AddVipBean>> addOrder(@Field("userId") String userId,
                                                @Field("vipTypeId") String vipTypeId,
                                                @Field("amount") String amount);

    /**
     * 个人中心
     *
     * @param userId
     * @return
     */
    @POST("account/homePage")
    @FormUrlEncoded
    Observable<HttpResult<CenterBean>> homePage(@Field("userId") String userId);

    /**
     * 个人资料
     *
     * @param userId
     * @return
     */
    @POST("account/personalData")
    @FormUrlEncoded
    Observable<HttpResult<PersonBean>> personalData(@Field("userId") String userId);

    /**
     * 首页图片
     *
     * @return
     */
    @POST("home/queryContentAdvertisementsByHome")
    Observable<HttpResult<HomeBean>> queryContentAdvertisementsByHome();

    /**
     * 登录
     *
     * @param userName
     * @param pwd
     * @return
     */
    @POST("login")
    @FormUrlEncoded
    Observable<HttpResult<User>> login(@Field("userName") String userName,
                                       @Field("pwd") String pwd);


    /**
     * 忘记密码
     *
     * @param mobile      手机号
     * @param code        验证码
     * @param scene       场景
     * @param passWordNoe 密码
     * @param passWordTwo 确认密码
     * @return
     */
    @POST("account/forgetPassword")
    @FormUrlEncoded
    Observable<HttpResult<Object>> forgetPassword(@Field("mobile") String mobile,
                                                  @Field("code") String code,
                                                  @Field("scene") String scene,
                                                  @Field("passWordNoe") String passWordNoe,
                                                  @Field("passWordTwo") String passWordTwo);

    /**
     * 收获地址列表
     *
     * @param userId
     * @return
     */
    @POST("address/addressList")
    @FormUrlEncoded
    Observable<HttpResult<AdressBean>> addressList(@Field("userId") String userId);

    /**
     * 删除收货地址
     *
     * @param id
     * @return
     */
    @POST("address/deleteById")
    @FormUrlEncoded
    Observable<HttpResult<Object>> deleteById(@Field("id") String id);


    /**
     * 设置默认地址
     *
     * @param id
     * @return
     */
    @POST("address/defaultAddress")
    @FormUrlEncoded
    Observable<HttpResult<Object>> defaultAddress(@Field("id") String id);

    /**
     * @param mobile          手机号码
     * @param status          是否默认收货地址
     * @param provinceId      省id
     * @param cityId          市id
     * @param district        区域id
     * @param areaId          儿园id
     * @param detailedAddress 详细地址
     * @param name            收货人姓名
     * @return
     */
    @POST("address/insertAddress")
    @FormUrlEncoded
    Observable<HttpResult<Object>> insertAddress(@Field("userId") String userId,
                                                 @Field("mobile") String mobile,
                                                 @Field("status") String status,
                                                 @Field("provinceId") String provinceId,
                                                 @Field("cityId") String cityId,
                                                 @Field("district") String district,
                                                 @Field("areaId") String areaId,
                                                 @Field("detailedAddress") String detailedAddress,
                                                 @Field("name") String name);

    /**
     * 编辑收货地址
     *
     * @param id
     * @return
     */
    @POST("address/updateAddressInit")
    @FormUrlEncoded
    Observable<HttpResult<EditAdressBean>> updateAddressInit(@Field("id") String id);


}
