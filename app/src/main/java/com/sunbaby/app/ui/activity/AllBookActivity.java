package com.sunbaby.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;

import butterknife.OnClick;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 所有图书
 */
public class AllBookActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, AllBookActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_all_book);
        setTitleLayoutVisiable(false);
    }

    @OnClick(R.id.flBack)
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.flBack:
                finish();
                break;
            default:
                break;
        }
    }


}
