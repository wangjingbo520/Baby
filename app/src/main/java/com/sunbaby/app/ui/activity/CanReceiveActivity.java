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

import com.sunbaby.app.R;
import com.sunbaby.app.bean.CanReceiveBean;
import com.sunbaby.app.callback.ICanReceiveView;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.utils.ToastUtil;
import com.sunbaby.app.presenter.CanOrNotReceivePresenter;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 原手机号能接收验证码,第一步
 */
public class CanReceiveActivity extends BaseActivity implements ICanReceiveView {

    @BindView(R.id.etAccount)
    TextView etAccount;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.tvGetCode)
    TextView tvGetCode;
    private String mobile = "";
    private CanOrNotReceivePresenter canOrNotReceivePresenter;
    private String scene = "";

    public static void start(Context context) {
        Intent starter = new Intent(context, CanReceiveActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_can_receive);
        setTitle("修改手机号");
        canOrNotReceivePresenter = new CanOrNotReceivePresenter(mContext, this);
        canOrNotReceivePresenter.updateMobileInit();
    }

    @OnClick({R.id.tvGetCode, R.id.next})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvGetCode:
                //获取验证码
                getCode();
                break;
            case R.id.next:
                //下一步,手机验证码验证
                mobilesVerify();
                break;
            default:
                break;
        }
    }

    private void mobilesVerify() {
        String code = etCode.getText().toString();
        if (TextUtils.isEmpty(code)) {
            ToastUtil.showMessage("验证码不能为空");
        }
        canOrNotReceivePresenter.updateMobilesVerify(mobile, code, scene);
    }

    private void getCode() {
        if (TextUtils.isEmpty(mobile) || TextUtils.isEmpty(scene)) {
            showToast("获取用户信息初始化错误");
            return;
        }
        canOrNotReceivePresenter.sendSmsUpdataPhoneNumber(mobile, scene);
    }

    @Override
    public void updateMobileInit(CanReceiveBean canReceiveBean) {
        //信息初始化
        this.mobile = canReceiveBean.getMobile() + "";
        this.scene = canReceiveBean.getScene();
        etAccount.setText(mobile);
    }

    @Override
    public void sendSmsUpdataPhoneNumber(Object o) {
        //获取验证码成功
        timer.start();
        ToastUtil.showMessage("短信已发送,请查收");
    }

    @Override
    public void updateMobilesVerify(Object o) {
        //验证码验证成功
        startTo(CanReceiveActivity2.class, true);
    }

    @Override
    public void updateMobileSave(Object o) {

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
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

}
