package com.sunbaby.app.bean;

import java.util.List;

/**
 * @author wangjingbo
 * @date 2018/7/23
 * describe
 */
public class PesisongBean {

    /**
     * goods_toy_num : 0
     * holding_toy_max : 6
     * list : [{"goods_name":"spring","user_id":1,"goods_id":5,"goods_num":5,"id":1,
     * "time":1531807370000,"goods_type_id":1,"status":0}]
     * goods_books_num : 5
     * holding_books_max : 9
     */

    private int goods_toy_num;
    private String holding_toy_max;
    private int goods_books_num;
    private String holding_books_max;
    private List<ListBean> list;

    public int getGoods_toy_num() {
        return goods_toy_num;
    }

    public void setGoods_toy_num(int goods_toy_num) {
        this.goods_toy_num = goods_toy_num;
    }

    public String getHolding_toy_max() {
        return holding_toy_max;
    }

    public void setHolding_toy_max(String holding_toy_max) {
        this.holding_toy_max = holding_toy_max;
    }

    public int getGoods_books_num() {
        return goods_books_num;
    }

    public void setGoods_books_num(int goods_books_num) {
        this.goods_books_num = goods_books_num;
    }

    public String getHolding_books_max() {
        return holding_books_max;
    }

    public void setHolding_books_max(String holding_books_max) {
        this.holding_books_max = holding_books_max;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * goods_name : spring
         * user_id : 1
         * goods_id : 5
         * goods_num : 5
         * id : 1
         * time : 1531807370000
         * goods_type_id : 1
         * status : 0
         */

        private String goods_name;
        private long user_id;
        private long goods_id;
        private int goods_num;
        private long id;
        private long time;
        private long goods_type_id;
        private int status;

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public long getUser_id() {
            return user_id;
        }

        public void setUser_id(long user_id) {
            this.user_id = user_id;
        }

        public long getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(long goods_id) {
            this.goods_id = goods_id;
        }

        public int getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(int goods_num) {
            this.goods_num = goods_num;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public long getGoods_type_id() {
            return goods_type_id;
        }

        public void setGoods_type_id(long goods_type_id) {
            this.goods_type_id = goods_type_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
