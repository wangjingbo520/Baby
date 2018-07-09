package com.sunbaby.app.ui.activity;

import android.os.Bundle;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 搜索
 */
public class SearchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_search);
        setTitleLayoutVisiable(false);
    }
}
