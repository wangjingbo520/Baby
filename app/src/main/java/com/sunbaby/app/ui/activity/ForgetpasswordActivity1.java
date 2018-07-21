package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.libray.basetools.utils.StringUtils;
import com.sunbaby.app.R;
import com.sunbaby.app.callback.IForgetView;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.utils.ToastUtil;
import com.sunbaby.app.presenter.ForgetpasswordPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 忘记密码第一步
 */
public class ForgetpasswordActivity1 extends BaseActivity implements IForgetView {
    @BindView(R.id.etAccount)
    EditText etAccount;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.tvGetCode)
    TextView tvGetCode;
    private ForgetpasswordPresenter forgetpasswordPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_forgetpassword1);
        setTitle("找回密码");
        forgetpasswordPresenter = new ForgetpasswordPresenter(mContext, this);
    }

    @OnClick({R.id.tvGetCode, R.id.next})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.next:
                //验证输入的短信是否正确
                enSureCode();
                break;
            case R.id.tvGetCode:
                getCode();
                break;
            default:
                break;
        }
    }

    private void enSureCode() {
        String code = etCode.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            showToast("请先输入验证码");
            return;
        }
        String mobile = etAccount.getText().toString().trim();
        forgetpasswordPresenter.updateMobilesVerify(mobile, code, "UPDATE_MOBILE_SCENE");
    }

    private void getCode() {
        String mobile = etAccount.getText().toString();
        if (StringUtils.isEmpty(mobile)) {
            showToast("请先输入手机号");
            return;
        }
        forgetpasswordPresenter.sendSms(mobile, "UPDATE_MOBILE_SCENE");
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

    @Override
    public void onGetCodeSucceed() {
        timer.start();
        ToastUtil.showMessage("短信验证码已发送");
    }

    @Override
    public void forgetPassword() {
    }

    @Override
    public void updateMobilesVerify() {
        //短信验证成功,跳转到下一步
        String code = etCode.getText().toString().trim();
        String mobile = etAccount.getText().toString().trim();
        ForgetpasswordActivity2.start(mContext, code, mobile);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
