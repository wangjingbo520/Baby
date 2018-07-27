package com.sunbaby.app.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author Wangjingbo
 * @date 2018/7/27
 * describe
 */
public class WeChatPayBean {

    /**
     * number : 1
     * package : Sign=WXPay
     * total_fee : 0.1
     * appid : wx544fb8293a8d4fda
     * sign : 9D0B7053EC3C80C936CDBAB4189C4150
     * partnerid : 1464352402
     * prepayid : wx190915414001455c81a8a52e1582667951
     * noncestr : 701001
     * prepay_id : wx190915414001455c81a8a52e1582667951
     * timestamp : 1531962940
     */

    private String number;
    @SerializedName("package")
    private String packageX;
    private String total_fee;
    private String appid;
    private String sign;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private String prepay_id;
    private String timestamp;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
