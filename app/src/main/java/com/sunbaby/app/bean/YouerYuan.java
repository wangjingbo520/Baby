package com.sunbaby.app.bean;

import java.util.List;

/**
 * @author wangjingbo
 * @date 2018/7/16
 * describe
 */
public class YouerYuan {

    private List<RegionListBean> regionList;

    public List<RegionListBean> getRegionList() {
        return regionList;
    }

    public void setRegionList(List<RegionListBean> regionList) {
        this.regionList = regionList;
    }

    public static class RegionListBean {
        /**
         * id : 1
         * name : 1班
         */

        private int id;
        private String name;
        private String className;

        public String getClassName() {
            return className == null ? "" : className;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
