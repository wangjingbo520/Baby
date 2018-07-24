package com.sunbaby.app.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.libray.basetools.view.imageview.CircleImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.sunbaby.app.R;
import com.sunbaby.app.bean.HomeBean;
import com.sunbaby.app.bean.QueryGoodsByRandBean;
import com.sunbaby.app.callback.IHomeView;
import com.sunbaby.app.common.base.BaseFragment;
import com.sunbaby.app.common.utils.GlideImageLoader;
import com.sunbaby.app.common.utils.statusbartils.BannerImageLoader;
import com.sunbaby.app.common.widget.HomeFragmentDialog;
import com.sunbaby.app.event.EventMessage;
import com.sunbaby.app.presenter.HomePresenter;
import com.sunbaby.app.ui.activity.AllBookActivity;
import com.sunbaby.app.ui.activity.ClassificationActivity;
import com.sunbaby.app.ui.activity.LoginActivity;
import com.sunbaby.app.ui.activity.RegisterActivity;
import com.sunbaby.app.ui.activity.SecondaryListActivity;
import com.youth.banner.Banner;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe  首页
 */
public class HomeFragment extends BaseFragment implements HomeFragmentDialog.DialogCallk,
        IHomeView {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.iv1)
    CircleImageView iv1;
    @BindView(R.id.iv2)
    CircleImageView iv2;
    @BindView(R.id.iv3)
    CircleImageView iv3;
    @BindView(R.id.iv4)
    CircleImageView iv4;
    @BindView(R.id.iv5)
    CircleImageView iv5;
    @BindView(R.id.iv6)
    CircleImageView iv6;

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
    }

    @OnClick({R.id.tvLogin, R.id.tvRegister, R.id.llSuiji, R.id.llFenlei, R.id.llAlltushu, R.id
            .llFenleiWanju, R.id.llAllWanju, R.id.llSuiji2})
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
                //图书随机
                homePresenter.queryGoodsByRand("1");
                break;
            case R.id.llFenlei:
                //图书分类查看
                ClassificationActivity.start(mContext, "1");
                break;
            case R.id.llFenleiWanju:
                //玩具分类查看
                ClassificationActivity.start(mContext, "2");
                break;
            case R.id.llAlltushu:
                //全部图书
                SecondaryListActivity.start(mContext, "");
                break;
            case R.id.llAllWanju:
                //全部玩具
                SecondaryListActivity.start(mContext, "");
                break;
            case R.id.llSuiji2:
                //玩具随机
                //1 图书 2 玩具 0 全部
                homePresenter.queryGoodsByRand("2");
                break;
            default:
                break;
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

    @Override
    public void queryGoodsByRand(QueryGoodsByRandBean queryGoodsByRandBean) {
        //首页随机商品
        if (homeFragmentDialog == null) {
            homeFragmentDialog = new HomeFragmentDialog(mContext, this, "", "");
        }
        homeFragmentDialog.setData(queryGoodsByRandBean);
        homeFragmentDialog.show();
    }

    @Override
    public void joinDistributionBox(Object object) {
        //加入配送箱成功
        EventBus.getDefault().post(new EventMessage(1));
    }

    @Override
    public void position(int position, QueryGoodsByRandBean queryGoodsByRandBean) {
        if (0 == position) {
            //继续随机
        } else {
            //加入配送箱
            homePresenter.joinDistributionBox(queryGoodsByRandBean.getId() + "");

        }
    }
}
