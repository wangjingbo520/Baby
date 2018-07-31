package com.sunbaby.app.common.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunbaby.app.AppData;
import com.sunbaby.app.bean.User;
import com.sunbaby.app.common.utils.DialogWithYesOrNoUtils;
import com.sunbaby.app.ui.activity.LoginActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * com.sunbaby.app.common.base
 *
 * @author wangjingbo
 * @date 2018/7/6
 * describe
 */
public abstract class BaseFragment extends BaseStateViewFragment {
    protected final String TAG = this.getClass().getSimpleName();
    private Unbinder mUnBinder;
    public View mRootView;
    public FragmentActivity mContext;

    public boolean userIsLogin(final boolean startToLogin) {
        User user = AppData.getInstance().getUser();
        if (null == user) {
            if (!((Activity) mContext).isFinishing()) {
                //show dialog
                DialogWithYesOrNoUtils.showDialog(mContext, "请先进行登录", new DialogWithYesOrNoUtils
                        .DialogCallBack() {
                    @Override
                    public void exectEvent() {
                        startTo(LoginActivity.class, startToLogin);
                    }
                });
            }
            return false;
        }
        return true;
    }

    public void startTo(Class c, boolean isFinish) {
        startActivity(new Intent(mContext, c));
        if (isFinish) {
            mContext.finish();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = null;
        if (getLayoutId() != 0) {
            this.mContext = this.getActivity();
            view = inflater.inflate(getLayoutId(), container, false);
            mUnBinder = ButterKnife.bind(this, view);
            mRootView = view;
            initView(view);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public View getRootView(){
        return mRootView;
    }

    /**
     * 数据初始化
     */
    public abstract void initData();

    /**
     * 初始化布局
     *
     * @return
     */
    protected abstract int getLayoutId();


    /**
     * 绑定布局
     *
     * @param view
     */
    public abstract void initView(View view);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }


}
