package com.sunbaby.app;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.sunbaby.app.common.utils.ToastUtil;
import com.sunbaby.app.ui.activity.DistributionActivity;
import com.sunbaby.app.ui.activity.LoginActivity;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 启动页,权限检测
 */
@RuntimePermissions
public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        StartActivityPermissionsDispatcher.getMultiWithCheck(this);
    }

    private void startMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(StartActivity.this, MainActivity.class));
            //    startActivity(new Intent(StartActivity.this, DistributionActivity.class));
                finish();
            }
        }, 2000);
    }

    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE, Manifest
            .permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void getMulti() {
        startMainActivity();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        StartActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode,
                grantResults);
    }

    /**
     * 这个方法会拦截你发出的请求，这个方法用于告诉用户你接下来申请的权限是干嘛的，说服用户给你权限
     */
    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE, Manifest
            .permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showRationale(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("使用此功能需要WRITE_EXTERNAL_STORAGE，下一步将继续请求权限")
                .setPositiveButton("下一步", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();//继续执行请求
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.cancel();//取消执行请求
            }
        }).show();
    }

    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void multiDenied() {
        //您已经拒绝权限,继续申请
        ToastUtil.showMessage("您已经拒绝权限,程序自动退出");
        finish();
    }

    /**
     * 用于标注如果权限请求失败,而且用户勾选不再询问的时候执行的方法，注解括号里面有参数，传入想要申请的权限
     */
    @OnNeverAskAgain({Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE, Manifest
            .permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void multiNeverAsk() {

        new AlertDialog.Builder(this)
                .setMessage("您已经拒绝请求权限,请到设置页面打开权限")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startSetting(StartActivity.this, getPackageName());

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).show();
    }

    private void startSetting(Context context, String packageName) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", packageName, null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra("com.android.settings.ApplicationPkgName", packageName);
        }
        context.startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //检查是否拥有全部权限

    }
}
