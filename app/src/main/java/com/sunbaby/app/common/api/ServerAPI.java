package com.sunbaby.app.common.api;


import com.sunbaby.app.bean.AddVipBean;
import com.sunbaby.app.bean.AdressBean;
import com.sunbaby.app.bean.Areabean;
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
import com.sunbaby.app.bean.UploadFile;
import com.sunbaby.app.bean.User;
import com.sunbaby.app.bean.VipBean;
import com.sunbaby.app.bean.YouerYuan;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * wangjingbo
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
     * 个人资料修改
     *
     * @param photo
     * @param userName
     * @param userId
     * @param sex
     * @return
     */
    @POST("account/updatePersonal")
    @FormUrlEncoded
    Observable<HttpResult<Object>> updatePersonal(@Field("photo") String photo,
                                                  @Field("userName") String userName,
                                                  @Field("userId") String userId,
                                                  @Field("sex") String sex);

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
     * 短息验证
     *
     * @param mobile
     * @param code
     * @param scene
     * @return
     */
    @POST("account/updateMobilesVerify")
    @FormUrlEncoded
    Observable<HttpResult<Object>> updateMobilesVerify(@Field("mobile") String mobile,
                                                       @Field("code") String code,
                                                       @Field("scene") String scene);

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

    /**
     * 首页随机商品
     *
     * @param type 1 图书 2 玩具 0 全部
     * @return
     */
    @POST("home/queryGoodsByRand")
    @FormUrlEncoded
    Observable<HttpResult<QueryGoodsByRandBean>> queryGoodsByRand(@Field("type") String type);

    /**
     * 商品分类 一级请求
     *
     * @param type
     * @return
     */
    @POST("fr/goods/queryGoodsType")
    @FormUrlEncoded
    Observable<HttpResult<ClassificationBean>> queryGoodsType(@Field("type") String type);


    /**
     * 商品二级类型
     *
     * @param type
     * @param scount_name
     * @param User_id
     * @param currPage
     * @param pageSize
     * @return
     */
    @POST("fr/goods/querydayGoodsByRand")
    @FormUrlEncoded
    Observable<HttpResult<SecondGoodsListBean>> querydayGoodsByRand(@Field("type") String type,
                                                                    @Field("scount_name") String
                                                                            scount_name,
                                                                    @Field("user_id") String
                                                                            User_id,
                                                                    @Field("currPage") int
                                                                            currPage,
                                                                    @Field("pageSize") int
                                                                            pageSize);

    /**
     * 历史搜索记录
     *
     * @param user_id
     * @param scount_name
     * @return
     */
    @POST("fr/goods/queryAccountSearch")
    @FormUrlEncoded
    Observable<HttpResult<SearchHistoryBean>> queryAccountSearch(@Field("user_id") String user_id,
                                                                 @Field("scount_name") String
                                                                         scount_name);

    /**
     * 会员数据初始化
     *
     * @param userId
     * @return
     */
    @POST("order/applyRefundInit")
    @FormUrlEncoded
    Observable<HttpResult<JoinBean2>> applyRefundInit(@Field("userId") String userId);

    /**
     * 支付方式
     *
     * @return
     */
    @POST("order/queryPayMethod")
    Observable<HttpResult<PayBean>> queryPayMethod();


    /**
     * 上传图片
     *
     * @return
     */
    @POST("common/imgUpload")
    @FormUrlEncoded
    Observable<HttpResult<UploadFile>> uploadFile(
            @Field("imgStr") String imgStr);


    /**
     * 配送箱列表
     *
     * @param user_id
     * @return
     */
    @POST("fr/dispatching/queryDispatching")
    @FormUrlEncoded
    Observable<HttpResult<PesisongBean>> queryDispatching(
            @Field("user_id") String user_id);


    /**
     * 删除配送箱商品
     *
     * @param user_id
     * @param dispatching_id
     * @return
     */
    @POST("fr/dispatching/deleteDispatching")
    @FormUrlEncoded
    Observable<HttpResult<Object>> deleteDispatching(
            @Field("user_id") String user_id,
            @Field("dispatching_id") String dispatching_id);

    /**
     * 添加到配送箱
     *
     * @param goodsId
     * @return
     */
    @POST("fr/dispatching/joinDistributionBox")
    @FormUrlEncoded
    Observable<HttpResult<Object>> joinDistributionBox(@Field("goodsId") String goodsId);


    /**
     * 配送箱确认
     * @param user_id
     * @param dispatchingJson
     * @return
     */
    @POST("fr/dispatching/affirmDispatching")
    @FormUrlEncoded
    Observable<HttpResult<Object>> affirmDispatching(@Field("user_id") String user_id,
                                                     @Field("dispatchingJson") String
                                                             dispatchingJson);
}
