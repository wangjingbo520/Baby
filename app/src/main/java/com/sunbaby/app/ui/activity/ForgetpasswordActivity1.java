package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("找回密码");
        showContent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forgetpassword1;
    }

    @OnClick(R.id.next)
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.next:
                startTo(ForgetpasswordActivity2.class, true);
                break;
            default:
                break;
        }
    }
}
