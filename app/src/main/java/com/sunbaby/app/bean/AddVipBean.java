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

    private double amount;
    private long time;
    private String orderCode;
    private long orderId;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
