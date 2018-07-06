package com.sunbaby.app.bean;

import java.io.Serializable;

/**
 * com.sunbaby.app.bean
 *
 * @author 王静波
 * @date 2018/7/6
 * describe
 */
public class User implements Serializable {


    /**
     * company_short_name :
     * email :
     * company_full_name :
     * name : ZZ123785
     * is_company : 0
     * userId : CAAFBC916624B568635230436570EC4E5D968BB5CF0091E82C3D729D9E212D1Dd42b8eea
     * sign_password : “”
     * mobile : 18278945665
     */

    private String company_short_name;
    private String email;
    private String company_full_name;
    private String name;
    private int is_company;
    private String userId;
    private String sign_password;
    private String mobile;
    private int company_info_type;

    public int getCompany_info_type() {
        return company_info_type;
    }

    public void setCompany_info_type(int company_info_type) {
        this.company_info_type = company_info_type;
    }

    public String getCompany_short_name() {
        return company_short_name;
    }

    public void setCompany_short_name(String company_short_name) {
        this.company_short_name = company_short_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany_full_name() {
        return company_full_name;
    }

    public void setCompany_full_name(String company_full_name) {
        this.company_full_name = company_full_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIs_company() {
        return is_company;
    }

    public void setIs_company(int is_company) {
        this.is_company = is_company;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSign_password() {
        return sign_password;
    }

    public void setSign_password(String sign_password) {
        this.sign_password = sign_password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
