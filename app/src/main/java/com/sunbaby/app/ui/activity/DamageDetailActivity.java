package com.sunbaby.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.sunbaby.app.R;
import com.sunbaby.app.bean.DamageDetailBean;
import com.sunbaby.app.bean.DamageRecordBean;
import com.sunbaby.app.callback.IDamageRecordView;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.utils.BaseUtils;
import com.sunbaby.app.presenter.DamageRecordPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 损坏详情
 */
public class DamageDetailActivity extends BaseActivity implements IDamageRecordView {
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvYuanjia)
    TextView tvYuanjia;
    @BindView(R.id.tvGoumaijia)
    TextView tvGoumaijia;
    private String goodsDamageId = "";
    private DamageRecordPresenter damageRecordPresenter;

    public static void start(Context context, String goodsDamageId) {
        Intent starter = new Intent(context, DamageDetailActivity.class);
        starter.putExtra("goodsDamageId", goodsDamageId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_damage_detail);
        setTitle("损坏详情");
        goodsDamageId = getIntent().getStringExtra("goodsDamageId");
        damageRecordPresenter = new DamageRecordPresenter(mContext, this);
        damageRecordPresenter.damageDetails(goodsDamageId);
    }

    @Override
    public void damageDetails(DamageDetailBean damageDetailBean) {
        tvGoumaijia.setText("￥" + damageDetailBean.getPrice());
        tvName.setText(damageDetailBean.getGoodsName());
        tvTime.setText(BaseUtils.getTime(damageDetailBean.getRecoveryTime()));
        tvYuanjia.setText("￥" + damageDetailBean.getGoodsPrice());
    }

    @Override
    public void onFinish() {
    }

    @Override
    public void damageList(DamageRecordBean damageRecordBean) {

    }

    @OnClick(R.id.tvPay)
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvPay:
                break;
            default:
                break;
        }
    }

}
