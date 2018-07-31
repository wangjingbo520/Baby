package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.sunbaby.app.AppData;
import com.sunbaby.app.MyApplication;
import com.sunbaby.app.R;
import com.sunbaby.app.callback.IExitLoginView;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.utils.DialogWithYesOrNoUtils;
import com.sunbaby.app.common.utils.Preferences;
import com.sunbaby.app.presenter.DistributionPresenter;
import com.sunbaby.app.ui.activity.LoginActivity;

import butterknife.OnClick;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 配送员主界面
 */
public class DistributionActivity extends BaseActivity implements IExitLoginView {
    private DistributionPresenter distributionPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_distribution);
        setBackLayoutVisiable(false);
        setTitle("配送员中心");
        distributionPresenter = new DistributionPresenter(mContext, this);
    }

    @OnClick({R.id.llQuhuo, R.id.llJinhuo, R.id.tvExitLogin})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvExitLogin:
                //退出登录
                exitLogin();
                break;
            case R.id.llQuhuo:
                //取货列表
                break;
            case R.id.llJinhuo:
                //进货列表
                break;
            default:
                break;
        }
    }

    private void exitLogin() {
        DialogWithYesOrNoUtils.showDialog(mContext, "您将退出登录吗?", new DialogWithYesOrNoUtils
                .DialogCallBack() {
            @Override
            public void exectEvent() {
                distributionPresenter.logout();
            }
        });
    }

    @Override
    public void logout() {
        Preferences.removeAll();
        AppData.getInstance().logoutClearData();
        MyApplication.getInstance().extiLoginApp();
        startTo(LoginActivity.class, true);
    }

}
