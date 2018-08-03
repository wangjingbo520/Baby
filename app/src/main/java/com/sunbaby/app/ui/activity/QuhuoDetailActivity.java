package com.sunbaby.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunbaby.app.R;
import com.sunbaby.app.bean.QShuoDetaiBean;
import com.sunbaby.app.callback.IQuhuoDetaiView;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.presenter.QShuoDetaiPresenter;

import butterknife.BindView;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 取货详情
 */
public class QuhuoDetailActivity extends BaseActivity implements IQuhuoDetaiView {
    @BindView(R.id.ivPic)
    ImageView ivPic;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvXingming)
    TextView tvXingming;
    @BindView(R.id.tvPhoneNumber)
    TextView tvPhoneNumber;
    @BindView(R.id.tvAdress)
    TextView tvAdress;
    @BindView(R.id.tvTime)
    TextView tvTime;

    private String orderid;
    private String time;
    private String Delivery_status;
    private String GoodsId;
    private String DispatchingID;

    private QShuoDetaiPresenter qShuoDetaiPresenter;

    public static void start(Context context, String orderid, String time, String
            Delivery_status, String GoodsId, String DispatchingID) {
        Intent starter = new Intent(context, QuhuoDetailActivity.class);
        starter.putExtra("orderid", orderid);
        starter.putExtra("time", time);
        starter.putExtra("Delivery_status", Delivery_status);
        starter.putExtra("GoodsId", GoodsId);
        starter.putExtra("DispatchingID", DispatchingID);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_quhuo_detail);
        setTitle("取货详情");
        qShuoDetaiPresenter = new QShuoDetaiPresenter(mContext, this);
        bindView();
        initData();
    }

    private void bindView() {
        orderid = getIntent().getStringExtra("orderid");
        time = getIntent().getStringExtra("time");
        Delivery_status = getIntent().getStringExtra("Delivery_status");
        GoodsId = getIntent().getStringExtra("GoodsId");
        DispatchingID = getIntent().getStringExtra("DispatchingID");
    }

    private void initData() {
        qShuoDetaiPresenter.retrievingListdetails(orderid, time, Delivery_status, GoodsId,
                DispatchingID);
    }

    @Override
    public void retrievingListdetails(QShuoDetaiBean qShuoDetaiBean) {


    }
}
