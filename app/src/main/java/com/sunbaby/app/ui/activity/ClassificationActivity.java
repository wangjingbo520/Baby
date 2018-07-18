package com.sunbaby.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.sunbaby.app.R;
import com.sunbaby.app.adapter.MenuAdapter;
import com.sunbaby.app.adapter.WanjuAdapter;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.utils.UIUtils;
import com.sunbaby.app.common.widget.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 商品分类
 */
public class ClassificationActivity extends BaseActivity {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;

    private MenuAdapter menuAdapter;
    private WanjuAdapter wanjuAdapter;
    private List<String> strings;

    public static void start(Context context) {
        Intent starter = new Intent(context, ClassificationActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("玩具");
        smartrefreshlayout.setRefreshHeader(new ClassicsHeader(mContext));
        smartrefreshlayout.setRefreshFooter(new ClassicsFooter(mContext));
        smartrefreshlayout.setEnableLoadmore(false);
        strings = new ArrayList<>();
        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_classification;
    }

    protected void initData() {
        for (int i = 0; i < 10; i++) {
            strings.add("我是第" + i);
        }
        menuAdapter = new MenuAdapter(this, strings);
        listView.setAdapter(menuAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                menuAdapter.setSelectItem(position);
                menuAdapter.notifyDataSetInvalidated();
            }
        });

        wanjuAdapter = new WanjuAdapter(R.layout.recy_item_wanju, strings);
        GridLayoutManager mgr = new GridLayoutManager(this, 2);
        recyclerview.setLayoutManager(mgr);
        recyclerview.addItemDecoration(new GridSpacingItemDecoration(2, UIUtils.px2sp(this, 50),
                false));
        recyclerview.setAdapter(wanjuAdapter);

        wanjuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProductDetailsActivity.start(mContext);
            }
        });
    }


    @OnClick(R.id.etSearch)
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.etSearch:
                SearchActivity.start(mContext);
                break;
            default:
                break;
        }
    }


}
