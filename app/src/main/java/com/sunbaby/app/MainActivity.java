package com.sunbaby.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.utils.ToastUtil;
import com.sunbaby.app.event.EventMessage;
import com.sunbaby.app.ui.fragment.CenterFragment;
import com.sunbaby.app.ui.fragment.HomeFragment;
import com.sunbaby.app.ui.fragment.PeisongFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wangjingbo
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
    @BindView(R.id.Mytitle)
    TextView tvTitle;
    @BindView(R.id.tab_peisong)
    LinearLayout tab_peisong;

    private HomeFragment homeFragment;
    private PeisongFragment peisongFragment;
    private CenterFragment centerFragment;

    public static String MAININDEX = "MAININDEX";
    private String[] title = {"首页", "配送箱", "中心"};
    private long mExitTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_main);
        setTitleLayoutVisiable(false);
        if (!TextUtils.isEmpty(getIntent().getStringExtra(MAININDEX))) {
            int index = Integer.parseInt(getIntent().getStringExtra(MAININDEX));
            initFragment(index);
            tvTitle.setText(title[index]);
        } else {
            initFragment(0);
            tvTitle.setText(title[0]);
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
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tab_home:
                restartBotton();
                tvTitle.setText("首页");
                tabHomeImageview.setImageResource(R.mipmap.homey);
                tabHomeTextview.setTextColor(ContextCompat.getColor(this, R.color.themeColor));
                initFragment(0);
                break;
            case R.id.tab_peisong:
                if (userIsLogin(false)) {
                    restartBotton();
                    tvTitle.setText("配送箱");
                    tabRongtongImageview.setImageResource(R.mipmap.peiy);
                    tabPeisongTextview.setTextColor(ContextCompat.getColor(this, R.color
                            .themeColor));
                    initFragment(1);
                }
                break;
            case R.id.tab_center:
                if (userIsLogin(false)) {
                    restartBotton();
                    tvTitle.setText("中心");
                    tabUserImageview.setImageResource(R.mipmap.gey);
                    tabCenterTextview.setTextColor(ContextCompat.getColor(this, R.color
                            .themeColor));
                    initFragment(2);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventMessage eventMessage) {
        if (EventbusConstant.MAIN_ACTIVITY == eventMessage.getClassInfo()) {
            restartBotton();
            tvTitle.setText("配送箱");
            tabRongtongImageview.setImageResource(R.mipmap.peiy);
            tabPeisongTextview.setTextColor(ContextCompat.getColor(this, R.color.themeColor));
            if (peisongFragment != null) {
                EventBus.getDefault().post(new EventMessage(EventbusConstant.PEISONG_FRAGMENT));
            }
            initFragment(1);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtil.showMessage("再按一次退出应用");
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
