package com.sunbaby.app.ui.activity;

import android.os.Bundle;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 商品详情
 */
public class ProductDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_product_details);
        setTitle("商品详情");
    }
}
