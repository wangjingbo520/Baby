package com.sunbaby.app.bean;

import java.util.List;

/**
 * @author wangjingbo
 * @date 2018/7/20
 * describe
 */
public class ClassificationBean {

    private List<GoodsTypeListBean> goodsTypeList;

    public List<GoodsTypeListBean> getGoodsTypeList() {
        return goodsTypeList;
    }

    public void setGoodsTypeList(List<GoodsTypeListBean> goodsTypeList) {
        this.goodsTypeList = goodsTypeList;
    }

    public static class GoodsTypeListBean {
        /**
         * id : 5
         * time : 1531302313000
         * update_time :
         * goods_type_name : 历史
         * status : 0
         * parent_id : 1
         * pic_url : 1111
         * sort : 2
         */

        private long id;
        private long time;
        private String update_time;
        private String goods_type_name;
        private int status;
        private int parent_id;
        private String pic_url;
        private int sort;

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

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getGoods_type_name() {
            return goods_type_name;
        }

        public void setGoods_type_name(String goods_type_name) {
            this.goods_type_name = goods_type_name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}
