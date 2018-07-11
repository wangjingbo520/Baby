package com.sunbaby.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.ui.fragment.CenterFragment;
import com.sunbaby.app.ui.fragment.HomeFragment;
import com.sunbaby.app.ui.fragment.PeisongFragment;

import butterknife.OnClick;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 主界面
 */
public class MainActivity extends BaseActivity {
    private HomeFragment homeFragment;
    private PeisongFragment peisongFragment;
    private CenterFragment centerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_main);
        setBackLayoutVisiable(false);
        initFragment(0);
        setTitle("首页");
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
                initFragment(0);
                break;
            case R.id.tab_peisong:
                setTitle("配送箱");
                initFragment(1);
                break;
            case R.id.tab_center:
                setTitle("中心");
                initFragment(2);
                break;
            default:
                break;
        }
    }


    protected void restartBotton() {
//        tabHome.setBackgroundColor(getResources().getColor(R.color.bottom));
//        tabRongtong.setBackgroundColor(getResources().getColor(R.color.bottom));
//        tabUser.setBackgroundColor(getResources().getColor(R.color.bottom));
//        tabMore.setBackgroundColor(getResources().getColor(R.color.bottom));
    }


}
