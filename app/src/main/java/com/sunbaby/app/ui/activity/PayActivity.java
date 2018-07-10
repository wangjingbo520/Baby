package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.sunbaby.app.MainActivity;
import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 支付
 */
public class PayActivity extends BaseActivity {

    @BindView(R.id.cv_countdownView)
    CountdownView cv_countdownView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_pay);
        setTitle("支付");
        initData();
    }

    private void initData() {
        cv_countdownView.setTag("test22");
        long time = (long) 60 * 60 * 1000;
        cv_countdownView.start(time);
        cv_countdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                //倒计时结束的操作
            }
        });
    }

    @OnClick(R.id.btnSure)
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnSure:
                startTo(MainActivity.class, true);
                break;
            default:
                break;
        }
    }

}
