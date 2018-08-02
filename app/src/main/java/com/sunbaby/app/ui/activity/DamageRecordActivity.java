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
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunbaby.app.R;
import com.sunbaby.app.adapter.SunhuaiAdapter;
import com.sunbaby.app.bean.DamageDetailBean;
import com.sunbaby.app.bean.DamageRecordBean;
import com.sunbaby.app.callback.IDamageRecordView;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.widget.MyRecycleViewDivider;
import com.sunbaby.app.presenter.DamageRecordPresenter;
import com.sunbaby.app.ui.activity.orderdetail.OneDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 损坏记录
 */
public class DamageRecordActivity extends BaseActivity implements IDamageRecordView {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;

    private SunhuaiAdapter sunhuaiAdapter;
    private DamageRecordPresenter damageRecordPresenter;

    private int currPage = 1;
    private int pageSize = 10;

    public static void start(Context context) {
        Intent starter = new Intent(context, DamageRecordActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_damage_record);
        setTitle("损坏记录");
        bindView();
        initData();
    }

    private void bindView() {
        damageRecordPresenter = new DamageRecordPresenter(mContext, this);
        smartrefreshlayout.setRefreshHeader(new ClassicsHeader(mContext));
        smartrefreshlayout.setRefreshFooter(new ClassicsFooter(mContext));
        smartrefreshlayout.setEnableLoadmore(false);
        sunhuaiAdapter = new SunhuaiAdapter(R.layout.recy_item_sunhuai, null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new MyRecycleViewDivider(mContext, LinearLayoutManager
                .HORIZONTAL, 15,
                ContextCompat.getColor(mContext, R.color.background)));
        mRecyclerView.setAdapter(sunhuaiAdapter);

        smartrefreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                currPage = 1;
                initData();
            }
        }).setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                currPage++;
                initData();
            }
        });

        sunhuaiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<DamageRecordBean.ListBean> data = sunhuaiAdapter.getData();
                DamageDetailActivity.start(mContext,
                        data.get(position).getGoodsDamageId() + "");
            }
        });
    }

    private void initData() {
        damageRecordPresenter.damageList(getUserId(), currPage, pageSize);
    }

    @Override
    public void damageList(DamageRecordBean damageRecordBean) {
        smartrefreshlayout.finishRefresh();
        smartrefreshlayout.finishLoadmore();
        if (currPage < damageRecordBean.getPages()) {
            smartrefreshlayout.setEnableLoadmore(true);
        } else {
            smartrefreshlayout.setEnableLoadmore(false);
        }
        if (1 == currPage) {
            if (damageRecordBean.getList().size() < 1) {
                showToast("没有数据");
            //    sunhuaiAdapter.setEmptyView(R.layout.view_empty);
            } else {
                sunhuaiAdapter.setNewData(damageRecordBean.getList());
            }
        } else {
            sunhuaiAdapter.addData(damageRecordBean.getList());
        }
    }

    @Override
    public void damageDetails(DamageDetailBean damageDetailBean) {

    }

    @Override
    public void onFinish() {
        smartrefreshlayout.finishRefresh();
        smartrefreshlayout.finishLoadmore();
    }

}
