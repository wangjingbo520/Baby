package com.sunbaby.app.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunbaby.app.EventbusConstant;
import com.sunbaby.app.R;
import com.sunbaby.app.bean.AdressBean;
import com.sunbaby.app.event.EventMessage;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @author wangjingbo
 * @date 2018/7/9
 * describe
 */
public class ManageAdressAdapter extends BaseQuickAdapter<AdressBean.ListBean, BaseViewHolder> {

    public ManageAdressAdapter(int layoutResId, @Nullable List<AdressBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AdressBean.ListBean item) {
        helper.addOnClickListener(R.id.radioButton);
        helper.addOnClickListener(R.id.llEditAdress);
        helper.addOnClickListener(R.id.llDeleteAdress);
    }

    /**
     * 设置默认收货地址成功  0 默认地址 1非默认地址
     */
    public void setDefaultAdress(int position) {
        List<AdressBean.ListBean> data = getData();
        for (int i = 0; i < data.size(); i++) {
            //先全部设置不被选中的状态
            if (1 == data.get(i).getStatus()) {
                data.get(i).setStatus(1);
                notifyItemChanged(i);
                break;
            }
        }
        //设置默认地址ui更新
        data.get(position).setStatus(0);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }

    public void deleteAdress(int position) {
        getData().remove(position);
        notifyDataSetChanged();
        if (getData().size() < 1) {
            //如果删除了所有地址,就发送消息提示更新ui,展示没有收货地址的ui
            EventBus.getDefault().post(new EventMessage(EventbusConstant.ADRESSMANAGE_ACTIVITY));
        }
    }

}

