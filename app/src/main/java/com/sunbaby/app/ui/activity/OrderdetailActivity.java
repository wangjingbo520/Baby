package com.sunbaby.app.ui.activity;

import android.os.Bundle;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 订单详情
 */
public class OrderdetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_orderdetail);
        setTitle("订单详情");
        initData();
    }

    private void initData() {
    }
}
