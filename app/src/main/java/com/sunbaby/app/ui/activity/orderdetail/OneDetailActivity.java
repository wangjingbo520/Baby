package com.sunbaby.app.ui.activity.orderdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 订单详情,待发货
 */
public class OneDetailActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, OneDetailActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_one_detail);
        setTitle("订单详情");
    }
}
