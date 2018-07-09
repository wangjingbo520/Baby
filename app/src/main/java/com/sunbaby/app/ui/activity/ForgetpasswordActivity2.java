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
 * describe 忘记密码第二步
 */
public class ForgetpasswordActivity2 extends BaseActivity implements CommomDialog.DialogCallk {
    private CommomDialog commomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_forgetpassword2);
        setTitle("找回密码");
    }

    @OnClick(R.id.btnSure)
    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (commomDialog == null) {
            commomDialog = new CommomDialog(this, this, "修改密码成功", "请返回登录页面重新登录");
        }
        commomDialog.show();
    }

    @Override
    public void sure() {
        finish();
    }
}
