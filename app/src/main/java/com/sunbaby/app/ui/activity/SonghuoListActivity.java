package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;

import butterknife.BindView;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 送货订单列表
 */

public class SonghuoListActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_songhuo_list);
        setTitle("送货订单列表");
    }
}
