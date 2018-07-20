package com.sunbaby.app.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunbaby.app.bean.SecondGoodsListBean;

import java.util.List;

/**
 * @author 王静波
 * @date 2018/7/20
 * describe 商品二级分类
 */
public class RecySecondaryListAdapter extends BaseQuickAdapter<SecondGoodsListBean.ListBean,
        BaseViewHolder> {

    public RecySecondaryListAdapter(int layoutResId, @Nullable List<SecondGoodsListBean.ListBean>
            data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SecondGoodsListBean.ListBean item) {

    }
}