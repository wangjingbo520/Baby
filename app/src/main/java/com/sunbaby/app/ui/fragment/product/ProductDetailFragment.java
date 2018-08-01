package com.sunbaby.app.ui.fragment.product;


import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.sunbaby.app.R;
import com.sunbaby.app.bean.ProductBean;
import com.sunbaby.app.callback.IProductView;
import com.sunbaby.app.common.base.BaseFragment;
import com.sunbaby.app.presenter.ProductPresenter;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe  商品详情
 */
public class ProductDetailFragment extends BaseFragment implements IProductView {
    private ProductPresenter productPresenter;
    /**
     * 商品详情id
     */
    private String goods_id = "";
    private WebView webView;

    public static ProductDetailFragment newInstance(String goods_id) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putString("goods_id", goods_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            goods_id = getArguments().getString("goods_id");
        }
    }

    @Override
    public void initData() {
        productPresenter.queryGoodsDetails(goods_id);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_detail;
    }

    @Override
    public void initView(View view) {
        webView = view.findViewById(R.id.webview);
        productPresenter = new ProductPresenter(mContext, this);
    }

    @Override
    public void queryGoodsDetails(ProductBean productBean) {


    }

    @Override
    public void joinDistributionBox(Object object) {

    }


}
