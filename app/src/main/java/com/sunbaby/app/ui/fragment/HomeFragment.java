package com.sunbaby.app.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.sunbaby.app.R;
import com.sunbaby.app.bean.HomeBean;
import com.sunbaby.app.callback.IHomeView;
import com.sunbaby.app.common.base.BaseFragment;
import com.sunbaby.app.common.utils.GlideImageLoader;
import com.sunbaby.app.common.utils.statusbartils.BannerImageLoader;
import com.sunbaby.app.common.widget.HomeFragmentDialog;
import com.sunbaby.app.presenter.HomePresenter;
import com.sunbaby.app.test.TestActivity;
import com.sunbaby.app.ui.activity.AllBookActivity;
import com.sunbaby.app.ui.activity.ClassificationActivity;
import com.sunbaby.app.ui.activity.LoginActivity;
import com.sunbaby.app.ui.activity.RegisterActivity;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe  首页
 */
public class HomeFragment extends BaseFragment implements HomeFragmentDialog.DialogCallk,
        IHomeView {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.iv4)
    ImageView iv4;
    @BindView(R.id.iv5)
    ImageView iv5;
    @BindView(R.id.iv6)
    ImageView iv6;

    private HomeFragmentDialog homeFragmentDialog;
    private SmartRefreshLayout smartRefreshLayout;
    private HomePresenter homePresenter;
    private List<String> bannerUrl;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData() {
        homePresenter.queryContentAdvertisementsByHome();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        bannerUrl = new ArrayList<>();
        homePresenter = new HomePresenter(mContext, this);
        smartRefreshLayout = view.findViewById(R.id.smartrefreshlayout);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(mContext));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(mContext));
        smartRefreshLayout.setEnableLoadmore(false);
        homeFragmentDialog = new HomeFragmentDialog(mContext, this, "", "");
    }

    @OnClick({R.id.tvLogin, R.id.tvRegister, R.id.llSuiji, R.id.llFenlei, R.id.llAlltushu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvLogin:
                //登录
                startActivity(new Intent(mContext, LoginActivity.class));
                mContext.finish();
                break;
            case R.id.tvRegister:
                //注册
                startActivity(new Intent(mContext, RegisterActivity.class));
                mContext.finish();
                break;
            case R.id.llSuiji:
                //随机
                homeFragmentDialog.show();
                break;
            case R.id.llFenlei:
                //图书分类查看
                ClassificationActivity.start(mContext);
                break;
            case R.id.llAlltushu:
                //全部图书
                AllBookActivity.start(mContext);
                break;
            default:
                break;
        }
    }

    @Override
    public void position(int position) {
        if (0 == position) {
            //继续随机
        } else {
            //加入配送箱
        }
    }

    @Override
    public void queryContentAdvertisementsByHome(HomeBean homeBean) {
        for (int i = 0; i < homeBean.getBanner().size(); i++) {
            bannerUrl.add(homeBean.getBanner().get(i).getImage_filename());
        }
        banner.setImages(bannerUrl).setDelayTime(3000).setImageLoader(new
                BannerImageLoader()).start();
        GlideImageLoader.loadImage(mContext, homeBean.getFunctional_diagram().get(0).getUrl(), iv1);
        GlideImageLoader.loadImage(mContext, homeBean.getFunctional_diagram().get(1).getUrl(), iv3);
        GlideImageLoader.loadImage(mContext, homeBean.getFunctional_diagram().get(2).getUrl(), iv3);
        GlideImageLoader.loadImage(mContext, homeBean.getFunctional_diagram().get(3).getUrl(), iv4);
        GlideImageLoader.loadImage(mContext, homeBean.getFunctional_diagram().get(4).getUrl(), iv5);
        GlideImageLoader.loadImage(mContext, homeBean.getFunctional_diagram().get(5).getUrl(), iv6);
    }

}
