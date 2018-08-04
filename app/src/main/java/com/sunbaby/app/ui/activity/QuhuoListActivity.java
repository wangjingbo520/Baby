package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunbaby.app.R;
import com.sunbaby.app.adapter.QHAdapter;
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

    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;
    private SQuoPresenter sQuoPresenter;
    private int currPage = 1;
    private int pageSize = 10;
    private List<SongQuhuoBean.ListBean> songQuhuoBeans;
    private QHAdapter songhuoAdapter;

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
        smartrefreshlayout.setRefreshHeader(new ClassicsHeader(mContext));
        smartrefreshlayout.setRefreshFooter(new ClassicsFooter(mContext));
        smartrefreshlayout.setEnableLoadmore(false);
        songQuhuoBeans = new ArrayList<>();
        songhuoAdapter = new QHAdapter(mContext, songQuhuoBeans);
        listview.setAdapter(songhuoAdapter);

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

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (songQuhuoBeans != null && songQuhuoBeans.size() > 0) {
                    SongQuhuoBean.ListBean listBean = songQuhuoBeans.get(position);
                    QuhuoDetailActivity.start(mContext, listBean.getId() + "", listBean.getTime() +
                                    "", listBean.getDelivery_status() + "", listBean.getGoods_id
                                    () + "",
                            listBean.getDispatchingId() + "");
                }
            }
        });
    }

    private void initData() {
        sQuoPresenter.retrievingList("1", "12", currPage, pageSize);
    }

    @Override
    public void retrievingList(SongQuhuoBean songQuhuoBean) {
        smartrefreshlayout.finishRefresh();
        smartrefreshlayout.finishLoadmore();
        if (currPage < songQuhuoBean.getPages()) {
            smartrefreshlayout.setEnableLoadmore(true);
        } else {
            smartrefreshlayout.setEnableLoadmore(false);
        }
        if (currPage == 1) {
            songQuhuoBeans.clear();
            if (songQuhuoBean.getList().size() < 1) {
                showToast("没有数据");
            }
        }
        for (int i = 0; i < songQuhuoBean.getList().size(); i++) {
            songQuhuoBeans.add(songQuhuoBean.getList().get(i));
        }
        songhuoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFinish() {
        smartrefreshlayout.finishRefresh();
        smartrefreshlayout.finishLoadmore();
    }
}
