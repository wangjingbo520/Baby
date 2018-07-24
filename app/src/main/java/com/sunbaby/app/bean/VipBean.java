package com.sunbaby.app.bean;

import java.util.List;

/**
 * @author wangjingbo
 * @date 2018/7/17
 * describe
 */
public class VipBean {

    private List<VipTypeListBean> vipTypeList;

    public List<VipTypeListBean> getVipTypeList() {
        return vipTypeList;
    }

    public void setVipTypeList(List<VipTypeListBean> vipTypeList) {
        this.vipTypeList = vipTypeList;
    }

    public static class VipTypeListBean {
        /**
         * id : 3
         * vipName : 图书会员
         * vipPriceList : [{"id":6,"price":500,"effectiveTime":1,"type":2}]
         */

        private int id;
        private String vipName;
        private List<VipPriceListBean> vipPriceList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVipName() {
            return vipName;
        }

        public void setVipName(String vipName) {
            this.vipName = vipName;
        }

        public List<VipPriceListBean> getVipPriceList() {
            return vipPriceList;
        }

        public void setVipPriceList(List<VipPriceListBean> vipPriceList) {
            this.vipPriceList = vipPriceList;
        }

        public static class VipPriceListBean {
            /**
             * id : 6
             * price : 500
             * effectiveTime : 1
             * type : 2
             */

            private int id;
            private int price;
            private int effectiveTime;
            private int type;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getEffectiveTime() {
                return effectiveTime;
            }

            public void setEffectiveTime(int effectiveTime) {
                this.effectiveTime = effectiveTime;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
