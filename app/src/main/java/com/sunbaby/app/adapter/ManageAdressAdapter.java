package com.sunbaby.app.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunbaby.app.R;

import java.util.List;

/**
 * @author 王静波
 * @date 2018/7/9
 * describe
 */
public class ManageAdressAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ManageAdressAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.checkbox);
    }
}

