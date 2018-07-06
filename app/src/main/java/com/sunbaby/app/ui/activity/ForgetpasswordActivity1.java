package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;

import butterknife.OnClick;

/**
 * com.sunbaby.app.ui.activity
 *
 * @author 王静波
 * @date 2018/7/6
 * describe 忘记密码第一步
 */
public class ForgetpasswordActivity1 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_forgetpassword1);
        setTitle("找回密码");
    }

    @OnClick(R.id.next)
    @Override
    public void onClick(View view) {
        super.onClick(view);
        startTo(ForgetpasswordActivity2.class, true);
    }


}
