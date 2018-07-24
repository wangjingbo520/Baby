package com.sunbaby.app.bean;

import java.util.List;

/**
 * @author wangjingbo
 * @date 2018/7/23
 * describe
 */
public class PayBean {

    private List<PayListBean> payList;

    public List<PayListBean> getPayList() {
        return payList;
    }

    public void setPayList(List<PayListBean> payList) {
        this.payList = payList;
    }

    public static class PayListBean {
        /**
         * id : 1
         * name : 支付宝
         * url : 1
         */

        private long id;
        private String name;
        private String url;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
