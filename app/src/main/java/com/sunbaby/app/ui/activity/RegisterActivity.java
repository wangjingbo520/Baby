package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.widget.CommomDialog;

import butterknife.OnClick;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 注册
 */
public class RegisterActivity extends BaseActivity implements CommomDialog.DialogCallk {
    private CommomDialog commomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_register);
        setTitle("注册");
    }

    @OnClick(R.id.btnRegister)
    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (commomDialog == null) {
            commomDialog = new CommomDialog(this, this, "注册成功", "立即前往登录");
        }
        commomDialog.show();
    }

    @Override
    public void sure() {
        finish();
    }
}
