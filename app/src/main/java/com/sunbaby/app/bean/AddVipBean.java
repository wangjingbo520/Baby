package com.sunbaby.app.bean;

/**
 * @author wangjingbo
 * @date 2018/7/17
 * describe
 */
public class AddVipBean {

    /**
     * amount : 500
     * time : 1531466288864
     * orderCode : 201807131518729448
     * orderId : 1
     */

    private int amount;
    private long time;
    private String orderCode;
    private int orderId;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
