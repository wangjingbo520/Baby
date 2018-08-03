package com.sunbaby.app.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Wangjingbo
 * @date 2018/8/3
 * describe 搜索历史记录
 */

@Entity
public class SearchBean {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String searchContent;


    @Generated(hash = 1966210574)
    public SearchBean(Long id, @NotNull String searchContent) {
        this.id = id;
        this.searchContent = searchContent;
    }

    @Generated(hash = 562045751)
    public SearchBean() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSearchContent() {
        return searchContent == null ? "" : searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }
}
