package com.sunbaby.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sunbaby.app.EventbusConstant;
import com.sunbaby.app.R;
import com.sunbaby.app.adapter.ManageAdressAdapter;
import com.sunbaby.app.bean.AdressBean;
import com.sunbaby.app.callback.IAdressView;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.utils.UIUtils;
import com.sunbaby.app.common.widget.MyRecycleViewDivider;
import com.sunbaby.app.event.EventMessage;
import com.sunbaby.app.presenter.ManageAddressPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 管理收货地址
 */
public class ManageAddressActivity extends BaseActivity implements IAdressView {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.include)
    View include;

    private ManageAdressAdapter recyDemoAdapter;
    private ManageAddressPresenter manageAddressPresenter;

    public static void start(Context context) {
        Intent starter = new Intent(context, ManageAddressActivity.class);
        context.startActivity(starter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventMessage eventMessage) {
        if (EventbusConstant.ADRESSMANAGE_ACTIVITY == eventMessage.getClassInfo()) {
            //收到了删除了最后一条收货地址以后的事件,展示和隐藏
            initData();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_manage_address);
        setTitle("管理收货地址");
        manageAddressPresenter = new ManageAddressPresenter(mContext, this);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new MyRecycleViewDivider(this, LinearLayoutManager
                .HORIZONTAL, UIUtils.px2Dp(this, 20),
                ContextCompat.getColor(this, R.color.background)));
        recyDemoAdapter = new ManageAdressAdapter(R.layout.recy_item_manage_address, null);
        mRecyclerView.setAdapter(recyDemoAdapter);

        recyDemoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener
                () {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                List<AdressBean.AddressListBean> data = recyDemoAdapter.getData();
                if (view.getId() == R.id.check_box) {
                    //设置为默认地址
                    manageAddressPresenter.defaultAddress(data.get(position).getId() +
                            "", position);
                } else if (view.getId() == R.id.llEditAdress) {
                    //编辑地址
                    EditAdressActivity.start(mContext, data.get(position).getId() + "");
                } else if (view.getId() == R.id.llDeleteAdress) {
                    //删除地址
                    manageAddressPresenter.deleteById(data.get(position).getId()
                            + "", position);
                }
            }
        });
    }

    private void initData() {
        manageAddressPresenter.addressList(getUserId());
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

    @Override
    public void addressList(AdressBean adressBean) {
        if (adressBean.getAddressList().size() <= 0) {
            include.setVisibility(View.VISIBLE);
        } else {
            include.setVisibility(View.GONE);
            recyDemoAdapter.setNewData(adressBean.getAddressList());
        }
    }

    @Override
    public void deleteById(int position) {
        //删除成功
        showToast("地址删除成功");
        recyDemoAdapter.deleteAdress(position);
    }

    @Override
    public void defaultAddress(int position) {
        //设置默认地址
        showToast("设置默认地址成功");
        recyDemoAdapter.setDefaultAdress(position);
    }
}
