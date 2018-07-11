package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;

import butterknife.OnClick;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 修改手机号码
 */
public class UpdatePhoneNumberActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_update_phone_number);
        setTitle("修改手机号");
    }

    @OnClick({R.id.llYes, R.id.llNo})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.llYes:
                //原手机号能接收验证码
                CanReceiveActivity.start(this);
                break;
            case R.id.llNo:
                //原手机号不能接收验证码
                CanNotReceiveActivity.start(this);
                break;
            default:
                break;
        }
    }

}
