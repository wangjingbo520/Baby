package com.sunbaby.app.ui.fragment.product;


import android.os.Bundle;
import android.view.View;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseFragment;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe  商品
 */
public class ProductFragment extends BaseFragment {


    public static ProductFragment newInstance() {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product;
    }

    @Override
    public void initView(View view) {

    }

}
