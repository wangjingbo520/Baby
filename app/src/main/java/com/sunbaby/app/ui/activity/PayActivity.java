package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sunbaby.app.MainActivity;
import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.utils.ToastUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

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
    @BindView(R.id.llWeixin)
    LinearLayout llWeixin;
    @BindView(R.id.rbTop)
    RadioButton rbTop;
    @BindView(R.id.rbBottom)
    RadioButton rbBottom;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    /**
     * 0:微信支付  1:支付宝支付
     */
    private int type = 0;

    private IWXAPI api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_pay);
        setTitle("支付");
        bindView();
        initData();
    }

    private void bindView() {
        //注册微信支付
        api = WXAPIFactory.createWXAPI(this, "wxb4ba3c02aa476ea1");
        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup
            .OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (group.getCheckedRadioButtonId()) {
                case R.id.rbTop:
                    //微信支付
                    type = 0;
                    break;
                case R.id.rbBottom:
                    //支付宝支付
                    type = 1;
                    break;
                default:
                    break;
            }
        }
    };

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

    @OnClick({R.id.btnSure, R.id.llWeixin, R.id.llZfb})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnSure:
                if (0==type){
                    wexinPay();
                    ToastUtil.showMessage("微信支付方式");
                }else {
                    ToastUtil.showMessage("支付宝支付方式");
                }
        //        startTo(MainActivity.class, true);
                break;
            case R.id.llWeixin:
                //微信支付
                if (!rbTop.isChecked()) {
                    rbTop.setChecked(true);
                    type = 0;
                }
                break;
            case R.id.llZfb:
                //支付宝支付
                if (!rbBottom.isChecked()) {
                    rbBottom.setChecked(true);
                    type = 1;
                }
                break;
            default:
                break;
        }
    }

    private void wexinPay() {
//        PayReq request = new PayReq();
//        request.appId = "wxd930ea5d5a258f4f";
//        request.partnerId = "1900000109";
//        request.prepayId= "1101000000140415649af9fc314aa427";
//        request.packageValue = "Sign=WXPay";
//        request.nonceStr= "1101000000140429eb40476f8896f4c9";
//        request.timeStamp= "1398746574";
//        request.sign= "7FFECB600D7157C5AA49810D2D8F28BC2811827B";
//        api.sendReq(request);
    }

}
