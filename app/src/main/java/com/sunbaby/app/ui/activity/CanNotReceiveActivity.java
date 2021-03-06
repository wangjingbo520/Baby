package com.sunbaby.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sunbaby.app.AppData;
import com.sunbaby.app.MyApplication;
import com.sunbaby.app.R;
import com.sunbaby.app.bean.CanReceiveBean;
import com.sunbaby.app.callback.ICanReceiveView;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.utils.Preferences;
import com.sunbaby.app.common.utils.ToastUtil;
import com.sunbaby.app.presenter.CanOrNotReceivePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 原手机号不能接收验证码
 */
public class CanNotReceiveActivity extends BaseActivity implements ICanReceiveView {

    @BindView(R.id.etPhoneNumber)
    EditText etPhoneNumber;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.tvGetCode)
    TextView tvGetCode;
    @BindView(R.id.etPassword)
    EditText etPassword;
    private CanOrNotReceivePresenter canOrNotReceivePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_can_not_receive);
        setTitle("修改手机号");
        canOrNotReceivePresenter = new CanOrNotReceivePresenter(mContext, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, CanNotReceiveActivity.class);
        context.startActivity(starter);
    }

    @OnClick({R.id.tvGetCode, R.id.btnSave})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvGetCode:
                //获取验证码
                getCode();
                break;
            case R.id.btnSave:
                //保存
                save();
                break;
            default:
                break;
        }
    }

    private void save() {
        String mobile = etPhoneNumber.getText().toString().trim();
        String code = etCode.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            showToast("手机号码不能为空");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            showToast("验证码不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showToast("密码不能为空");
            return;
        }
        canOrNotReceivePresenter.updateMobileSave(mobile, "UPDATE_MOBILE_SCENE", code, password);
    }

    private void getCode() {
        String mobile = etPhoneNumber.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            showToast("请先输入手机号码");
            return;
        }
        canOrNotReceivePresenter.sendSmsUpdataPhoneNumber(mobile, "UPDATE_MOBILE_SCENE");
    }

    @Override
    public void updateMobileInit(CanReceiveBean canReceiveBean) {

    }

    @Override
    public void sendSmsUpdataPhoneNumber(Object o) {
        timer.start();
        ToastUtil.showMessage("短信已发送,请查收");

    }

    @Override
    public void updateMobilesVerify(Object o) {

    }

    @Override
    public void updateMobileSave(Object o) {
        showToast("新手机号码设置成功,请重新登录");
        Preferences.removeAll();
        AppData.getInstance().logoutClearData();
        MyApplication.getInstance().extiLoginApp();
        startTo(LoginActivity.class, true);
    }

    CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            int time = (int) (millisUntilFinished / 1000);
            tvGetCode.setText(time + "秒后重发");
            tvGetCode.setEnabled(false);
        }

        @Override
        public void onFinish() {
            tvGetCode.setText("获取验证码");
            tvGetCode.setEnabled(true);
        }
    };


}
