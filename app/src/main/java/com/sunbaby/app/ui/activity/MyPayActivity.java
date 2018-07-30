package com.sunbaby.app.ui.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;
import com.sunbaby.app.MainActivity;
import com.sunbaby.app.R;
import com.sunbaby.app.adapter.RecyGuihuangAdapter;
import com.sunbaby.app.adapter.SingleCheckAdapter;
import com.sunbaby.app.bean.AlipayBean;
import com.sunbaby.app.bean.GuihuangBean;
import com.sunbaby.app.bean.PayBean;
import com.sunbaby.app.bean.WeChatPayBean;
import com.sunbaby.app.callback.IMypayView;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.utils.pay.AuthResult;
import com.sunbaby.app.common.utils.pay.OrderInfoUtil2_0;
import com.sunbaby.app.common.utils.pay.PayResult;
import com.sunbaby.app.presenter.MyPayPresenter;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 支付,从后台返回的列表
 */
public class MyPayActivity extends BaseActivity implements IMypayView {

    @BindView(R.id.cv_countdownView)
    CountdownView cv_countdownView;
    @BindView(R.id.listview)
    ListView listview;

    /**
     * 0:微信支付  1:支付宝支付
     */
    private String payType;
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

    private MyPayPresenter myPayPresenter;
    private SingleCheckAdapter singleCheckAdapter;
    private PayBean payBean;
    private String orderId;

    /**
     * @param context
     * @param orderId 订单id
     */
    public static void start(Context context, String orderId) {
        Intent starter = new Intent(context, MyPayActivity.class);
        starter.putExtra("orderId", orderId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_my_pay);
        setTitle("支付");
        orderId = getIntent().getStringExtra("orderId");
        myPayPresenter = new MyPayPresenter(mContext, this);
        initData();
        //注册微信支付
        api = WXAPIFactory.createWXAPI(this, "wxb4ba3c02aa476ea1");
        cv_countdownView.setTag("test22");
        long time = (long) 60 * 60 * 1000;
        cv_countdownView.start(time);
        cv_countdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                //倒计时结束的操作
                finish();
            }
        });
    }

    private void initData() {
        myPayPresenter.queryPayMethod();
    }

    @OnClick({R.id.btnSure})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnSure:
                checkPay();
                break;
            default:
                break;
        }
    }

    private void checkPay() {
        if (singleCheckAdapter != null) {
            payType = singleCheckAdapter.getSelectPosition();
            //这里需要判断是哪个支付类型,再去调用支付客户端
        }
        if (!TextUtils.isEmpty(payType)) {
            int position = Integer.parseInt(payType);
            showToast(payBean.getPayList().get(position).getName());
        } else {
            showToast("请先选择支付方式");
        }

    }


    /**
     * 支付宝支付业务
     */
    private void alipay() {
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(MyPayActivity.this);
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
                        Toast.makeText(MyPayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(MyPayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(MyPayActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode())
                                , Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(MyPayActivity.this,
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

    @Override
    public void queryPayMethod(PayBean payBean) {
        //支付方式返回列表
        this.payBean = payBean;
        singleCheckAdapter = new SingleCheckAdapter(mContext, payBean.getPayList());
        listview.setAdapter(singleCheckAdapter);
    }

    /***********************微信支付和支付宝支付接口**************************************************/
    @Override
    public void wechatPayBefore(WeChatPayBean weChatPayBean) {
        //
        PayReq request = new PayReq();
        request.appId = "wxd930ea5d5a258f4f";
        request.partnerId = weChatPayBean.getPartnerid();
        request.prepayId= weChatPayBean.getPrepayid();
        request.packageValue = weChatPayBean.getPackageX();
        request.nonceStr= weChatPayBean.getNoncestr();
        request.timeStamp= weChatPayBean.getTimestamp();
        request.sign= weChatPayBean.getSign();
        api.sendReq(request);
    }

    @Override
    public void alipayBefore(AlipayBean alipayBean) {



    }


}
