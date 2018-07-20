package com.sunbaby.app.bean;

import java.util.List;

/**
 * @author 王静波
 * @date 2018/7/20
 * describe
 */
public class SecondGoodsListBean {

    /**
     * total : 6
     * currPage : 1
     * pages : 2
     * pageSize : 5
     * list : [{"goods_name":"spring","goods_price":52,"id":5,"pic_url":"1111"}]
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
         * goods_name : spring
         * goods_price : 52
         * id : 5
         * pic_url : 1111
         */

        private String goods_name;
        private int goods_price;
        private long id;
        private String pic_url;

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public int getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(int goods_price) {
            this.goods_price = goods_price;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }
    }
}
