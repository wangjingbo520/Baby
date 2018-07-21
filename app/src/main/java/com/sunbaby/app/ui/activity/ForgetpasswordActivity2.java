package com.sunbaby.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.sunbaby.app.R;
import com.sunbaby.app.callback.IForgetView;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.widget.CommomDialog;
import com.sunbaby.app.presenter.ForgetpasswordPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 忘记密码第二步
 */
public class ForgetpasswordActivity2 extends BaseActivity implements CommomDialog.DialogCallk,
        IForgetView {
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etSurePassword)
    EditText etSurePassword;
    private CommomDialog commomDialog;
    private String code;
    private String mobile;
    private ForgetpasswordPresenter forgetpasswordPresenter;

    public static void start(Context context, String code, String mobile) {
        Intent starter = new Intent(context, ForgetpasswordActivity2.class);
        starter.putExtra("code", code);
        starter.putExtra("mobile", mobile);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_forgetpassword2);
        setTitle("找回密码");
        code = getIntent().getStringExtra("code");
        mobile = getIntent().getStringExtra("mobile");
        forgetpasswordPresenter = new ForgetpasswordPresenter(mContext, this);
    }


    @OnClick(R.id.btnSure)
    @Override
    public void onClick(View view) {
        super.onClick(view);
        commit();
    }

    private void commit() {
        String password = etPassword.getText().toString().trim();
        String surePassword = etSurePassword.getText().toString().trim();
        if (!password.equals(surePassword)) {
            showToast("新密码与确认密码不一致");
            return;
        }
        forgetpasswordPresenter.forgetPassword(mobile, code, "UPDATE_MOBILE_SCENE", password,
                surePassword);
    }

    @Override
    public void sure() {
        startTo(LoginActivity.class, true);
    }

    @Override
    public void onGetCodeSucceed() {

    }

    @Override
    public void forgetPassword() {
        if (commomDialog == null) {
            commomDialog = new CommomDialog(this, this, "修改密码成功", "请返回登录页面重新登录");
        }
        commomDialog.show();
    }

    @Override
    public void updateMobilesVerify() {

    }
}
