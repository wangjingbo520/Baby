package com.sunbaby.app.bean;

import java.util.List;

/**
 * @author wangjingbo
 * @date 2018/7/20
 * describe
 */
public class SearchHistoryBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * scount_name : ok
         */

        private String scount_name;

        public String getScount_name() {
            return scount_name;
        }

        public void setScount_name(String scount_name) {
            this.scount_name = scount_name;
        }
    }
}
