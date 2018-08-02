package com.sunbaby.app.bean;

/**
 * @author Wangjingbo
 * @date 2018/8/2
 * describe
 */
public class DamageDetailBean {

    /**
     * recoveryTime : 1531966345000
     * picUrl : 1111
     * price : 64
     * goodsPrice : 80
     * id : 1
     * goodsName : 钢铁是怎么炼成
     * userId : 8
     * status : 0
     */

    private long recoveryTime;
    private String picUrl;
    private int price;
    private int goodsPrice;
    private long id;
    private String goodsName;
    private long userId;
    private int status;

    public long getRecoveryTime() {
        return recoveryTime;
    }

    public void setRecoveryTime(long recoveryTime) {
        this.recoveryTime = recoveryTime;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(int goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
