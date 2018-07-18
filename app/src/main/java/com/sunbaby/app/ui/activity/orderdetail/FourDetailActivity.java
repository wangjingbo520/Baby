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
 * describe 订单详情,已完成
 */
public class FourDetailActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, FourDetailActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("订单详情");
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_four_detail;
    }
}
