package com.sunbaby.app.ui.fragment.product;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.sunbaby.app.R;
import com.sunbaby.app.bean.ProductBean;
import com.sunbaby.app.callback.IProductView;
import com.sunbaby.app.common.base.BaseFragment;
import com.sunbaby.app.common.utils.GlideImageLoader;
import com.sunbaby.app.common.utils.statusbartils.BannerImageLoader;
import com.sunbaby.app.presenter.ProductPresenter;
import com.sunbaby.app.ui.activity.ImageBrowseActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe  商品
 */
public class ProductFragment extends BaseFragment implements IProductView {
    /**
     * 商品详情id
     */
    private String goods_id = "";
    private ProductPresenter productPresenter;
    private Banner banner;
    private WebView webView;
    private ArrayList<String> picUrlList = new ArrayList<>();

    public static ProductFragment newInstance(String goods_id) {
        ProductFragment fragment = new ProductFragment();
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
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (picUrlList != null && picUrlList.get(position) != null) {
                    ImageBrowseActivity.start(mContext, picUrlList, position);
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product;
    }

    @Override
    public void initView(View view) {
        banner = view.findViewById(R.id.banner);
        webView = view.findViewById(R.id.webview);
        banner.isAutoPlay(false);
        banner.updateBannerStyle(BannerConfig.NUM_INDICATOR);
        productPresenter = new ProductPresenter(mContext, this);
    }

    @Override
    public void queryGoodsDetails(ProductBean productBean) {
        picUrlList.clear();
        for (int i = 0; i < productBean.getPicList().size(); i++) {
            picUrlList.add(productBean.getPicList().get(i).getPic_url());
        }
        banner.setImages(picUrlList)
                .setImageLoader(new BannerImageLoader())
                .start();
        webView.loadDataWithBaseURL(null, productBean.getDetails(),
                "text/html", "utf-8", null);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
    }

    @Override
    public void joinDistributionBox(Object object) {

    }


}
