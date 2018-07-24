package com.sunbaby.app.bean;

/**
 * @author wangjingbo
 * @date 2018/7/17
 * describe
 */
public class CenterBean {

    /**
     * time : 1531705102614
     * expirationTime :
     * orderNumber : {"forGoode":1,"stayRecycled":1,"stayDelivery":0}
     * openingTime :
     * photo :
     */

    private long time;
    private String expirationTime;
    private OrderNumberBean orderNumber;
    private String openingTime;
    private String photo;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public OrderNumberBean getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(OrderNumberBean orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public static class OrderNumberBean {
        /**
         * forGoode : 1
         * stayRecycled : 1
         * stayDelivery : 0
         */

        private int forGoode;
        private int stayRecycled;
        private int stayDelivery;

        public int getForGoode() {
            return forGoode;
        }

        public void setForGoode(int forGoode) {
            this.forGoode = forGoode;
        }

        public int getStayRecycled() {
            return stayRecycled;
        }

        public void setStayRecycled(int stayRecycled) {
            this.stayRecycled = stayRecycled;
        }

        public int getStayDelivery() {
            return stayDelivery;
        }

        public void setStayDelivery(int stayDelivery) {
            this.stayDelivery = stayDelivery;
        }
    }
}
