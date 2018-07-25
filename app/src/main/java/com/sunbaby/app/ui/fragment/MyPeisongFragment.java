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
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.api.RequestClient;
import com.sunbaby.app.common.base.BaseStateViewFragment;
import com.sunbaby.app.common.utils.DialogWithYesOrNoUtils;
import com.sunbaby.app.event.EventMessage;
import com.sunbaby.app.presenter.PeisongPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author Wangjingbo
 * @date 2018/7/25
 * describe
 */
public class MyPeisongFragment extends BaseStateViewFragment implements IPeisongView,
        View.OnClickListener {
    private SmartRefreshLayout smartrefreshlayout;
    private PeisongPresenter peisongPresenter;
    private RecyclerView recyclerview;
    private MyPeisongRecyAdapter myPeisongRecyAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

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

    public static MyPeisongFragment newInstance() {
        MyPeisongFragment fragment = new MyPeisongFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newpeisong, container, false);
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
        smartrefreshlayout = view.findViewById(R.id.smartrefreshlayout);
        recyclerview = view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        smartrefreshlayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        smartrefreshlayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        smartrefreshlayout.setEnableLoadmore(false);
        view.findViewById(R.id.tvSure).setOnClickListener(this);
        myPeisongRecyAdapter = new MyPeisongRecyAdapter(R.layout.item_list_peisong, null);
        recyclerview.setAdapter(myPeisongRecyAdapter);

        smartrefreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initData();
            }
        });

        myPeisongRecyAdapter.setOnItemChildClickListener(new BaseQuickAdapter
                .OnItemChildClickListener() {
            @Override
            public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int
                    position) {
                if (view.getId() == R.id.ivDelete) {
                    //删除配送单
                    DialogWithYesOrNoUtils.showDialog(getActivity(), "确认要删除吗?", new
                            DialogWithYesOrNoUtils.DialogCallBack() {
                                @Override
                                public void exectEvent() {
                                    peisongPresenter.deleteDispatching(position, "1",
                                            myPeisongRecyAdapter.getData().get(position)
                                                    .getGoods_name());
                                }
                            });
                }
            }
        });
    }

    @Override
    public void queryDispatching(PesisongBean pesisongBean) {
        //配送箱列表,容器
        smartrefreshlayout.finishRefresh();
        myPeisongRecyAdapter.setNewData(pesisongBean.getList());
    }

    @Override
    public void deleteDispatching(int position) {
        myPeisongRecyAdapter.notifyItemRemoved(position);
        myPeisongRecyAdapter.notifyDataSetChanged();
    }

    @Override
    public void affirmDispatching(Object object) {
        //配送箱确认回调

    }

    @Override
    public void onFinish() {
        if (smartrefreshlayout.isRefreshing()) {
            smartrefreshlayout.finishRefresh();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventMessage eventMessage) {
        if (2 == eventMessage.getPosition()) {
            initData();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}

