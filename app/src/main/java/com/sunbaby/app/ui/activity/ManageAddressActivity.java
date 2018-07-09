package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sunbaby.app.R;
import com.sunbaby.app.adapter.ManageAdressAdapter;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.widget.MyRecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 管理收货地址
 */
public class ManageAddressActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private List<String> strings;
    private ManageAdressAdapter recyDemoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_manage_address);
        setTitle("管理收货地址");
        initData();
    }

    private void initData() {
        strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new MyRecycleViewDivider(this, LinearLayoutManager
                .HORIZONTAL, 15,
                ContextCompat.getColor(this, R.color.background)));
        recyDemoAdapter = new ManageAdressAdapter(R.layout.recy_item_manage_address, strings);
        mRecyclerView.setAdapter(recyDemoAdapter);

        recyDemoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener
                () {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.checkbox) {
                    CheckBox checkBox = (CheckBox) view;
                    if (checkBox.isChecked()) {
                        //   adapter.setNewData(null);
                    } else {
                        adapter.setNewData(strings);
                        //ToastUtil.show("你取消啦");
                    }
                }
            }
        });
    }

    @OnClick(R.id.tvAddress)
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvAddress:
                AddNewAddressActivity.start(this);
                break;
            default:
                break;
        }
    }

}