package com.sunbaby.app.bean;

import java.util.List;

/**
 * @author Wangjingbo
 * @date 2018/8/2
 * describe
 */
public class SongQuhuoBean {


    /**
     * total : 3
     * currPage : 1
     * pages : 1
     * pageSize : 10
     * list : [{"id":34,"order_num":"201808031347177183","pic_url":"","goods_type_name":"",
     * "goods_id":1111,"goods_name":"","goods_num":5,"time":1533275225000,"status":1,
     * "goods_type_id":11,"user_id":11,"deliveryman_id":12,"delivery_status":9,"goods_pic":"",
     * "given_num":12,"is_given":false,"dispatchingId":11}]
     */

    private int total;
    private int currPage;
    private int pages;
    private int pageSize;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 34
         * order_num : 201808031347177183
         * pic_url :
         * goods_type_name :
         * goods_id : 1111
         * goods_name :
         * goods_num : 5
         * time : 1533275225000
         * status : 1
         * goods_type_id : 11
         * user_id : 11
         * deliveryman_id : 12
         * delivery_status : 9
         * goods_pic :
         * given_num : 12
         * is_given : false
         * dispatchingId : 11
         */

        private long id;
        private String order_num;
        private String pic_url;
        private String goods_type_name;
        private int goods_id;
        private String goods_name;
        private int goods_num;
        private long time;
        private int status;
        private long goods_type_id;
        private long user_id;
        private long deliveryman_id;
        private int delivery_status;
        private String goods_pic;
        private int given_num;
        private boolean is_given;
        private int dispatchingId;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getGoods_type_name() {
            return goods_type_name;
        }

        public void setGoods_type_name(String goods_type_name) {
            this.goods_type_name = goods_type_name;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public int getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(int goods_num) {
            this.goods_num = goods_num;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getGoods_type_id() {
            return goods_type_id;
        }

        public void setGoods_type_id(long goods_type_id) {
            this.goods_type_id = goods_type_id;
        }

        public long getUser_id() {
            return user_id;
        }

        public void setUser_id(long user_id) {
            this.user_id = user_id;
        }

        public long getDeliveryman_id() {
            return deliveryman_id;
        }

        public void setDeliveryman_id(long deliveryman_id) {
            this.deliveryman_id = deliveryman_id;
        }

        public int getDelivery_status() {
            return delivery_status;
        }

        public void setDelivery_status(int delivery_status) {
            this.delivery_status = delivery_status;
        }

        public String getGoods_pic() {
            return goods_pic;
        }

        public void setGoods_pic(String goods_pic) {
            this.goods_pic = goods_pic;
        }

        public int getGiven_num() {
            return given_num;
        }

        public void setGiven_num(int given_num) {
            this.given_num = given_num;
        }

        public boolean isIs_given() {
            return is_given;
        }

        public void setIs_given(boolean is_given) {
            this.is_given = is_given;
        }

        public int getDispatchingId() {
            return dispatchingId;
        }

        public void setDispatchingId(int dispatchingId) {
            this.dispatchingId = dispatchingId;
        }
    }
}
