package com.sunbaby.app.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunbaby.app.bean.SongQuhuoBean;

import java.util.List;

/**
 * @author Wangjingbo
 * @date 2018/8/3
 * describe
 */
public class QuhuoAdapter extends BaseQuickAdapter<SongQuhuoBean,
        BaseViewHolder> {

    public QuhuoAdapter(int layoutResId, @Nullable List<SongQuhuoBean>
            data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,SongQuhuoBean songQuhuoBean) {
//        helper.setText(R.id.tvName, goodsTypeListBean.getGoods_type_name());
//        GlideImageLoader.loadImage(mContext, goodsTypeListBean.getPic_url(), (ImageView) helper
//                .getView(R.id.ivPic));

    }
}