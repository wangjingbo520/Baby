package com.sunbaby.app.bean;

/**
 * @author Wangjingbo
 * @date 2018/7/27
 * describe
 */
public class AlipayBean {

    /**
     * number : 1
     * total_fee : 64.0
     * orderString :
     */

    private String number;
    private String total_fee;
    private String orderString;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getOrderString() {
        return orderString;
    }

    public void setOrderString(String orderString) {
        this.orderString = orderString;
    }
}
