package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseCameraActivity;

import butterknife.OnClick;


/**
 * @author 王静波
 * @date 2018/7/6
 * describe 个人资料
 */
public class PersonActivity extends BaseCameraActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_person);
        setTitle("个人资料");
    }

    @OnClick({R.id.llTouxiang, R.id.llPassword, R.id.llPhoneNumber})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.llTouxiang:
                showCameraPopwindow(this.getWindow().getDecorView(), false, false);
                break;
            case R.id.llPassword:
                UpdatePaswordActivity.start(this);
                break;
            case R.id.llPhoneNumber:
                startTo(UpdatePhoneNumberActivity.class, false);
                break;
            default:
                break;
        }
    }

}
