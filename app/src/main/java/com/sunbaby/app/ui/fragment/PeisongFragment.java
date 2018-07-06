package com.sunbaby.app.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseFragment;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe  配送
 */
public class PeisongFragment extends BaseFragment {

    public static PeisongFragment newInstance() {
        PeisongFragment fragment = new PeisongFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_peisong;
    }

    @Override
    public void initView(View view) {

    }

}
