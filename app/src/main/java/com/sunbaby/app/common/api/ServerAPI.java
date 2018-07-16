package com.sunbaby.app.common.api;


import com.sunbaby.app.bean.Areabean;
import com.sunbaby.app.bean.User;
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


}
