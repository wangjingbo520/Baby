package com.sunbaby.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.sunbaby.app.R;
import com.sunbaby.app.adapter.SunhuaiAdapter;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.widget.MyRecycleViewDivider;
import com.sunbaby.app.ui.activity.orderdetail.OneDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 损坏记录
 */
public class DamageRecordActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;

    private SunhuaiAdapter sunhuaiAdapter;
    private List<String> strings;

    public static void start(Context context) {
        Intent starter = new Intent(context, DamageRecordActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("损坏记录");
        strings = new ArrayList<>();
        smartrefreshlayout.setRefreshHeader(new ClassicsHeader(mContext));
        smartrefreshlayout.setRefreshFooter(new ClassicsFooter(mContext));
        smartrefreshlayout.setEnableLoadmore(false);
        for (int i = 0; i < 10; i++) {
            strings.add("");
        }

        sunhuaiAdapter = new SunhuaiAdapter(R.layout.recy_item_sunhuai, strings);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new MyRecycleViewDivider(mContext, LinearLayoutManager
                .HORIZONTAL, 15,
                ContextCompat.getColor(mContext, R.color.background)));
        mRecyclerView.setAdapter(sunhuaiAdapter);
        sunhuaiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DamageDetailActivity.start(mContext);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_damage_record;
    }

}
