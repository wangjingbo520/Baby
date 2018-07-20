package com.sunbaby.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;

import butterknife.OnClick;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 填写订单
 */
public class FillOrderActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, FillOrderActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("填写订单");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fill_order;
    }


    @OnClick(R.id.llSelectAdress)
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.llSelectAdress:
                ManageAddressActivity.start(this);
                break;
            default:
                break;
        }
    }

}
