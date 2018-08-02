package com.sunbaby.app.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunbaby.app.R;
import com.sunbaby.app.bean.DamageRecordBean;
import com.sunbaby.app.common.utils.GlideImageLoader;

import java.util.List;

/**
 * @author wangjingbo
 * @date 2018/7/11
 * describe
 */
public class SunhuaiAdapter extends BaseQuickAdapter<DamageRecordBean.ListBean, BaseViewHolder> {


    public SunhuaiAdapter(int layoutResId, @Nullable List<DamageRecordBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DamageRecordBean.ListBean item) {
        helper.setText(R.id.tvName, item.getGoodsName());
        GlideImageLoader.loadImage(mContext, item.getPicUrl(), (ImageView) helper.getView(R.id
                .ivPic));
        int status = item.getStatus();
        //  "status": 0 //是否购买 0 默认未购买 1已购买
        if (0 == status) {
            helper.setText(R.id.tvStatus, "未购买");
        } else {
            helper.setText(R.id.tvStatus, "已购买");
        }
    }
}

