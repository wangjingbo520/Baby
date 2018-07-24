package com.sunbaby.app.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author wangjingbo
 * @date 2018/7/10
 * describe 待收货
 */
public class PeisongAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public PeisongAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}

