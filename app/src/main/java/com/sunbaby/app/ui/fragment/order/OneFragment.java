package com.sunbaby.app.ui.fragment.order;


import android.os.Bundle;
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
import com.sunbaby.app.adapter.FahuoAdapter;
import com.sunbaby.app.common.base.BaseFragment;
import com.sunbaby.app.common.widget.MyRecycleViewDivider;
import com.sunbaby.app.ui.activity.orderdetail.OneDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe  待发货
 */
public class OneFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private SmartRefreshLayout smartrefreshlayout;
    private FahuoAdapter fahuoAdapter;
    private List<String> strings;

    public static OneFragment newInstance() {
        OneFragment fragment = new OneFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData() {
        for (int i = 0; i < 10; i++) {
            strings.add("");
        }
        fahuoAdapter = new FahuoAdapter(R.layout.recy_item_daifahuo, strings);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new MyRecycleViewDivider(mContext, LinearLayoutManager
                .HORIZONTAL, 15,
                ContextCompat.getColor(mContext, R.color.background)));
        mRecyclerView.setAdapter(fahuoAdapter);
        fahuoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OneDetailActivity.start(mContext);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_one;
    }

    @Override
    public void initView(View view) {
        strings = new ArrayList<>();
        mRecyclerView = view.findViewById(R.id.recyclerview);
        smartrefreshlayout = view.findViewById(R.id.smartrefreshlayout);
        smartrefreshlayout.setRefreshHeader(new ClassicsHeader(mContext));
        smartrefreshlayout.setRefreshFooter(new ClassicsFooter(mContext));
        smartrefreshlayout.setEnableLoadmore(false);
    }

}
