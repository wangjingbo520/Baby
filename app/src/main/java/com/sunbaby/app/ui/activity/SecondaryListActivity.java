package com.sunbaby.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunbaby.app.R;
import com.sunbaby.app.adapter.RecySecondaryListAdapter;
import com.sunbaby.app.bean.SecondGoodsListBean;
import com.sunbaby.app.callback.ISecondaryListView;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.utils.UIUtils;
import com.sunbaby.app.common.widget.GridSpacingItemDecoration;
import com.sunbaby.app.presenter.SecondaryListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 二级商品列表
 */
public class SecondaryListActivity extends BaseActivity implements ISecondaryListView {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;
    @BindView(R.id.etSearch)
    EditText etSearch;

    private RecySecondaryListAdapter goodsTypeAdapter;
    private SecondaryListPresenter secondaryListPresenter;
    /**
     * 类别
     */
    private String type = "";
    /**
     * 搜索关键字
     */
    private String scount_name = "";
    private int currPage = 1;
    private int pageSize = 10;

    public static void start(Context context, String type) {
        Intent starter = new Intent(context, SecondaryListActivity.class);
        starter.putExtra("type", type);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_secondary_list);
        setTitleLayoutVisiable(false);
        initView();
        initData();
    }

    private void initData() {
        secondaryListPresenter.querydayGoodsByRand(type, scount_name, getUserId(), currPage,
                pageSize);
    }

    private void initView() {
        if (getIntent().getStringExtra("type") != null) {
            type = getIntent().getStringExtra("type");
        }
        if (getIntent().getStringExtra("scount_name") != null) {
            scount_name = getIntent().getStringExtra("scount_name");
        }
        secondaryListPresenter = new SecondaryListPresenter(mContext, this);
        smartrefreshlayout = findViewById(R.id.smartrefreshlayout);
        smartrefreshlayout.setRefreshHeader(new ClassicsHeader(mContext));
        smartrefreshlayout.setRefreshFooter(new ClassicsFooter(mContext));
        smartrefreshlayout.setEnableLoadmore(false);
        GridLayoutManager mgr = new GridLayoutManager(this, 3);
        recyclerview.setLayoutManager(mgr);
        recyclerview.addItemDecoration(new GridSpacingItemDecoration(3, UIUtils.px2sp(this, 60),
                false));
        goodsTypeAdapter = new RecySecondaryListAdapter(R.layout.recy_item_wanju, null);
        recyclerview.setAdapter(goodsTypeAdapter);

        goodsTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });

        smartrefreshlayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                //上拉加载
                currPage++;
                initData();
            }
        }).setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //下拉刷新
                scount_name = etSearch.getText().toString().trim();
                currPage = 1;
                initData();
            }
        });
    }

    @Override
    public void querydayGoodsByRand(SecondGoodsListBean secondGoodsListBean) {
        smartrefreshlayout.finishRefresh();
        smartrefreshlayout.finishLoadmore();
        if (currPage < secondGoodsListBean.getPages()) {
            smartrefreshlayout.setEnableLoadmore(true);
        } else {
            smartrefreshlayout.setEnableLoadmore(false);
        }

        if (currPage == 1) {
            if (secondGoodsListBean.getList().size() < 1) {
                showToast("没有数据");
            } else {
                goodsTypeAdapter.setNewData(secondGoodsListBean.getList());
            }
        } else {
            goodsTypeAdapter.addData(secondGoodsListBean.getList());
        }
    }

    private void search() {
        scount_name = etSearch.getText().toString();
        if (TextUtils.isEmpty(scount_name)) {
            showToast("请输入搜索关键字");
            return;
        }
        currPage = 1;
        initData();
    }

    @Override
    public void onFinish() {
        smartrefreshlayout.finishRefresh();
        smartrefreshlayout.finishLoadmore();
    }

    @OnClick({R.id.tvSearch, R.id.flBack})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvSearch:
                search();
                break;
            case R.id.flBack:
                finish();
                break;
            default:
                break;
        }
    }


}
