package com.sunbaby.app.common.api;


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
     * 获取手机验证码
     *
     * @param phone
     * @param type
     * @return
     */
    @POST("user/getValidateCode.do")
    @FormUrlEncoded
    Observable<HttpResult<String>> getValidateCode(@Field("phone") String phone, @Field("type")
            String type);

    /**
     * 用户注册
     *
     * @param mobile
     * @param password
     * @param phone
     * @return
     */
    @POST("user/appRegister.do")
    @FormUrlEncoded
    Observable<HttpResult<Object>> appRegister(@Field("code") String mobile, @Field("password")
            String password, @Field("phone") String phone);


}
