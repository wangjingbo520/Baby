package com.sunbaby.app.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunbaby.app.R;
import com.sunbaby.app.adapter.NewPeisongAdapter;
import com.sunbaby.app.bean.PesisongBean;
import com.sunbaby.app.callback.IPeisongView;
import com.sunbaby.app.common.base.BaseStateViewFragment;
import com.sunbaby.app.common.utils.ToastUtil;
import com.sunbaby.app.presenter.PeisongPresenter;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe  配送
 */
public class PeisongFragment extends BaseStateViewFragment implements IPeisongView,
        NewPeisongAdapter.OnOnDeleteClickListener {
    private RecyclerView recyclerview;
    private SmartRefreshLayout smartrefreshlayout;
    private PeisongPresenter peisongPresenter;
    private NewPeisongAdapter peisongAdapter;

    public static PeisongFragment newInstance() {
        PeisongFragment fragment = new PeisongFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_peisong, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public void initData() {
        peisongPresenter.queryDispatching("1");
    }

    public void initView(View view) {
        recyclerview = view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        smartrefreshlayout = view.findViewById(R.id.smartrefreshlayout);
        smartrefreshlayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        smartrefreshlayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        smartrefreshlayout.setEnableLoadmore(false);
        peisongPresenter = new PeisongPresenter(getActivity(), this);
        smartrefreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
            }
        }).setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
            }
        });
    }

    @Override
    public void queryDispatching(PesisongBean pesisongBean) {
        //配送箱列表
        peisongAdapter = new NewPeisongAdapter(getActivity(), pesisongBean.getList());
        recyclerview.setAdapter(peisongAdapter);
        peisongAdapter.setOnDeleteListenerClickListener(this);
    }


    @Override
    public void deleteDispatching(int position) {
        //删除成功
        peisongAdapter.notifiItemDete(position);
        ToastUtil.showMessage("删除成功");
    }

    @Override
    public void onItemDeleteListener(int pos, PesisongBean.ListBean listBean) {
        peisongPresenter.deleteDispatching(pos, "1", listBean.getId() + "");
    }
}
