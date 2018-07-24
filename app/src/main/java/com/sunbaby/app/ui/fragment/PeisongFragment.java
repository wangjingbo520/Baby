package com.sunbaby.app.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunbaby.app.R;
import com.sunbaby.app.adapter.MyPeisongRecyAdapter;
import com.sunbaby.app.bean.PesisongBean;
import com.sunbaby.app.callback.IPeisongView;
import com.sunbaby.app.common.base.BaseStateViewFragment;
import com.sunbaby.app.presenter.PeisongPresenter;

import org.greenrobot.eventbus.EventBus;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe  配送
 */
public class PeisongFragment extends BaseStateViewFragment implements IPeisongView,
        View.OnClickListener {
    private SmartRefreshLayout smartrefreshlayout;
    private PeisongPresenter peisongPresenter;
    private RecyclerView recyclerView;
    private MyPeisongRecyAdapter myPeisongRecyAdapter;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSure:
                //配送箱确认
                peisongPresenter.affirmDispatching(getUserId(), "");
                break;
            default:
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //    EventBus.getDefault().register(this);
    }

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
        peisongPresenter = new PeisongPresenter(getActivity(), this);
        recyclerView = view.findViewById(R.id.recyclerview);
        smartrefreshlayout = view.findViewById(R.id.smartrefreshlayout);
        smartrefreshlayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        smartrefreshlayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        smartrefreshlayout.setEnableLoadmore(false);
        view.findViewById(R.id.tvSure).setOnClickListener(this);
        myPeisongRecyAdapter = new MyPeisongRecyAdapter(R.layout.item_list_peisong, null);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myPeisongRecyAdapter);

        myPeisongRecyAdapter.setOnItemChildClickListener(new BaseQuickAdapter
                .OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.ivDelete) {
                    //删除配送箱

                }

            }
        });

        smartrefreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initData();
            }
        });
    }

    @Override
    public void queryDispatching(PesisongBean pesisongBean) {
        //配送箱列表
        smartrefreshlayout.finishRefresh();
        myPeisongRecyAdapter.setNewData(pesisongBean.getList());
    }

    @Override
    public void deleteDispatching(int position) {

    }

    @Override
    public void affirmDispatching(Object object) {
        //配送箱确认回调
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
