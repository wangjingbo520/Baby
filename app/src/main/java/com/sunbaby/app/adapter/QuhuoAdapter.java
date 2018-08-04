package com.sunbaby.app.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunbaby.app.R;
import com.sunbaby.app.bean.SongQuhuoBean;
import com.sunbaby.app.common.utils.GlideImageLoader;

import java.util.List;

/**
 * @author Wangjingbo
 * @date 2018/8/3
 * describe
 */
public class QuhuoAdapter extends BaseQuickAdapter<SongQuhuoBean.ListBean,
        BaseViewHolder> {

    public QuhuoAdapter(int layoutResId, @Nullable List<SongQuhuoBean.ListBean>
            data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SongQuhuoBean.ListBean songQuhuoBean) {
        helper.setText(R.id.tvName, songQuhuoBean.getGoods_name());
        helper.setText(R.id.tvOrderNumber, "订单号: " + songQuhuoBean.getOrder_num());
        int delivery_status = songQuhuoBean.getDelivery_status();
        switch (delivery_status) {
            case 4:
                helper.setText(R.id.tvStatus, "已到达");
                break;
            case 7:
                helper.setText(R.id.tvStatus, "未达到");
                break;
            default:
                break;
        }
        GlideImageLoader.loadImage(mContext, songQuhuoBean.getPic_url(), (ImageView) helper
                .getView(R.id.ivPic));

    }
}