package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunbaby.app.R;
import com.sunbaby.app.adapter.QuhuoAdapter;
import com.sunbaby.app.bean.SongQuhuoBean;
import com.sunbaby.app.callback.ISQuoView;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.presenter.SQuoPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 送货订单列表
 */

public class SonghuoListActivity extends BaseActivity implements ISQuoView {

    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private SQuoPresenter sQuoPresenter;
    private int currPage = 1;
    private int pageSize = 10;
    private QuhuoAdapter quhuoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_songhuo_list);
        setTitle("送货订单列表");
        sQuoPresenter = new SQuoPresenter(mContext, this);
        bindView();
        initData();
    }

    private void bindView() {
        smartrefreshlayout.setRefreshHeader(new ClassicsHeader(mContext));
        smartrefreshlayout.setRefreshFooter(new ClassicsFooter(mContext));
        smartrefreshlayout.setEnableLoadmore(false);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        quhuoAdapter = new QuhuoAdapter(R.layout.listview_quhuo, null);
        recyclerview.setAdapter(quhuoAdapter);
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

        quhuoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SongQuhuoBean.ListBean listBean = quhuoAdapter.getData().get(position);
                SonghuoDetailActivity.start(mContext, listBean.getId() + "", listBean.getTime() +
                                "", listBean.getDelivery_status() + "", listBean.getGoods_id
                                () + "",
                        listBean.getDispatchingId() + "");
            }
        });
    }

    private void initData() {
        sQuoPresenter.retrievingList("2", "12", currPage, pageSize);
    }

    @Override
    public void retrievingList(SongQuhuoBean songQuhuoBean) {
        //取货列表
        smartrefreshlayout.finishRefresh();
        smartrefreshlayout.finishLoadmore();
        if (currPage < songQuhuoBean.getPages()) {
            smartrefreshlayout.setEnableLoadmore(true);
        } else {
            smartrefreshlayout.setEnableLoadmore(false);
        }

        if (currPage == 1) {
            quhuoAdapter.setNewData(songQuhuoBean.getList());
        } else {
            quhuoAdapter.addData(songQuhuoBean.getList());
        }
    }

    @Override
    public void onFinish() {
        smartrefreshlayout.finishRefresh();
        smartrefreshlayout.finishLoadmore();
    }
}
