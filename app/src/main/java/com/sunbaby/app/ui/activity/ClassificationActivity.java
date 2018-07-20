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
import com.sunbaby.app.adapter.GoodsTypeAdapter;
import com.sunbaby.app.adapter.MenuAdapter;
import com.sunbaby.app.bean.ClassificationBean;
import com.sunbaby.app.callback.IClassificationView;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.utils.UIUtils;
import com.sunbaby.app.common.widget.GridSpacingItemDecoration;
import com.sunbaby.app.presenter.ClassificationPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 商品分类
 */
public class ClassificationActivity extends BaseActivity implements IClassificationView {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;

    private MenuAdapter menuAdapter;
    private GoodsTypeAdapter goodsTypeAdapter;
    private List<String> titleLeft;
    private ClassificationPresenter classificationPresenter;
    private List<ClassificationBean.GoodsTypeListBean> goodsTypeListBeans;
    /**
     * 1 图书 2 玩具
     */
    private String type;

    public static void start(Context context, String type) {
        Intent starter = new Intent(context, ClassificationActivity.class);
        starter.putExtra("type", type);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showContent();
        initView();
        initData();
    }

    private void initView() {
        titleLeft = new ArrayList<>();
        type = getIntent().getStringExtra("type");
        if ("1".equals(type)) {
            titleLeft.add("图书");
        } else {
            titleLeft.add("玩具");
        }
        menuAdapter = new MenuAdapter(this, titleLeft);
        listView.setAdapter(menuAdapter);
        classificationPresenter = new ClassificationPresenter(mContext, this);
        smartrefreshlayout.setRefreshHeader(new ClassicsHeader(mContext));
        smartrefreshlayout.setRefreshFooter(new ClassicsFooter(mContext));
        smartrefreshlayout.setEnableLoadmore(false);
        goodsTypeListBeans = new ArrayList<>();

        goodsTypeAdapter = new GoodsTypeAdapter(R.layout.recy_item_wanju, goodsTypeListBeans);
        GridLayoutManager mgr = new GridLayoutManager(this, 2);
        recyclerview.setLayoutManager(mgr);
        recyclerview.addItemDecoration(new GridSpacingItemDecoration(2, UIUtils.px2sp(this, 50),
                false));
        recyclerview.setAdapter(goodsTypeAdapter);
        goodsTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //进入二级列表页面
                SecondaryListActivity.start(mContext, goodsTypeListBeans.get(position).getId() +
                        "");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                menuAdapter.setSelectItem(position);
                menuAdapter.notifyDataSetInvalidated();
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_classification;
    }

    protected void initData() {
        classificationPresenter.queryGoodsType(type);
    }

    @OnClick(R.id.etSearch)
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.etSearch:
                SearchActivity.start(mContext, "");
                break;
            default:
                break;
        }
    }

    @Override
    protected void doOnRetry() {
        super.doOnRetry();
        initData();
    }

    @Override
    public void queryGoodsType(ClassificationBean classificationBean) {
        //一级列表,右边
        if (classificationBean.getGoodsTypeList().size() < 1) {
            showEmpty();
        } else {
            goodsTypeAdapter.addData(classificationBean.getGoodsTypeList());
            showContent();
        }
    }
}
