package com.sunbaby.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunbaby.app.R;
import com.sunbaby.app.bean.SongQuhuoBean;
import com.sunbaby.app.common.utils.GlideImageLoader;

import java.util.List;

/**
 * @author Wangjingbo
 * @date 2018/8/2
 * describe
 */
public class QHAdapter extends BaseAdapter {
    private Context context;
    private List<SongQuhuoBean.ListBean> list;

    public QHAdapter(Context context, List<SongQuhuoBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public SongQuhuoBean.ListBean getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(final int position, View arg1, ViewGroup arg2) {
        ViewHolder holder = null;
        if (arg1 == null) {
            holder = new ViewHolder();
            arg1 = View.inflate(context, R.layout.listview_quhuo, null);
            holder.tvOrderNumber = arg1.findViewById(R.id.tvOrderNumber);
            holder.tvStatus = arg1.findViewById(R.id.tvStatus);
            holder.ivPic = arg1.findViewById(R.id.ivPic);
            holder.tvName = arg1.findViewById(R.id.tvName);
            arg1.setTag(holder);
        } else {
            holder = (ViewHolder) arg1.getTag();
        }

        GlideImageLoader.loadImage(context, getItem(position).getPic_url(), holder.ivPic);
        holder.tvName.setText(getItem(position).getGoods_name());
        holder.tvOrderNumber.setText("订单号: " + getItem(position).getOrder_num());

        int delivery_status = getItem(position).getDelivery_status();
        switch (delivery_status) {
            case 4:
                holder.tvStatus.setText("待取货");
                break;
            case 7:
                holder.tvStatus.setText("已取货");
                break;
            default:
                break;
        }

        return arg1;
    }

    static class ViewHolder {
        ImageView ivPic;
        TextView tvName;
        TextView tvOrderNumber;
        TextView tvStatus;

    }
}