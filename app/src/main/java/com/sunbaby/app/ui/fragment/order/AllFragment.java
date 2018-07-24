package com.sunbaby.app.ui.fragment.order;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sunbaby.app.R;
import com.sunbaby.app.adapter.RecyDemoAdapter;
import com.sunbaby.app.common.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe  全部
 */
public class AllFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private int currentPage = 1;
    private int pageSize = 10;

    private List<String> strings;
    private RecyDemoAdapter recyDemoAdapter;


    public static AllFragment newInstance() {
        AllFragment fragment = new AllFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData() {
        strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        recyDemoAdapter = new RecyDemoAdapter(R.layout.recy_item_demo, strings);
        mRecyclerView.setAdapter(recyDemoAdapter);
        recyDemoAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, mRecyclerView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all;
    }

    @Override
    public void initView(View view) {
        // 超过两个Fragment后就使用findViewById的方式,不用注解方式找到控件
        mRecyclerView = view.findViewById(R.id.recyclerview);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    /**
     * 下拉刷新
     */
    private void refresh() {
        //这里的作用是防止下拉刷新的时候还可以上拉加载
        recyDemoAdapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setData(true, null);
                recyDemoAdapter.setEnableLoadMore(true);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    private void loadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
                //    recyDemoAdapter.loadMoreFail();
                //第一次进入后台就没有数据
//                recyDemoAdapter.setEmptyView(R.layout.activity_login, (ViewGroup) mRecyclerView
//                        .getParent());
                setData(false, null);
            }
        }, 1000);
    }


    private void setData(boolean isRefresh, List data) {
        currentPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            //之前的数据清空,重新设置
            recyDemoAdapter.setNewData(data);
        } else {
            if (size > 0) {
                //追加数据
                recyDemoAdapter.addData(data);
            }
        }
        if (size < pageSize) {
            //第一页如果不够一页就不显示没有更多数据布局,这里证明后台没有数据了
            recyDemoAdapter.loadMoreEnd(isRefresh);
            Toast.makeText(mContext, "no more data", Toast.LENGTH_SHORT).show();
        } else {
            // 加载完成（注意不是加载结束，而是本次数据加载结束并且还有下页数据）
            recyDemoAdapter.loadMoreComplete();
        }
    }

}
