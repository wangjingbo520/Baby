package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunbaby.app.R;
import com.sunbaby.app.adapter.SonghuoAdapter;
import com.sunbaby.app.bean.SongQuhuoBean;
import com.sunbaby.app.callback.ISQuoView;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.presenter.SQuoPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 取货订单类表
 */
public class QuhuoListActivity extends BaseActivity implements ISQuoView {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;
    private SQuoPresenter sQuoPresenter;
    private int currPage = 1;
    private int pageSize = 10;
    private List<SongQuhuoBean> songQuhuoBeans;
    private SonghuoAdapter songhuoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_quhuo_list);
        setTitle("取货订单列表");
        sQuoPresenter = new SQuoPresenter(mContext, this);
        bindView();
        initData();
    }

    private void bindView() {
        songQuhuoBeans = new ArrayList<>();
        songhuoAdapter = new SonghuoAdapter(mContext, songQuhuoBeans);
        listView.setAdapter(songhuoAdapter);

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }


    private void initData() {
        sQuoPresenter.retrievingList("1", getUserId(), currPage, pageSize);
    }

    @Override
    public void retrievingList(SongQuhuoBean songQuhuoBean) {


    }

    @Override
    public void onFinish() {
        smartrefreshlayout.finishRefresh();
        smartrefreshlayout.finishLoadmore();
    }
}
