package com.sunbaby.app.ui.activity.orderdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 订单详情,待归还
 */
public class ThreeDetailActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, ThreeDetailActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_three_detail);
        setTitle("订单详情");
    }

}
