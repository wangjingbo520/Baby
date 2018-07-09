package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseCameraActivity;

import butterknife.OnClick;


/**
 * @author 王静波
 * @date 2018/7/6
 * describe 支付
 */
public class PersonActivity extends BaseCameraActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_person);
        setTitle("个人资料");
    }

    @OnClick(R.id.btn)
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn:
                showCameraPopwindow(this.getWindow().getDecorView(), false, false);
                break;
            default:

        }
    }

}
