package com.sunbaby.app.ui.fragment.order;


import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.sunbaby.app.R;
import com.sunbaby.app.adapter.RecyGuihuangAdapter;
import com.sunbaby.app.bean.GuihuangBean;
import com.sunbaby.app.common.base.BaseFragment;
import com.sunbaby.app.common.utils.ToastUtil;
import com.sunbaby.app.common.widget.MyRecycleViewDivider;
import com.sunbaby.app.ui.activity.orderdetail.ThreeDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe  待归还
 */
public class ThreeFragment extends BaseFragment implements RecyGuihuangAdapter
        .OnCheckBoxClickListener, RecyGuihuangAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private SmartRefreshLayout smartrefreshlayout;
    private RecyGuihuangAdapter recyGuihuangAdapter;
    private List<GuihuangBean> guihuangBeans;

    public static ThreeFragment newInstance() {
        ThreeFragment fragment = new ThreeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData() {
        guihuangBeans = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            guihuangBeans.add(new GuihuangBean());
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new MyRecycleViewDivider(mContext, LinearLayoutManager
                .HORIZONTAL, 15,
                ContextCompat.getColor(mContext, R.color.background)));
        recyGuihuangAdapter = new RecyGuihuangAdapter(mContext);

        recyGuihuangAdapter.notifyAdapter(guihuangBeans, false);
        recyGuihuangAdapter.setOnItemClickListener(this);
        recyGuihuangAdapter.setOnCheckBoxClickListener(this);
        mRecyclerView.setAdapter(recyGuihuangAdapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_three;
    }

    @Override
    public void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerview);
        smartrefreshlayout = view.findViewById(R.id.smartrefreshlayout);
        smartrefreshlayout.setRefreshHeader(new ClassicsHeader(mContext));
        smartrefreshlayout.setRefreshFooter(new ClassicsFooter(mContext));
        smartrefreshlayout.setEnableLoadmore(false);
    }

    @Override
    public void onCheckboxClickListener(int pos, List<GuihuangBean> myLiveList) {
        GuihuangBean guihuangBean = myLiveList.get(pos);
        boolean isSelect = guihuangBean.isSelect();
        if (!isSelect) {
            guihuangBean.setSelect(true);
        } else {
            guihuangBean.setSelect(false);
        }
        recyGuihuangAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.tvGuihuang)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvGuihuang:
                if (recyGuihuangAdapter != null) {
                    List<GuihuangBean> guihuangBeans = new ArrayList<>();
                    for (int i = recyGuihuangAdapter.getMyLiveList().size(), j = 0; i > j; i--) {
                        GuihuangBean guihuangBean = recyGuihuangAdapter.getMyLiveList().get(i - 1);
                        if (guihuangBean.isSelect()) {
                            guihuangBeans.add(guihuangBean);
                        }
                    }
                    ToastUtil.show("您选择了" + guihuangBeans.size() + "个条目");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClickListener(int pos, List<GuihuangBean> myLiveList) {
        ThreeDetailActivity.start(mContext);
    }
}
