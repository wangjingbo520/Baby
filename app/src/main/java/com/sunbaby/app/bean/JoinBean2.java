package com.sunbaby.app.bean;

/**
 * @author 王静波
 * @date 2018/7/23
 * describe
 */
public class JoinBean2 {

    /**
     * orderData : {"amount":500,"id":1,"orderCode":"201807131350026049","vipTypeId":6,
     * "pyaTime":1531474717000,"vipPriceId":1}
     * status : 1
     */

    private OrderDataBean orderData;
    private int status;

    public OrderDataBean getOrderData() {
        return orderData;
    }

    public void setOrderData(OrderDataBean orderData) {
        this.orderData = orderData;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class OrderDataBean {
        /**
         * amount : 500
         * id : 1
         * orderCode : 201807131350026049
         * vipTypeId : 6
         * pyaTime : 1531474717000
         * vipPriceId : 1
         */

        private int amount;
        private long id;
        private String orderCode;
        private int vipTypeId;
        private long pyaTime;
        private int vipPriceId;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public int getVipTypeId() {
            return vipTypeId;
        }

        public void setVipTypeId(int vipTypeId) {
            this.vipTypeId = vipTypeId;
        }

        public long getPyaTime() {
            return pyaTime;
        }

        public void setPyaTime(long pyaTime) {
            this.pyaTime = pyaTime;
        }

        public int getVipPriceId() {
            return vipPriceId;
        }

        public void setVipPriceId(int vipPriceId) {
            this.vipPriceId = vipPriceId;
        }
    }
}
