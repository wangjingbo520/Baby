package com.sunbaby.app.wxapi;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 支付回调的处理
 */
public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_wxpay_entry);
        setTitle("支付结果");
        //注册微信支付
        api = WXAPIFactory.createWXAPI(this, "APPID");
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    //0   成功 展示成功页面
    //-1  错误 可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
    //-2  用户取消 无需处理。发生场景：用户不支付了，点击取消，返回APP。
    @Override
    public void onResp(BaseResp resp) {
        Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("微信支付结果：%s" + String.valueOf(resp.errCode));
            builder.show();
        }
    }
}
