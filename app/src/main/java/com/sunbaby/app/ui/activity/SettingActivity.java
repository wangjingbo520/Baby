package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.sunbaby.app.AppData;
import com.sunbaby.app.R;
import com.sunbaby.app.callback.IExitLoginView;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.utils.DialogWithYesOrNoUtils;
import com.sunbaby.app.presenter.DistributionPresenter;

import butterknife.OnClick;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 设置
 */
public class SettingActivity extends BaseActivity implements IExitLoginView {
    private DistributionPresenter distributionPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_setting);
        setTitle("设置");
        distributionPresenter = new DistributionPresenter(mContext, this);
    }

    @OnClick({R.id.llPerson, R.id.llAdress, R.id.exLogin})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.llPerson:
                //个人中心
                startTo(PersonActivity.class, false);
                break;
            case R.id.llAdress:
                //收货地址
                startTo(ManageAddressActivity.class, false);
                break;
            case R.id.exLogin:
                //退出登录
                exitLogin();
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
        AppData.getInstance().logoutClearData();
        startTo(LoginActivity.class, true);
    }
}
