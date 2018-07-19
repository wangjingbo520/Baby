package com.sunbaby.app.common.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.sunbaby.app.AppData;
import com.sunbaby.app.bean.User;

/**
 * @author 王静波
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

    public boolean userIsLogin(boolean startToLogin) {
        User user = AppData.getInstance().getUser();
        if (null == user) {
            if (startToLogin) {
                //   startTo(LoginActivity.class);
            }
            return false;
        }
        return true;
    }


}
