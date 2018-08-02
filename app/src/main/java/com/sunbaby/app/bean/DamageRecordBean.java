package com.sunbaby.app.bean;

import java.util.List;

/**
 * @author Wangjingbo
 * @date 2018/8/2
 * describe
 */
public class DamageRecordBean {

    /**
     * total : 1
     * currPage : 1
     * pages : 1
     * pageSize : 20
     * list : [{"picUrl":"1111","goodsId":9,"goodsDamageId":1,"goodsName":"钢铁是怎么炼成","status":0}]
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
         * picUrl : 1111
         * goodsId : 9
         * goodsDamageId : 1
         * goodsName : 钢铁是怎么炼成
         * status : 0
         */

        private String picUrl;
        private long goodsId;
        private long goodsDamageId;
        private String goodsName;
        private int status;

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public long getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(long goodsId) {
            this.goodsId = goodsId;
        }

        public long getGoodsDamageId() {
            return goodsDamageId;
        }

        public void setGoodsDamageId(long goodsDamageId) {
            this.goodsDamageId = goodsDamageId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
