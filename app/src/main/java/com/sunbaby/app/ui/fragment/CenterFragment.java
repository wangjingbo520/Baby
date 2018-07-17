package com.sunbaby.app.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.load.model.GlideUrl;
import com.libray.basetools.view.imageview.CircleImageView;
import com.sunbaby.app.R;
import com.sunbaby.app.bean.CenterBean;
import com.sunbaby.app.callback.ICenterView;
import com.sunbaby.app.common.base.BaseFragment;
import com.sunbaby.app.common.utils.GlideImageLoader;
import com.sunbaby.app.presenter.CenterPresenter;
import com.sunbaby.app.ui.activity.DamageRecordActivity;
import com.sunbaby.app.ui.activity.JoinmemberActivity2;
import com.sunbaby.app.ui.activity.MyOrderActivity;
import com.sunbaby.app.ui.activity.SettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe  中心
 */
public class CenterFragment extends BaseFragment implements ICenterView {
    @BindView(R.id.ivLogo)
    CircleImageView ivLogo;
    @BindView(R.id.tvRing1)
    TextView tvRing1;
    @BindView(R.id.tvRing2)
    TextView tvRing2;
    @BindView(R.id.tvRing3)
    TextView tvRing3;
    @BindView(R.id.tvRing4)
    TextView tvRing4;
    private CenterPresenter centerPresenter;

    public static CenterFragment newInstance() {
        CenterFragment fragment = new CenterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData() {
        //  centerPresenter.homePage(getUser().getUserId());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_center;
    }

    @Override
    public void initView(View view) {
        centerPresenter = new CenterPresenter(mContext, this);

    }

    @OnClick({R.id.llSetting, R.id.llLookmore, R.id.llSunhuai, R.id.ll1, R.id.ll2, R.id.ll3, R.id
            .ll4, R.id.llHuiyuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSetting:
                //设置
                startActivity(new Intent(mContext, SettingActivity.class));
                break;
            case R.id.llLookmore:
                //查看更多
                MyOrderActivity.start(mContext, 0);
                break;
            case R.id.llSunhuai:
                DamageRecordActivity.start(mContext);
                break;
            case R.id.ll1:
                //待发货
                MyOrderActivity.start(mContext, 1);
                break;
            case R.id.ll2:
                //待收货
                MyOrderActivity.start(mContext, 2);
                break;
            case R.id.ll3:
                //待归还
                MyOrderActivity.start(mContext, 3);
                break;
            case R.id.ll4:
                //已完成
                MyOrderActivity.start(mContext, 4);
                break;
            case R.id.llHuiyuan:
                //会员
                JoinmemberActivity2.start(mContext);
                break;
            default:
                break;
        }
    }

    @Override
    public void homePage(CenterBean centerBean) {
        if (centerBean.getOrderNumber().getStayDelivery() > 0) {
            //待发货
            tvRing1.setVisibility(View.VISIBLE);
            tvRing1.setText(centerBean.getOrderNumber().getStayDelivery() + "");
        }
        if (centerBean.getOrderNumber().getForGoode() > 0) {
            //待收货
            tvRing2.setVisibility(View.VISIBLE);
            tvRing2.setText(centerBean.getOrderNumber().getForGoode() + "");
        }
        if (centerBean.getOrderNumber().getStayRecycled() > 0) {
            //待归还
            tvRing3.setVisibility(View.VISIBLE);
            tvRing3.setText(centerBean.getOrderNumber().getStayDelivery() + "");
        }
        //头像
        GlideImageLoader.loadImage(mContext, centerBean.getPhoto(), ivLogo);
    }

}
