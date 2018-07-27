package com.sunbaby.app.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunbaby.app.R;
import com.sunbaby.app.WebViewActivity;
import com.sunbaby.app.adapter.PeisongListViewAdapter;
import com.sunbaby.app.bean.PesisongBean;
import com.sunbaby.app.bean.SureBean;
import com.sunbaby.app.callback.IPeisongView;
import com.sunbaby.app.common.base.BaseStateViewFragment;
import com.sunbaby.app.event.EventMessage;
import com.sunbaby.app.presenter.PeisongPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe  配送
 */
public class PeisongFragment extends BaseStateViewFragment implements IPeisongView,
        View.OnClickListener {
    private SmartRefreshLayout smartrefreshlayout;
    private PeisongPresenter peisongPresenter;
    private PeisongListViewAdapter peisongListViewAdapter;
    private ListView listView;
    private TextView tvWanju;
    private TextView tvShuji;
    private TextView tvTishi;
    private View empty;
    private LinearLayout llContent;


    /**
     * 收到主页面加入配送箱的消息
     *
     * @param eventMessage
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventMessage eventMessage) {
        if (2 == eventMessage.getPosition()) {
            initData();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSure:
                //配送箱确认
                if (peisongListViewAdapter.getDatas() != null) {
                    List<PesisongBean.ListBean> datas = peisongListViewAdapter.getDatas();
                    Object object = com.alibaba.fastjson.JSONArray.toJSON(datas);
                    peisongPresenter.affirmDispatching(getUserId(), object.toString());
                }
                break;
            default:
                break;
        }
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
        peisongPresenter.queryDispatching(getUserId());
    }

    public void initView(View view) {
        tvWanju = view.findViewById(R.id.tvWanju);
        tvShuji = view.findViewById(R.id.tvShuji);
        tvTishi = view.findViewById(R.id.tvTishi);
        listView = view.findViewById(R.id.listView);
        llContent = view.findViewById(R.id.llContent);
        empty = view.findViewById(R.id.empty);

        smartrefreshlayout = view.findViewById(R.id.smartrefreshlayout);
        smartrefreshlayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        smartrefreshlayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        smartrefreshlayout.setEnableLoadmore(false);
        view.findViewById(R.id.tvSure).setOnClickListener(this);

        peisongPresenter = new PeisongPresenter(getActivity(), this);
        peisongListViewAdapter = new PeisongListViewAdapter(getActivity());
        listView.setAdapter(peisongListViewAdapter);
        smartrefreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initData();
            }
        });
    }

    @Override
    public void queryDispatching(PesisongBean pesisongBean) {
        //配送箱列表,容器
        smartrefreshlayout.finishRefresh();
        if (pesisongBean.getList().size() <= 0) {
            llContent.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
            return;
        } else {
            int holding_toy_max = Integer.parseInt(pesisongBean.getHolding_toy_max());
            int holding_books_max = Integer.parseInt(pesisongBean.getHolding_books_max());
            peisongListViewAdapter.setData(pesisongBean.getList(), holding_books_max,
                    holding_toy_max);
            if (holding_books_max == pesisongBean.getGoods_books_num() || holding_toy_max ==
                    pesisongBean.getGoods_toy_num()) {
                tvTishi.setVisibility(View.VISIBLE);
            } else {
                tvTishi.setVisibility(View.GONE);
            }
            tvShuji.setText("您当前已持有书籍" + pesisongBean.getGoods_books_num() + "/" + pesisongBean
                    .getHolding_books_max());
            tvWanju.setText("您当前已持有玩具" + pesisongBean.getGoods_toy_num() + "/" + pesisongBean
                    .getHolding_toy_max());

            llContent.setVisibility(View.VISIBLE);
            empty.setVisibility(View.GONE);
        }
    }

    @Override
    public void deleteDispatching(int position) {
    }

    @Override
    public void affirmDispatching(SureBean sureBean) {
        //配送箱确认回调
        initData();
        String url = sureBean.getUrl();
        WebViewActivity.start(getActivity(), url);
    }

    @Override
    public void onFinish() {
        if (smartrefreshlayout.isRefreshing()) {
            smartrefreshlayout.finishRefresh();
        }
    }

}
