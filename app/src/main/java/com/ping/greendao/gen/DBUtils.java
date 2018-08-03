package com.ping.greendao.gen;

import com.sunbaby.app.MyApplication;
import com.sunbaby.app.bean.SearchBean;

import java.util.List;

/**
 * @author Wangjingbo
 * @date 2018/8/3
 * describe 历史搜索的增删改查
 */
public class DBUtils {
    /**
     * 插入
     *
     * @param searchBean
     */
    public static void addSearchData(SearchBean searchBean) {
        MyApplication.getDaoInstant().getSearchBeanDao().insert(searchBean);
    }

    /**
     * 删除全部
     */
    public static void deleteAll() {
        MyApplication.getDaoInstant().getSearchBeanDao().deleteAll();
    }

    public static List<SearchBean> queryAll() {
        return MyApplication.getDaoInstant().getSearchBeanDao().loadAll();
    }
}
