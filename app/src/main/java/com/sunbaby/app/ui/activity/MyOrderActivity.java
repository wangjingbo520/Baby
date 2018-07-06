package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.sunbaby.app.R;
import com.sunbaby.app.adapter.MainTabAdapter;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.ui.fragment.order.AllFragment;
import com.sunbaby.app.ui.fragment.order.FourFragment;
import com.sunbaby.app.ui.fragment.order.OneFragment;
import com.sunbaby.app.ui.fragment.order.ThreeFragment;
import com.sunbaby.app.ui.fragment.order.TwoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 我的订单
 */
public class MyOrderActivity extends BaseActivity {

    @BindView(R.id.tab_title)
    TabLayout mTablayout;
    @BindView(R.id.view_pager)
    ViewPager mViewpager;


    private List<Fragment> mFirstFraments;
    private String[] mList_title;
    private MainTabAdapter mAdapter_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_my_order);
        setTitle("我的订单");
        initFragment();
    }

    private void initFragment() {
        mList_title = getResources().getStringArray(R.array.tab_myOrder);
        mFirstFraments = new ArrayList<>();
        mFirstFraments.add(AllFragment.newInstance());
        mFirstFraments.add(OneFragment.newInstance());
        mFirstFraments.add(TwoFragment.newInstance());
        mFirstFraments.add(ThreeFragment.newInstance());
        mFirstFraments.add(FourFragment.newInstance());
        mAdapter_title = new MainTabAdapter(getSupportFragmentManager(), mFirstFraments,
                mList_title);
        mViewpager.setAdapter(mAdapter_title);
        mTablayout.setupWithViewPager(mViewpager);
    }
}
