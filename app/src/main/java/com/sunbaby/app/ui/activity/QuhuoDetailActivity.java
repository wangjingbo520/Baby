package com.sunbaby.app.ui.activity;

import android.os.Bundle;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 取货详情
 */
public class QuhuoDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_quhuo_detail);
        setTitle("取货详情");
        initData();
    }

    private void initData() {
    }
}
