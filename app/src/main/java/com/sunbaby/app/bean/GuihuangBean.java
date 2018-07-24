package com.sunbaby.app.bean;

/**
 * @author wangjingbo
 * @date 2018/7/10
 * describe
 */
public class GuihuangBean {
    private String name;
    public boolean isSelect;


    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
