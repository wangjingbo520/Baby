package com.sunbaby.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.ui.fragment.CenterFragment;
import com.sunbaby.app.ui.fragment.HomeFragment;
import com.sunbaby.app.ui.fragment.PeisongFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 主界面
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.tab_home_imageview)
    ImageView tabHomeImageview;
    @BindView(R.id.tab_home_textview)
    TextView tabHomeTextview;
    @BindView(R.id.tab_rongtong_imageview)
    ImageView tabRongtongImageview;
    @BindView(R.id.tab_peisong_textview)
    TextView tabPeisongTextview;
    @BindView(R.id.tab_user_imageview)
    ImageView tabUserImageview;
    @BindView(R.id.tab_center_textview)
    TextView tabCenterTextview;

    private HomeFragment homeFragment;
    private PeisongFragment peisongFragment;
    private CenterFragment centerFragment;
    public static String MAININDEX = "MAININDEX";
    private String[] title = {"首页", "配送箱", "中心"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_main);
        setBackLayoutVisiable(false);
        if (!TextUtils.isEmpty(getIntent().getStringExtra(MAININDEX))) {
            int index = Integer.parseInt(getIntent().getStringExtra(MAININDEX));
            initFragment(index);
            setTitle(title[index]);
        } else {
            initFragment(0);
            setTitle(title[0]);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (!TextUtils.isEmpty(intent.getStringExtra(MAININDEX))) {
            int index = Integer.parseInt(intent.getStringExtra(MAININDEX));
            initFragment(index);
            setTitle(title[index]);
        }
    }

    private void initFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (index) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = HomeFragment.newInstance();
                    transaction.add(R.id.main_frame, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                if (peisongFragment == null) {
                    peisongFragment = PeisongFragment.newInstance();
                    transaction.add(R.id.main_frame, peisongFragment);
                } else {
                    transaction.show(peisongFragment);
                }
                break;
            case 2:
                if (centerFragment == null) {
                    centerFragment = CenterFragment.newInstance();
                    transaction.add(R.id.main_frame, centerFragment);
                } else {
                    transaction.show(centerFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (peisongFragment != null) {
            transaction.hide(peisongFragment);
        }
        if (centerFragment != null) {
            transaction.hide(centerFragment);
        }
    }

    @OnClick({R.id.tab_home, R.id.tab_peisong, R.id.tab_center})
    @Override
    public void onClick(View view) {
        restartBotton();
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tab_home:
                setTitle("首页");
                tabHomeImageview.setImageResource(R.mipmap.homey);
                tabHomeTextview.setTextColor(ContextCompat.getColor(this, R.color.themeColor));
                initFragment(0);
                break;
            case R.id.tab_peisong:
                setTitle("配送箱");
                tabRongtongImageview.setImageResource(R.mipmap.peiy);
                tabPeisongTextview.setTextColor(ContextCompat.getColor(this, R.color.themeColor));
                initFragment(1);
                break;
            case R.id.tab_center:
                setTitle("中心");
                tabUserImageview.setImageResource(R.mipmap.gey);
                tabCenterTextview.setTextColor(ContextCompat.getColor(this, R.color.themeColor));
                initFragment(2);
                break;
            default:
                break;
        }
    }

    protected void restartBotton() {
        tabHomeTextview.setTextColor(ContextCompat.getColor(this, R.color.textColor6));
        tabCenterTextview.setTextColor(ContextCompat.getColor(this, R.color.textColor6));
        tabPeisongTextview.setTextColor(ContextCompat.getColor(this, R.color.textColor6));
        tabHomeImageview.setImageResource(R.mipmap.homen);
        tabRongtongImageview.setImageResource(R.mipmap.pein);
        tabUserImageview.setImageResource(R.mipmap.gen);
    }
}
