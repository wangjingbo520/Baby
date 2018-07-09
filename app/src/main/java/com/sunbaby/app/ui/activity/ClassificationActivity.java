package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sunbaby.app.R;
import com.sunbaby.app.adapter.MenuAdapter;
import com.sunbaby.app.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    private MenuAdapter menuAdapter;
    private List<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classification);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        strings = new ArrayList<>();
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
             //   menuAdapter.notifyDataSetChanged();
            }
        });
    }
}
