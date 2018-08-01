package com.sunbaby.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.sunbaby.app.AppData;
import com.sunbaby.app.MyApplication;
import com.sunbaby.app.R;
import com.sunbaby.app.callback.IUpdatePaswordView;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.utils.Preferences;
import com.sunbaby.app.presenter.UpdatePaswordPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 修改密码
 */
public class UpdatePaswordActivity extends BaseActivity implements IUpdatePaswordView {

    @BindView(R.id.etOldpass)
    EditText etOldpass;
    @BindView(R.id.etNewpass)
    EditText etNewpass;
    @BindView(R.id.etNewpass1)
    EditText etNewpass1;
    private UpdatePaswordPresenter updatePaswordPresenter;

    public static void start(Context context) {
        Intent starter = new Intent(context, UpdatePaswordActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_update_pasword);
        setTitle("修改密码");
        updatePaswordPresenter = new UpdatePaswordPresenter(mContext, this);
    }

    @OnClick(R.id.btnSure)
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnSure:
                //确认
                sure();
                break;
            default:
                break;
        }
    }

    private void sure() {
        String passWord = etOldpass.getText().toString();
        String passWordNoe = etNewpass.getText().toString();
        String passWordTwo = etNewpass1.getText().toString();
        if (TextUtils.isEmpty(passWord)) {
            showToast("旧密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(passWordNoe)) {
            showToast("新密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(passWordTwo)) {
            showToast("请再次输入密码");
            return;
        }
        if (!passWordNoe.equals(passWordTwo)) {
            showToast("新密码两次输入不一致,请重新输入");
            return;
        }
        updatePaswordPresenter.updatePassword(passWord, passWordNoe, passWordTwo);
    }

    @Override
    public void updatePassword() {
        //修改密码成功
        showToast("密码修改成功,请重新登录");
        Preferences.removeAll();
        AppData.getInstance().logoutClearData();
        MyApplication.getInstance().extiLoginApp();
        startTo(LoginActivity.class, true);
    }
}
