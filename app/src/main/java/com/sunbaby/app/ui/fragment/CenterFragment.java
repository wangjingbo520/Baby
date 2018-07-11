package com.sunbaby.app.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseFragment;
import com.sunbaby.app.ui.activity.DamageRecordActivity;
import com.sunbaby.app.ui.activity.JoinmemberActivity2;
import com.sunbaby.app.ui.activity.MyOrderActivity;
import com.sunbaby.app.ui.activity.SettingActivity;

import butterknife.OnClick;

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

    @OnClick({R.id.llSetting, R.id.llLookmore, R.id.llSunhuai})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSetting:
                //设置
                startActivity(new Intent(mContext, SettingActivity.class));
                //   startActivity(new Intent(mContext, JoinmemberActivity2.class));
                break;
            case R.id.llLookmore:
                //查看更多
                MyOrderActivity.start(mContext, 0);
                break;
            case R.id.llSunhuai:
                DamageRecordActivity.start(mContext);
                break;
            default:
                break;
        }
    }
}
