package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.bean.PersonBean;
import com.sunbaby.app.callback.IPersonView;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author 王静波
 * @date 2018/7/17
 * describe
 */
public class PersonPresenter extends BasePresenter {
    private IPersonView iPersonView;

    public PersonPresenter(Context context, IPersonView iPersonView) {
        super(context);
        this.iPersonView = iPersonView;
    }


    /**
     * 个人资料
     *
     * @param userId
     */
    public void personalData(String userId) {
        mRequestClient.personalData(userId).subscribe(new ProgressSubscriber<PersonBean>
                (mContext) {
            @Override
            public void onNext(PersonBean personBean) {
                if (null != iPersonView) {
                    iPersonView.personalData(personBean);
                }
            }
        });
    }

    /**
     * 个人资料修改
     *
     * @param photo
     * @param userName
     * @param userId
     * @param sex
     */
    public void updatePersonal(String photo, String userName, String userId, String sex) {
        mRequestClient.updatePersonal(photo, userName, userId, sex).subscribe(new ProgressSubscriber<Object>
                (mContext) {
            @Override
            public void onNext(Object object) {
                if (null != iPersonView) {
                    iPersonView.updatePersonal();
                }
            }
        });
    }

}
