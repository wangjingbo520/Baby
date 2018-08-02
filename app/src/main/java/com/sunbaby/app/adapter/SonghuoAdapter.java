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

import java.util.List;

/**
 * @author Wangjingbo
 * @date 2018/8/2
 * describe
 */
public class SonghuoAdapter extends BaseAdapter {
    private Context context;
    private List<SongQuhuoBean> list;

    public SonghuoAdapter(Context context, List<SongQuhuoBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
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
            holder.radioButton = arg1.findViewById(R.id.rb);
            holder.ivPic = arg1.findViewById(R.id.ivPic);
            holder.tvName = arg1.findViewById(R.id.tvName);
            arg1.setTag(holder);
        } else {
            holder = (ViewHolder) arg1.getTag();
        }
        return arg1;
    }

    static class ViewHolder {
        ImageView ivPic;
        TextView tvName;
        CheckBox radioButton;
    }
}