package com.sunbaby.app.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunbaby.app.EventbusConstant;
import com.sunbaby.app.R;
import com.sunbaby.app.bean.PesisongBean;
import com.sunbaby.app.common.api.ProgressSubscriber;
import com.sunbaby.app.common.api.RequestClient;
import com.sunbaby.app.common.utils.NDialog;
import com.sunbaby.app.common.utils.ToastUtil;
import com.sunbaby.app.event.EventMessage;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @author Wangjingbo
 * @date 2018/7/24
 * describe
 */
public class PeisongListViewAdapter extends BaseAdapter {

    private Context context;
    private List<PesisongBean.ListBean> listBeans;
    private NDialog alertDialog;
    private int tushuMax;
    private int wanjuMax;

    public PeisongListViewAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<PesisongBean.ListBean> listBeans, int tushuMax, int wanjuMax) {
        this.listBeans = listBeans;
        this.tushuMax = tushuMax;
        this.wanjuMax = wanjuMax;
        notifyDataSetChanged();
        alertDialog = new NDialog(context);
    }

    @Override
    public int getCount() {
        return listBeans == null ? 0 : listBeans.size();
    }

    @Override
    public PesisongBean.ListBean getItem(int arg0) {
        return listBeans.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(final int arg0, View arg1, ViewGroup arg2) {
        final ViewHolder holder;
        if (arg1 == null) {
            holder = new ViewHolder();
            arg1 = View.inflate(context, R.layout.item_list_peisong, null);
            holder.ivDelete = arg1.findViewById(R.id.ivDelete);
            holder.tvNumber = arg1.findViewById(R.id.tvNumber);
            holder.tvName = arg1.findViewById(R.id.tvName);
            holder.ivDeleteNumber = arg1.findViewById(R.id.ivDeleteNumber);
            holder.ivAddNumber = arg1.findViewById(R.id.ivAddNumber);
            arg1.setTag(holder);
        } else {
            holder = (ViewHolder) arg1.getTag();
        }

        holder.tvNumber.setText(getItem(arg0).getGoods_num() + "");
        holder.tvName.setText(getItem(arg0).getGoods_name());
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  删除配送箱商品
                delete(arg0);
            }
        });

        holder.ivDeleteNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int index = arg0;
                if (listBeans.get(index).getGoods_num() <= 1) {
                    delete(index);
                } else {
                    listBeans.get(index).setGoods_num(listBeans.get(index).getGoods_num() - 1);
                    notifyDataSetChanged();
                }
            }
        });

        holder.ivAddNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = arg0;
                int type = listBeans.get(index).getGoods_type_id();
                if (1 == type) {
                    //图书
                    if (listBeans.get(index).getGoods_num() < tushuMax) {
                        listBeans.get(index).setGoods_num(listBeans.get(index).getGoods_num() + 1);
                    } else {
                        ToastUtil.showMessage("图书已达到最大的数量");
                    }
                } else {
                    //玩具
                    if (listBeans.get(index).getGoods_num() < wanjuMax) {
                        listBeans.get(index).setGoods_num(listBeans.get(index).getGoods_num() + 1);
                    } else {
                        ToastUtil.showMessage("玩具已达到最大的数量");
                    }
                }
                notifyDataSetChanged();
            }
        });
        return arg1;
    }

    private void delete(final int position) {
        alertDialog.setTitleSize(20)
                .setTitle("提示")
                .setMessage("确认要删除吗?")
                .setTitleCenter(false)
                .setMessageCenter(false)
                .setMessageSize(18)
                .setMessageColor(ContextCompat.getColor(context, R.color.textColor3))
                .setNegativeTextColor(ContextCompat.getColor(context, R.color.textColor3))
                .setPositiveTextColor(ContextCompat.getColor(context, R.color.textColor3))
                .setButtonCenter(false)
                .setButtonSize(18)
                .setCancleable(true)
                .setOnConfirmListener(new NDialog.OnConfirmListener() {
                    @Override
                    public void onClick(int which) {
                        //which,0代表NegativeButton，1代表PositiveButton
                        if (1 == which) {
                            RequestClient.getInstance().deleteDispatching("1", listBeans.get
                                    (position).getId() + "")
                                    .subscribe(new ProgressSubscriber<Object>
                                            (context) {
                                        @Override
                                        public void onNext(Object object) {
                                            listBeans.remove(getItem(position));
                                            notifyDataSetChanged();
                                            //没有数据的时候展示无数据视图
                                            if (listBeans.size() < 1) {
                                                EventBus.getDefault().post(new EventMessage
                                                        (EventbusConstant.PEISONG_FRAGMENT));
                                            }
                                        }
                                    });
                        }
                    }
                });
        alertDialog.create(NDialog.CONFIRM).show();
    }

    static class ViewHolder {
        ImageView ivDelete;
        TextView tvName;
        TextView tvNumber;
        ImageView ivDeleteNumber;
        ImageView ivAddNumber;
    }

    public List<PesisongBean.ListBean> getDatas() {
        return listBeans;
    }
}