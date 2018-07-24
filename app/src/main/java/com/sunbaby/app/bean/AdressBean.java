package com.sunbaby.app.bean;

import java.util.List;

/**
 * @author wangjingbo
 * @date 2018/7/19
 * describe
 */
public class AdressBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 3
         * address : 山西省阳泉市宝安幼儿园顺平县56465656
         * status : 0
         * name : 6546565
         */

        private long id;
        private String address;
        private int status;
        private String name;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
