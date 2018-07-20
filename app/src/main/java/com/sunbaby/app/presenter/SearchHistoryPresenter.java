package com.sunbaby.app.presenter;

import android.content.Context;

import com.sunbaby.app.bean.Areabean;
import com.sunbaby.app.bean.SearchHistoryBean;
import com.sunbaby.app.callback.IRegisterView;
import com.sunbaby.app.callback.ISearchHistoryView;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.base.BasePresenter;

/**
 * @author 王静波
 * @date 2018/7/20
 * describe
 */
public class SearchHistoryPresenter extends BasePresenter {
    private ISearchHistoryView iSearchHistoryView;

    public SearchHistoryPresenter(Context context, ISearchHistoryView iSearchHistoryView) {
        super(context);
        this.iSearchHistoryView = iSearchHistoryView;
    }

    public void regionList(String user_id, String scount_name) {
        mRequestClient.queryAccountSearch(user_id, scount_name).subscribe(new ProgressSubscriber<SearchHistoryBean>
                (mContext) {
            @Override
            public void onNext(SearchHistoryBean searchHistoryBean) {
                if (null != iSearchHistoryView) {
                    iSearchHistoryView.queryAccountSearch(searchHistoryBean);
                }
            }

        });
    }
}
