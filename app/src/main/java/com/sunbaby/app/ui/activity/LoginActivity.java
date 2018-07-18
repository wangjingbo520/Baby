package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author 王静波
 * @date 2018/7/6
 * describe 登录
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.etAccount)
    EditText etAccount;
    @BindView(R.id.etPassword)
    EditText etPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.btnLogin, R.id.tvFoget, R.id.tvRegister})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnLogin:
                startTo(JoinmemberActivity.class, true);
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setTitle("登录");
        showContent();

    }

}
