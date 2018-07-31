package com.sunbaby.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sunbaby.app.R;
import com.sunbaby.app.WebViewActivity;
import com.sunbaby.app.bean.AddVipBean;
import com.sunbaby.app.bean.JoinBean2;
import com.sunbaby.app.bean.VipBean;
import com.sunbaby.app.callback.IJoinView2;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.utils.ToastUtil;
import com.sunbaby.app.common.widget.floawlayout.FlowLayout;
import com.sunbaby.app.common.widget.floawlayout.TagAdapter;
import com.sunbaby.app.common.widget.floawlayout.TagFlowLayout;
import com.sunbaby.app.presenter.JoinmemberPresenter;
import com.sunbaby.app.presenter.JoinmemberPresenter2;

import java.util.Iterator;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 加入会员2
 */
public class JoinmemberActivity2 extends BaseActivity implements IJoinView2, TagFlowLayout
        .OnTagClickListener {

    @BindView(R.id.tag1)
    TagFlowLayout tag1;
    @BindView(R.id.tag2)
    TagFlowLayout tag2;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvJine)
    TextView tvJine;
    @BindView(R.id.btnTuikuan)
    Button btnTuikuan;

    private LayoutInflater mInflater;

    private JoinmemberPresenter2 joinmemberPresenter2;
    private VipBean vipBean;
    private TagAdapter<VipBean.VipTypeListBean> tagAdapter1;
    private TagAdapter<VipBean.VipTypeListBean.VipPriceListBean> tagAdapter2;

    /**
     * 会员类型
     */
    private String vipTypeId = "";
    /**
     * /会员金额类型id
     */
    private String vipPriceId = "";
    /**
     * 金额
     */
    private String amount = "";


    public static void start(Context context) {
        Intent starter = new Intent(context, JoinmemberActivity2.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_joinmember2);
        setTitle("会员续费");
        tag1.setOnTagClickListener(this);
        joinmemberPresenter2 = new JoinmemberPresenter2(mContext, this);
        mInflater = LayoutInflater.from(mContext);
        initData();
    }

    private void initData() {
        joinmemberPresenter2.queryVipType();
        joinmemberPresenter2.applyRefundInit(getUserId());
    }

    @OnClick(R.id.btnKaitong)
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnKaitong:
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
        joinmemberPresenter2.addOrder(getUserId(), vipTypeId, vipPriceId, amount);
    }

    @Override
    public void applyRefundInit(JoinBean2 joinBean2) {
        //会员初始化数据
        tvJine.setText(joinBean2.getOrderData().getVipPriceId());
        tvTime.setText(joinBean2.getOrderData().getPyaTime() + "");
        int status = joinBean2.getStatus();
        if (1 == status) {
            //可以申请退款
            btnTuikuan.setVisibility(View.VISIBLE);
        } else {
            btnTuikuan.setVisibility(View.GONE);
        }
    }

    @Override
    public void queryVipType(final VipBean vipBean) {
        //查找对应的会员类型
        this.vipBean = vipBean;
        if (vipBean.getVipTypeList() != null && vipBean.getVipTypeList().size() > 0) {
            //会员类型展示
            tag1.setAdapter(tagAdapter1 = new TagAdapter<VipBean.VipTypeListBean>(vipBean
                    .getVipTypeList()) {
                @Override
                public View getView(FlowLayout parent, int position, VipBean.VipTypeListBean bean) {
                    TextView tv = (TextView) mInflater.inflate(R.layout.tv, tag1, false);
                    tv.setText(bean.getVipName());
                    return tv;
                }
            });

            //时间和价钱的展示
            tag2.setAdapter(tagAdapter2 = new TagAdapter<VipBean.VipTypeListBean
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
        //开通会员,跳到支付页面
        startTo(MyPayActivity.class, true);
    }

    @Override
    public boolean onTagClick(View view, final int position1, FlowLayout parent) {
        if (vipBean != null && vipBean.getVipTypeList().size() > 0) {
            tag2.setAdapter(tagAdapter2 = new TagAdapter<VipBean.VipTypeListBean
                    .VipPriceListBean>(vipBean
                    .getVipTypeList().get(position1).getVipPriceList()) {
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

//        tag2.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
//            @Override
//            public boolean onTagClick(View view, int position, FlowLayout parent) {
//                tvJine.setText(vipBean
//                        .getVipTypeList().get(position1).getVipPriceList().get(position).getPrice
//                                () + "");
//                return false;
//            }
//        });
        return false;
    }
}
