package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.sunbaby.app.MainActivity;
import com.sunbaby.app.R;
import com.sunbaby.app.bean.User;
import com.sunbaby.app.callback.ILoginView;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 登录
 */
public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R.id.etAccount)
    EditText etAccount;
    @BindView(R.id.etPassword)
    EditText etPassword;
    private LoginPresenter loginPresenter;

    @OnClick({R.id.btnLogin, R.id.tvFoget, R.id.tvRegister})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnLogin:
                login();
                break;
            case R.id.tvFoget:
                startTo(ForgetpasswordActivity1.class, false);
                break;
            case R.id.tvRegister:
                startTo(RegisterActivity.class, false);
                break;
            default:
                break;
        }
    }

    private void login() {
        String name = etAccount.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            showToast("请先输入您的账号");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showToast("请先输入您的密码");
            return;
        }
        loginPresenter.login(name, password);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_login);
        setTitle("登录");
        setBackLayoutVisiable(false);
        loginPresenter = new LoginPresenter(mContext, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getUser()!=null){
            if (getUserId()!=null){
                startTo(MainActivity.class, true);
            }
        }
    }

    @Override
    public void onLoginSucceed(User user) {
        //登录成功,跳转至开通会员页面
      //  startTo(MainActivity.class, true);
        startTo(JoinmemberActivity.class, true);
    }
}
