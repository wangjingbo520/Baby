package com.sunbaby.app.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunbaby.app.R;
import com.sunbaby.app.bean.PesisongBean;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.api.RequestClient;
import com.sunbaby.app.common.utils.DialogWithYesOrNoUtils;

import java.util.List;

/**
 * @author Wangjingbo
 * @date 2018/7/24
 * describe
 */
public class MyPeisongRecyAdapter extends BaseQuickAdapter<PesisongBean.ListBean, BaseViewHolder> {

    public MyPeisongRecyAdapter(int layoutResId, @Nullable List<PesisongBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PesisongBean.ListBean bean) {
        helper.setText(R.id.tvName, bean.getGoods_name());
        helper.addOnClickListener(R.id.ivDelete);
    }

}
