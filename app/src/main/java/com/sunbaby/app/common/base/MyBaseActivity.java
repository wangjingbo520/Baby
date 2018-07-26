package com.sunbaby.app.common.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.sunbaby.app.AppData;
import com.sunbaby.app.bean.User;
import com.sunbaby.app.common.utils.DialogWithYesOrNoUtils;
import com.sunbaby.app.ui.activity.LoginActivity;

/**
 * @author wangjingbo
 * @date 2018/7/19
 * describe 主要用来存储一些方法
 */
public class MyBaseActivity extends AppCompatActivity {
    public User getUser() {
        return AppData.getInstance().getUser();
    }

    public String getUserId() {
        return AppData.getInstance().getUser().getUserId() + "";
    }

    public void startTo(Class c, boolean isFinish) {
        startActivity(new Intent(this, c));
        if (isFinish) {
            this.finish();
        }
    }

    public boolean userIsLogin(final boolean startToLogin) {
        User user = AppData.getInstance().getUser();
        if (null == user) {
            DialogWithYesOrNoUtils.showDialog(this, "请先进行登录", new DialogWithYesOrNoUtils
                    .DialogCallBack() {
                @Override
                public void exectEvent() {
                    startTo(LoginActivity.class, startToLogin);
                }
            });
            return false;
        }
        return true;
    }
}
