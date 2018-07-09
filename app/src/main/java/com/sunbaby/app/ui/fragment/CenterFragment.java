package com.sunbaby.app.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseFragment;
import com.sunbaby.app.ui.activity.MyOrderActivity;
import com.sunbaby.app.ui.activity.SettingActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe  中心
 */
public class CenterFragment extends BaseFragment {

    public static CenterFragment newInstance() {
        CenterFragment fragment = new CenterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_center;
    }

    @Override
    public void initView(View view) {

    }

    @OnClick({R.id.tvSetting, R.id.llLookmore})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvSetting:
                //设置
                startActivity(new Intent(mContext, SettingActivity.class));
                break;
            case R.id.llLookmore:
                //查看更多
                MyOrderActivity.start(mContext, 0);
                break;
            default:
                break;
        }
    }
}
