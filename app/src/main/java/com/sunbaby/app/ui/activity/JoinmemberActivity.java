package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 加入会员
 */
public class JoinmemberActivity extends BaseActivity implements IJoinView, TagFlowLayout
        .OnTagClickListener {
    @BindView(R.id.tag1)
    TagFlowLayout tag1;
    @BindView(R.id.tag2)
    TagFlowLayout tag2;

    private LayoutInflater mInflater;
    private TagAdapter<VipBean.VipTypeListBean> tagAdapter1;
    private TagAdapter<VipBean.VipTypeListBean> tagAdapter2;

    private JoinmemberPresenter joinmemberPresenter;
    private VipBean vipBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_joinmember);
        setTitle("加入会员");
        mInflater = LayoutInflater.from(mContext);
        joinmemberPresenter = new JoinmemberPresenter(mContext, this);
        tag1.setOnTagClickListener(this);
        initData();
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
                //    joinmemberPresenter.addOrder(getUser().getUserId(), "vipTypeId", "amount");
                startTo(PayActivity.class, true);
                break;
            default:
                break;
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
            tagAdapter1.setSelectedList(1);
            //时间和价钱的展示
            tag2.setAdapter(tagAdapter2 = new TagAdapter<VipBean.VipTypeListBean>(vipBean
                    .getVipTypeList()) {
                @Override
                public View getView(FlowLayout parent, int position, VipBean.VipTypeListBean bean) {
                    //默认第一个被选中
                    TextView tv = (TextView) mInflater.inflate(R.layout.tv, tag2, false);
                    tv.setText(bean.getVipPriceList().get(0).getEffectiveTime() + "\n" + bean
                            .getVipPriceList().get(0).getPrice());
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
            tag2.setAdapter(tagAdapter2 = new TagAdapter<VipBean.VipTypeListBean>(vipBean
                    .getVipTypeList()) {
                @Override
                public View getView(FlowLayout parent, int position, VipBean.VipTypeListBean bean) {
                    //默认第一个被选中
                    TextView tv = (TextView) mInflater.inflate(R.layout.tv, tag2, false);
                    tv.setText(bean.getVipPriceList().get(position).getEffectiveTime() + "\n" + bean
                            .getVipPriceList().get(0).getPrice());
                    return tv;
                }
            });
        }
        return false;
    }
}
