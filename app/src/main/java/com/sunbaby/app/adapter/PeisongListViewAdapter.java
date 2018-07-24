package com.sunbaby.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunbaby.app.R;
import com.sunbaby.app.bean.PesisongBean;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.api.RequestClient;
import com.sunbaby.app.common.utils.DialogWithYesOrNoUtils;
import com.sunbaby.app.common.utils.ToastUtil;

import java.util.List;

/**
 * @author Wangjingbo
 * @date 2018/7/24
 * describe
 */
public class PeisongListViewAdapter extends BaseAdapter {

    private Context context;
    private List<PesisongBean.ListBean> list;

    public PeisongListViewAdapter(Context context, List<PesisongBean.ListBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public PesisongBean.ListBean getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(final int arg0, View arg1, ViewGroup arg2) {
        ViewHolder holder = null;
        if (arg1 == null) {
            holder = new ViewHolder();
            arg1 = View.inflate(context, R.layout.item_list_peisong, null);
            holder.ivDelete = (ImageView) arg1.findViewById(R.id.ivDelete);
            holder.tvName = (TextView) arg1.findViewById(R.id.tvName);
            arg1.setTag(holder);
        } else {
            holder = (ViewHolder) arg1.getTag();
        }

        holder.tvName.setText(getItem(arg0).getGoods_name());
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  删除配送箱商品
                ToastUtil.showMessage("点击了" + arg0);
                DialogWithYesOrNoUtils.showDialog(context, "确认要删除吗?", new
                        DialogWithYesOrNoUtils.DialogCallBack() {
                            @Override
                            public void exectEvent() {
                                RequestClient.getInstance().deleteDispatching("1", getItem(arg0)
                                        .getId() + "").subscribe(new ProgressSubscriber<Object>
                                        (context) {
                                    @Override
                                    public void onNext(Object object) {
                                        ToastUtil.showMessage(arg0 + "位置");
                                        list.remove(arg0);
                                        notifyDataSetChanged();
                                    }
                                });
                            }
                        });

            }
        });

        return arg1;
    }

    static class ViewHolder {
        ImageView ivDelete;
        TextView tvName;
    }
}