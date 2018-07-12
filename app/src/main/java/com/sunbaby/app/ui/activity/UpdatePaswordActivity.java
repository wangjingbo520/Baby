package com.sunbaby.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;
/**
 * @author 王静波
 * @date 2018/7/6
 * describe 修改密码
 */
public class UpdatePaswordActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, UpdatePaswordActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_update_pasword);
        setTitle("修改密码");
    }
}