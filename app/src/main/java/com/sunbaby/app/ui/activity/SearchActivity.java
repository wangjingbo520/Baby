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

import com.ping.greendao.gen.DBUtils;
import com.sunbaby.app.R;
import com.sunbaby.app.bean.SearchBean;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.widget.floawlayout.FlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 搜索
 */
public class SearchActivity extends BaseActivity {

    @BindView(R.id.flowlayout)
    FlowLayout flowlayout;
    @BindView(R.id.etSearch)
    EditText etSearch;
    private LayoutInflater mInflater;
    private String type;

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
        mInflater = LayoutInflater.from(this);
        initData();
    }

    private void initData() {
        type = getIntent().getStringExtra("type");
        List<SearchBean> searchBeans = DBUtils.queryAll();
        if (searchBeans.size() > 0) {
            for (int i = 0; i < searchBeans.size(); i++) {
                TextView tv = (TextView) mInflater.inflate(
                        R.layout.search_layout, flowlayout, false);
                tv.setText(searchBeans.get(i).getSearchContent());
                final String str = tv.getText().toString();
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SecondaryListActivity.start(mContext, str);
                    }
                });
                flowlayout.addView(tv);
            }
        } else {
            flowlayout.removeAllViews();
        }
    }

    @OnClick({R.id.flBack, R.id.tvSearch, R.id.ivDelete})
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
            case R.id.ivDelete:
                DBUtils.deleteAll();
                initData();
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
        SearchBean searchBean = new SearchBean();
        searchBean.setSearchContent(scount_name);
        SecondaryListActivity.serchTo(mContext, type, scount_name);
        finish();
    }

}
