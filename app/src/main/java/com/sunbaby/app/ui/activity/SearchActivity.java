package com.sunbaby.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sunbaby.app.R;
import com.sunbaby.app.bean.SearchHistoryBean;
import com.sunbaby.app.callback.ISearchHistoryView;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.widget.floawlayout.FlowLayout;
import com.sunbaby.app.common.widget.floawlayout.TagAdapter;
import com.sunbaby.app.common.widget.floawlayout.TagFlowLayout;
import com.sunbaby.app.presenter.SearchHistoryPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 搜索
 */
public class SearchActivity extends BaseActivity implements ISearchHistoryView {

    @BindView(R.id.tagflowlayout)
    TagFlowLayout tagflowlayout;
    @BindView(R.id.etSearch)
    EditText etSearch;
    private LayoutInflater mInflater;

    private SearchHistoryPresenter searchHistoryPresenter;
    private SearchHistoryBean searchHistoryBean;

    public static void start(Context context, String type) {
        Intent starter = new Intent(context, SearchActivity.class);
        starter.putExtra("type", type);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_search);
        setTitleLayoutVisiable(false);
        mInflater = LayoutInflater.from(mContext);
        searchHistoryPresenter = new SearchHistoryPresenter(mContext, this);
        searchHistoryPresenter.regionList(getUserId(), "");
        tagflowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                if (searchHistoryBean != null && searchHistoryBean.getList().size() > 0) {
                    SecondaryListActivity.start(mContext, searchHistoryBean.getList().get
                            (position).getScount_name());
                    finish();
                }
                return false;
            }
        });
    }

    @OnClick({R.id.flBack, R.id.tvSearch})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.flBack:
                finish();
                break;
            case R.id.tvSearch:
                search();
                break;
            default:
                break;
        }
    }

    private void search() {
        String scount_name = etSearch.getText().toString();
        if (TextUtils.isEmpty(scount_name)) {
            showToast("请输入搜索关键字");
            return;
        }
        SecondaryListActivity.start(mContext, scount_name);
        finish();
    }

    @Override
    public void queryAccountSearch(final SearchHistoryBean searchHistoryBean) {
        this.searchHistoryBean = searchHistoryBean;
        tagflowlayout.setAdapter(new TagAdapter<SearchHistoryBean.ListBean>(searchHistoryBean
                .getList()) {
            @Override
            public View getView(FlowLayout parent, int position, SearchHistoryBean.ListBean bean) {
                //默认第一个被选中
                TextView tv = (TextView) mInflater.inflate(R.layout.item_top_search,
                        tagflowlayout, false);
                tv.setText(bean.getScount_name());
                return tv;
            }
        });

    }
}
