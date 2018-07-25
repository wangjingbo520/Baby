package com.sunbaby.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunbaby.app.R;
import com.sunbaby.app.bean.SecondGoodsListBean;
import com.sunbaby.app.common.utils.GlideImageLoader;

import java.util.List;

/**
 * @author Wangjingbo
 * @date 2018/7/25
 * describe
 */
public class SecondaryGridviewAdapter extends BaseAdapter {
    private Context context;
    private List<SecondGoodsListBean.ListBean> listBeanList;

    public SecondaryGridviewAdapter(Context context, List<SecondGoodsListBean.ListBean>
            listBeanList) {
        this.listBeanList = listBeanList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listBeanList.size();
    }

    @Override
    public SecondGoodsListBean.ListBean getItem(int arg0) {
        return listBeanList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View arg1, ViewGroup arg2) {
        ViewHolder holder = null;
        if (arg1 == null) {
            holder = new ViewHolder();
            arg1 = View.inflate(context, R.layout.recy_item_wanju, null);
            holder.ivPic = arg1.findViewById(R.id.ivPic);
            holder.tvName = arg1.findViewById(R.id.tvName);
            arg1.setTag(holder);
        } else {
            holder = (ViewHolder) arg1.getTag();
        }

        GlideImageLoader.loadImage(context, getItem(position).getPic_url(), holder.ivPic);
        holder.tvName.setText(getItem(position).getGoods_name());
        return arg1;
    }

    static class ViewHolder {
        private ImageView ivPic;
        private TextView tvName;
    }
}