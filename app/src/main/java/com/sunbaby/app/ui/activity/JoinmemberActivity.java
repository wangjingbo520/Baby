package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sunbaby.app.R;
import com.sunbaby.app.bean.AddVipBean;
import com.sunbaby.app.bean.VipBean;
import com.sunbaby.app.callback.IJoinView;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.utils.ToastUtil;
import com.sunbaby.app.common.widget.floawlayout.FlowLayout;
import com.sunbaby.app.common.widget.floawlayout.TagAdapter;
import com.sunbaby.app.common.widget.floawlayout.TagFlowLayout;
import com.sunbaby.app.presenter.JoinmemberPresenter;

import java.util.Iterator;
import java.util.Set;

import butterknife.OnClick;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 加入会员
 */
public class JoinmemberActivity extends BaseActivity implements IJoinView, TagFlowLayout
        .OnTagClickListener {
    private TagFlowLayout tag1;
    private TagFlowLayout tag2;

    private LayoutInflater mInflater;
//    private TagAdapter<VipBean.VipTypeListBean> tagAdapter1;
//    private TagAdapter<VipBean.VipTypeListBean.VipPriceListBean> tagAdapter2;

    private JoinmemberPresenter joinmemberPresenter;
    private VipBean vipBean;
    //会员类型
    private String vipTypeId = "";
    //会员金额类型id
    private String vipPriceId = "";
    //金额
    private String amount = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_joinmember);
        initView();
        initData();
    }

    private void initView() {
        setTitle("加入会员");
        tag1 = findViewById(R.id.tag1);
        tag2 = findViewById(R.id.tag2);
        mInflater = LayoutInflater.from(mContext);
        joinmemberPresenter = new JoinmemberPresenter(mContext, this);
        tag1.setOnTagClickListener(this);
    }

    private void initData() {
        joinmemberPresenter.queryVipType();
    }

    @OnClick(R.id.btnKaitong)
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnKaitong:
                //开通会员
                kaiTongVip();
                break;
            default:
                break;
        }
    }

    private void kaiTongVip() {
        //选择了会员类型
        Set<Integer> VipTypaSelectedList = tag1.getSelectedList();
        if (VipTypaSelectedList.size() == 0) {
            vipTypeId = "";
            ToastUtil.showMessage("请先选择会员类型");
            return;
        }
        int vipTypePosition = 0;
        Iterator VipTypeIterator = VipTypaSelectedList.iterator();
        while (VipTypeIterator.hasNext()) {
            vipTypePosition = (Integer) VipTypeIterator.next();
            vipTypeId = vipBean.getVipTypeList().get(vipTypePosition).getId() + "";
        }

        //选择了时间和金额
        Set<Integer> PriceSelectedList = tag2.getSelectedList();
        if (PriceSelectedList.size() == 0) {
            vipTypeId = "";
            ToastUtil.showMessage("请选择会员金额");
            return;
        }
        Iterator PriceIterator = PriceSelectedList.iterator();
        while (PriceIterator.hasNext()) {
            int pricePosition = (Integer) PriceIterator.next();
            //会员金额类型id
            vipPriceId = vipBean.getVipTypeList().get(vipTypePosition).getVipPriceList().get
                    (pricePosition).getId() + "";
            //金额
            amount = vipBean.getVipTypeList().get(vipTypePosition).getVipPriceList().get
                    (pricePosition).getPrice() + "";
        }
        joinmemberPresenter.addOrder(getUserId(), vipTypeId, vipPriceId, amount);
    }

    @Override
    public void queryVipType(final VipBean vipBean) {
        //查找对应的会员类型
        this.vipBean = vipBean;
        if (vipBean.getVipTypeList() != null && vipBean.getVipTypeList().size() > 0) {
            //会员类型展示
            tag1.setAdapter(new TagAdapter<VipBean.VipTypeListBean>(vipBean
                    .getVipTypeList()) {
                @Override
                public View getView(FlowLayout parent, int position, VipBean.VipTypeListBean bean) {
                    TextView tv = (TextView) mInflater.inflate(R.layout.tv, tag1, false);
                    tv.setText(bean.getVipName());
                    return tv;
                }
            });
            //时间和价钱的展示
            tag2.setAdapter(new TagAdapter<VipBean.VipTypeListBean
                    .VipPriceListBean>(vipBean.getVipTypeList().get(0).getVipPriceList()) {
                @Override
                public View getView(FlowLayout parent, int position, VipBean.VipTypeListBean
                        .VipPriceListBean bean) {
                    //默认第一个被选中
                    TextView tv = (TextView) mInflater.inflate(R.layout.tv, tag2, false);
                    tv.setText(bean.getEffectiveTime() + "年 " + "(¥" + bean
                            .getPrice() + ")");
                    return tv;
                }
            });
        }
    }

    @Override
    public void addOrder(AddVipBean addVipBean) {
        //开通会员
        MyPayActivity.start(mContext, addVipBean.getOrderId() + "");
        finish();
    }

    @Override
    public boolean onTagClick(View view, int position, FlowLayout parent) {
        if (vipBean != null && vipBean.getVipTypeList().size() > 0) {
            tag2.setAdapter(new TagAdapter<VipBean.VipTypeListBean
                    .VipPriceListBean>(vipBean
                    .getVipTypeList().get(position).getVipPriceList()) {
                @Override
                public View getView(FlowLayout parent, int position, VipBean.VipTypeListBean
                        .VipPriceListBean bean) {
                    //默认第一个被选中
                    TextView tv = (TextView) mInflater.inflate(R.layout.tv, tag2, false);
                    tv.setText(bean.getEffectiveTime() + "年 " + "(¥" + bean
                            .getPrice() + ")");
                    return tv;
                }
            });
        }
        return false;
    }
}
