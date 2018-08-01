package com.sunbaby.app.bean;

import java.util.List;

/**
 * @author Wangjingbo
 * @date 2018/8/1
 * describe
 */
public class ProductBean {

    /**
     * details : <p>									&nbsp; &nbsp; &nbsp;</p><p>
     *     &nbsp; &nbsp; &nbsp;
     * </p><p>2342342</p><p>
     &nbsp;	</p><p>
     &nbsp;	</p>
     * goods_id : 6
     * picList : [{"pic_url":"1111"}]
     */

    private String details;
    private int goods_id;
    private List<PicListBean> picList;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public List<PicListBean> getPicList() {
        return picList;
    }

    public void setPicList(List<PicListBean> picList) {
        this.picList = picList;
    }

    public static class PicListBean {
        /**
         * pic_url : 1111
         */

        private String pic_url;

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }
    }
}
