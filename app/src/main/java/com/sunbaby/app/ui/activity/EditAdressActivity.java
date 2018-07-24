package com.sunbaby.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.sunbaby.app.R;
import com.sunbaby.app.bean.EditAdressBean;
import com.sunbaby.app.callback.IEditAddressView;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.presenter.EditAdressPresenter;

import butterknife.BindView;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 编辑收货地址
 */
public class EditAdressActivity extends BaseActivity implements IEditAddressView {
    @BindView(R.id.etAccount)
    EditText etAccount;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    private EditAdressPresenter editAdressPresenter;
    private String id = "";

    public static void start(Context context, String id) {
        Intent starter = new Intent(context, EditAdressActivity.class);
        starter.putExtra("id", id);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_edit_adress);
        setRightText("保存");
        setTitle("编辑收货地址");
        id = getIntent().getStringExtra("id");
        editAdressPresenter = new EditAdressPresenter(mContext, this);
        initData();
    }

    private void initData() {
        editAdressPresenter.updateAddressInit(id);
    }

    @Override
    public void onRightLisenter() {
        super.onRightLisenter();
    }

    @Override
    public void updateAddressInit(EditAdressBean editAdressBean) {

    }

}
