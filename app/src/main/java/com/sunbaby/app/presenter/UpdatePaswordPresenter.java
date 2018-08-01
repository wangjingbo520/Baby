package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.bean.SearchHistoryBean;
import com.sunbaby.app.callback.ISearchHistoryView;
import com.sunbaby.app.callback.IUpdatePaswordView;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author Wangjingbo
 * @date 2018/8/1
 * describe
 */
public class UpdatePaswordPresenter extends BasePresenter {
    private IUpdatePaswordView iUpdatePaswordView;

    public UpdatePaswordPresenter(Context context, IUpdatePaswordView iUpdatePaswordView) {
        super(context);
        this.iUpdatePaswordView = iUpdatePaswordView;
    }

    /**
     * 修改密码
     *
     * @param passWord
     * @param passWordNoe
     * @param passWordTwo
     */
    public void updatePassword(String passWord, String passWordNoe, String passWordTwo) {
        mRequestClient.updatePassword(passWord, passWordNoe, passWordTwo).subscribe(new ProgressSubscriber<Object>
                (mContext) {
            @Override
            public void onNext(Object object) {
                if (null != iUpdatePaswordView) {
                    iUpdatePaswordView.updatePassword();
                }
            }

        });
    }
}