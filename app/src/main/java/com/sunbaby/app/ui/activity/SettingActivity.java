package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.sunbaby.app.AppData;
import com.sunbaby.app.EventbusConstant;
import com.sunbaby.app.MyApplication;
import com.sunbaby.app.R;
import com.sunbaby.app.callback.IExitLoginView;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.api.RequestClient;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.utils.DialogWithYesOrNoUtils;
import com.sunbaby.app.common.utils.NDialog;
import com.sunbaby.app.common.utils.Preferences;
import com.sunbaby.app.event.EventMessage;
import com.sunbaby.app.presenter.DistributionPresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 设置
 */
public class SettingActivity extends BaseActivity implements IExitLoginView {
    private DistributionPresenter distributionPresenter;
    private NDialog alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_setting);
        setTitle("设置");
        alertDialog = new NDialog(mContext);
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
        alertDialog.setTitleSize(20)
                .setTitle("提示")
                .setMessage("确认要退出吗?")
                .setTitleCenter(false)
                .setMessageCenter(false)
                .setMessageSize(18)
                .setMessageColor(ContextCompat.getColor(mContext, R.color.textColor3))
                .setNegativeTextColor(ContextCompat.getColor(mContext, R.color.textColor3))
                .setPositiveTextColor(ContextCompat.getColor(mContext, R.color.textColor3))
                .setButtonCenter(false)
                .setButtonSize(18)
                .setCancleable(true)
                .setOnConfirmListener(new NDialog.OnConfirmListener() {
                    @Override
                    public void onClick(int which) {
                        //which,0代表NegativeButton，1代表PositiveButton
                        if (1 == which) {
                            distributionPresenter.logout();
                        }
                    }
                });
        alertDialog.create(NDialog.CONFIRM).show();
    }

    @Override
    public void logout() {
        Preferences.removeAll();
        AppData.getInstance().logoutClearData();
        MyApplication.getInstance().extiLoginApp();
        startTo(LoginActivity.class, true);

    }
}
