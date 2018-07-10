package com.sunbaby.app.ui.activity.orderdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 待收货订单详情
 */
public class TwoDetailActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, TwoDetailActivity.class);
        context.startActivity(starter);
    }

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
