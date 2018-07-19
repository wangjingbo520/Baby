package com.sunbaby.app.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunbaby.app.R;
import com.sunbaby.app.bean.AdressBean;

import java.util.List;

/**
 * @author 王静波
 * @date 2018/7/9
 * describe
 */
public class ManageAdressAdapter extends BaseQuickAdapter<AdressBean.ListBean, BaseViewHolder> {

    public ManageAdressAdapter(int layoutResId, @Nullable List<AdressBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AdressBean.ListBean item) {
        helper.addOnClickListener(R.id.radioButton);
        helper.addOnClickListener(R.id.llEditAdress);
        helper.addOnClickListener(R.id.llDeleteAdress);
    }
}

