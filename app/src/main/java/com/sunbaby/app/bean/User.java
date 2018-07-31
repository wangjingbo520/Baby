package com.sunbaby.app.bean;

import java.io.Serializable;

/**
 * com.sunbaby.app.bean
 *
 * @author wangjingbo
 * @date 2018/7/6
 * describe
 */
public class User implements Serializable {


    /**
     * expirationTime : null
     * userId : 9
     * userName : boob
     * type : null
     * openingTime : null
     * url : {"damageList":"/fr/goods/damageList","addressList":"/address/addressList",
     * "mobileInit":"/account/mobileInit","updatePasswordInit":"/account/updatePasswordInit",
     * "questionList":"/fr/questionList","selectAddressList":"/address/selectAddressList"}
     * mobile : 15575163734
     */

    private long expirationTime;
    private long userId;
    private String userName;
    private int type;
    private long openingTime;
    private UrlBean url;
    private long mobile;

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(long openingTime) {
        this.openingTime = openingTime;
    }

    public UrlBean getUrl() {
        return url;
    }

    public void setUrl(UrlBean url) {
        this.url = url;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public static class UrlBean {
        /**
         * damageList : /fr/goods/damageList
         * addressList : /address/addressList
         * mobileInit : /account/mobileInit
         * updatePasswordInit : /account/updatePasswordInit
         * questionList : /fr/questionList
         * selectAddressList : /address/selectAddressList
         */

        private String damageList;
        private String addressList;
        private String mobileInit;
        private String updatePasswordInit;
        private String questionList;
        private String selectAddressList;

        public String getDamageList() {
            return damageList;
        }

        public void setDamageList(String damageList) {
            this.damageList = damageList;
        }

        public String getAddressList() {
            return addressList;
        }

        public void setAddressList(String addressList) {
            this.addressList = addressList;
        }

        public String getMobileInit() {
            return mobileInit;
        }

        public void setMobileInit(String mobileInit) {
            this.mobileInit = mobileInit;
        }

        public String getUpdatePasswordInit() {
            return updatePasswordInit;
        }

        public void setUpdatePasswordInit(String updatePasswordInit) {
            this.updatePasswordInit = updatePasswordInit;
        }

        public String getQuestionList() {
            return questionList;
        }

        public void setQuestionList(String questionList) {
            this.questionList = questionList;
        }

        public String getSelectAddressList() {
            return selectAddressList;
        }

        public void setSelectAddressList(String selectAddressList) {
            this.selectAddressList = selectAddressList;
        }
    }
}
