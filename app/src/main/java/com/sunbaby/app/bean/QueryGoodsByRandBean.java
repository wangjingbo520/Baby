package com.sunbaby.app.bean;

/**
 * @author wangjingbo
 * @date 2018/7/20
 * describe
 */
public class QueryGoodsByRandBean {

    /**
     * goods_name : 222
     * id : 7
     * pic_url : 1111
     */

    private String goods_name;
    private long id;
    private String pic_url;

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
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
