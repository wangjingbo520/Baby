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
import com.sunbaby.app.common.widget.floawlayout.FlowLayout;
import com.sunbaby.app.common.widget.floawlayout.TagAdapter;
import com.sunbaby.app.common.widget.floawlayout.TagFlowLayout;
import com.sunbaby.app.presenter.JoinmemberPresenter;

import java.util.Iterator;
import java.util.Set;

import butterknife.OnClick;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 加入会员
 */
public class JoinmemberActivity extends BaseActivity implements IJoinView, TagFlowLayout
        .OnTagClickListener {
    private TagFlowLayout tag1;
    private TagFlowLayout tag2;

    private LayoutInflater mInflater;
    private TagAdapter<VipBean.VipTypeListBean> tagAdapter1;
    private TagAdapter<VipBean.VipTypeListBean.VipPriceListBean> tagAdapter2;

    private JoinmemberPresenter joinmemberPresenter;
    private VipBean vipBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusLayoutManager.showLoading();
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_joinmember;
    }


    @OnClick(R.id.btnKaitong)
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnKaitong:
                joinmemberPresenter.addOrder(getUserId(), "vipTypeId", "amount");
                break;
            default:
                break;
        }
    }

    @Override
    public void queryVipType(final VipBean vipBean) {
        //查找对应的会员类型
        showContent();
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
                    tv.setText(bean.getEffectiveTime() + "\n" + bean
                            .getPrice());
                    return tv;
                }
            });
        }
    }

    @Override
    public void addOrder(AddVipBean addVipBean) {
        //开通会员
        Set<Integer> selectedList = tag1.getSelectedList();
        Iterator it = selectedList.iterator();
        while (it.hasNext()) {
            //这里执行获取选中的值
            // period = String.valueOf((Integer) it.next() + 1);
        }
        startTo(PayActivity.class, true);
    }

    @Override
    public boolean onTagClick(View view, int position, FlowLayout parent) {
        if (vipBean != null && vipBean.getVipTypeList().size() > 0) {
            tag2.setAdapter(tagAdapter2 = new TagAdapter<VipBean.VipTypeListBean
                    .VipPriceListBean>(vipBean
                    .getVipTypeList().get(position).getVipPriceList()) {
                @Override
                public View getView(FlowLayout parent, int position, VipBean.VipTypeListBean
                        .VipPriceListBean bean) {
                    //默认第一个被选中
                    TextView tv = (TextView) mInflater.inflate(R.layout.tv, tag2, false);
                    tv.setText(bean.getEffectiveTime() + "\n" + bean
                            .getPrice());
                    return tv;
                }
            });
        }
        return false;
    }
}
