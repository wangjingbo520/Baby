package com.sunbaby.app.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunbaby.app.R;
import com.sunbaby.app.bean.ClassificationBean;
import com.sunbaby.app.common.utils.GlideImageLoader;

import java.util.List;

/**
 * @author wangjingbo
 * @date 2018/7/11
 * describe
 */
public class GoodsTypeAdapter extends BaseQuickAdapter<ClassificationBean.GoodsTypeListBean,
        BaseViewHolder> {

    public GoodsTypeAdapter(int layoutResId, @Nullable List<ClassificationBean.GoodsTypeListBean>
            data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassificationBean.GoodsTypeListBean
            goodsTypeListBean) {
        helper.setText(R.id.tvName, goodsTypeListBean.getGoods_type_name());
        GlideImageLoader.loadImage(mContext, goodsTypeListBean.getPic_url(), (ImageView) helper
                .getView(R.id.ivPic));

    }
}

