package com.sunbaby.app.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author 王静波
 * @date 2018/7/11
 * describe
 */
public class WanjuAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public WanjuAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
