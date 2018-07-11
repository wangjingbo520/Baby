package com.sunbaby.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 原手机号不能接收验证码
 */
public class CanNotReceiveActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, CanNotReceiveActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_can_not_receive);
        setTitle("修改手机号");
    }
}
