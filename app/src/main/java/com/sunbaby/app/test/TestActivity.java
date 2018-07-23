package com.sunbaby.app.test;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.sunbaby.app.R;
import com.sunbaby.app.adapter.SingleCheckAdapter;
import com.sunbaby.app.statusview.OnRetryListener;
import com.sunbaby.app.statusview.StatusLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends TestBaseActivity {

    private SingleCheckAdapter singleCheckAdapter;
    private List<String> strings;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusLayoutManager.showLoading();
   //     initData();
    }

//    private void initData() {
//        listView = findViewById(R.id.listview);
//        strings = new ArrayList<>();
//        strings.add("");
//        strings.add("");
//        strings.add("");
//        strings.add("");
//        strings.add("");
//        strings.add("");
//        strings.add("");
//        strings.add("");
//        singleCheckAdapter = new SingleCheckAdapter(this, strings);
//        listView.setAdapter(singleCheckAdapter);
//
//        findViewById(R.id.fl_back).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                statusLayoutManager.showContent();
//            }
//        });
//    }

    @Override
    protected void initStatusLayout() {
        statusLayoutManager = StatusLayoutManager.newBuilder(this)
                .contentView(R.layout.activity_test)
                .emptyDataView(R.layout.activity_emptydata)
                .errorView(R.layout.activity_error)
                .loadingView(R.layout.activity_loading)
                .emptyDataRetryViewId(R.id.button_retry)
                .onRetryListener(new OnRetryListener() {
                    @Override
                    public void onRetry() {
                        statusLayoutManager.showContent();
                    }
                })
                .netWorkErrorView(R.layout.activity_networkerror).build();
    }

    @Override
    public void onClick(View v) {

    }
}
