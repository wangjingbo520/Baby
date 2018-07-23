package com.sunbaby.app.common.base;

import android.support.v4.app.Fragment;

import com.sunbaby.app.AppData;
import com.sunbaby.app.bean.User;

/**
 * @author 王静波
 * @date 2018/7/23
 * describe
 */
public class BaseStateViewFragment extends Fragment {
    public User getUser() {
        return AppData.getInstance().getUser();
    }

    public String getUserId() {
        return AppData.getInstance().getUser().getUserId() + "";
    }

    public boolean userIsLogin() {
        User user = AppData.getInstance().getUser();
        if (null == user) {
            return false;
        } else {
            return true;
        }
    }

}
