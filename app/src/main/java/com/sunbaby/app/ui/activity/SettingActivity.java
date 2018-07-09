package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;

import butterknife.OnClick;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 设置
 */
public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_setting);
    }

    @OnClick({R.id.llPerson, R.id.llAdress})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.llPerson:
                //个人中心
                startTo(PersonActivity.class, false);
                break;
            case R.id.llAdress:
                //收货地址
                startTo(ManageAddressActivity.class, false);
                break;
            default:
                break;
        }
    }


}
