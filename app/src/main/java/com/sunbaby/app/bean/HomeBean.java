package com.sunbaby.app.bean;

import java.util.List;

/**
 * @author wangjingbo
 * @date 2018/7/18
 * describe
 */
public class HomeBean{

    private List<BannerBean> banner;
    private List<FunctionalDiagramBean> functional_diagram;

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<FunctionalDiagramBean> getFunctional_diagram() {
        return functional_diagram;
    }

    public void setFunctional_diagram(List<FunctionalDiagramBean> functional_diagram) {
        this.functional_diagram = functional_diagram;
    }

    public static class BannerBean {
        /**
         * id : 1
         * no : 1
         * location : 首页banner
         * image_filename : http://d1.cto3.shovesoft.com:12300/2018/5/22/1529638009954.jpg
         * url : askjdd
         * status : 1
         * channel : 首页
         * statusStr : 显示
         */

        private int id;
        private int no;
        private String location;
        private String image_filename;
        private String url;
        private int status;
        private String channel;
        private String statusStr;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getImage_filename() {
            return image_filename;
        }

        public void setImage_filename(String image_filename) {
            this.image_filename = image_filename;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getStatusStr() {
            return statusStr;
        }

        public void setStatusStr(String statusStr) {
            this.statusStr = statusStr;
        }
    }

    public static class FunctionalDiagramBean {
        /**
         * id : 208
         * no : 2
         * location : 首页全部图书
         * image_filename : http://d1.cto3.shovesoft.com:12300/2018/5/22/1529638009954.jpg
         * url : http://test.jhm.com/coupon
         * status : 1
         * channel : 首页
         * statusStr : 显示
         */

        private int id;
        private int no;
        private String location;
        private String image_filename;
        private String url;
        private int status;
        private String channel;
        private String statusStr;
        private List<FunctionalDiagramBean> functional_diagram;
        private List<FunctionalDiagramBean> banner;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getImage_filename() {
            return image_filename;
        }

        public void setImage_filename(String image_filename) {
            this.image_filename = image_filename;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getStatusStr() {
            return statusStr;
        }

        public void setStatusStr(String statusStr) {
            this.statusStr = statusStr;
        }

        public List<FunctionalDiagramBean> getFunctional_diagram() {
            return functional_diagram;
        }

        public void setFunctional_diagram(List<FunctionalDiagramBean> functional_diagram) {
            this.functional_diagram = functional_diagram;
        }

        public List<FunctionalDiagramBean> getBanner() {
            return banner;
        }

        public void setBanner(List<FunctionalDiagramBean> banner) {
            this.banner = banner;
        }
    }
}
