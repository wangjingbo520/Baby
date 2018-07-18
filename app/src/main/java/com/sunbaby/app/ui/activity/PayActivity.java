package com.sunbaby.app.ui.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;
import com.sunbaby.app.MainActivity;
import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.utils.pay.AuthResult;
import com.sunbaby.app.common.utils.pay.OrderInfoUtil2_0;
import com.sunbaby.app.common.utils.pay.PayResult;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

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
    /**
     * 微信支付业务：入参app_id
     */
    private IWXAPI api;
    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "";
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    public static final String RSA2_PRIVATE = "";
    public static final String RSA_PRIVATE = "";
    /**
     * 支付宝账户登录授权业务：入参pid值
     */
    public static final String PID = "";
    /**
     * 支付宝账户登录授权业务：入参target_id值
     */
    public static final String TARGET_ID = "";


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
                startTo(MainActivity.class, true);
//                if (0 == type) {
//                    //微信支付
//                    wexinPay();
//                } else {
//                    //支付宝支付,先判断是否授权,再调用支付接口
//                    authV2();
//                }
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

    /***************************************微信支付和支付宝支付**********************************************/
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


    /**
     * 支付宝账户授权业务
     */
    public void authV2() {
        if (TextUtils.isEmpty(PID) || TextUtils.isEmpty(APPID)
                || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))
                || TextUtils.isEmpty(TARGET_ID)) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置PARTNER |APP_ID| " +
                    "RSA_PRIVATE| TARGET_ID")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * authInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID,
                TARGET_ID, rsa2);
        String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2);
        final String authInfo = info + "&" + sign;
        Runnable authRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造AuthTask 对象
                AuthTask authTask = new AuthTask(PayActivity.this);
                // 调用授权接口，获取授权结果
                Map<String, String> result = authTask.authV2(authInfo, true);
                Message msg = new Message();
                msg.what = SDK_AUTH_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }

    /**
     * 支付宝支付业务
     */
    private void alipay() {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty
                (RSA_PRIVATE))) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("提交的支付宝信息", result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();
                    // 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult
                            .getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(PayActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode())
                                , Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(PayActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()),
                                Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }
    };


}
