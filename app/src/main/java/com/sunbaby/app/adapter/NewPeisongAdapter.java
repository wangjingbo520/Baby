package com.sunbaby.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunbaby.app.R;
import com.sunbaby.app.bean.GuihuangBean;
import com.sunbaby.app.bean.PesisongBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 王静波
 * @date 2018/7/23
 * describe
 */
public class NewPeisongAdapter extends RecyclerView.Adapter<NewPeisongAdapter.ViewHolder> {

    private Context context;
    private List<PesisongBean.ListBean> pesisongBeans;
    private OnOnDeleteClickListener onItemClickListener;

    public NewPeisongAdapter(Context context, List<PesisongBean.ListBean> pesisongBeans) {
        this.context = context;
        this.pesisongBeans = pesisongBeans;
    }

    public void notifiItemDete(int position){
        pesisongBeans.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .item_list_peisong, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return pesisongBeans.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final PesisongBean.ListBean guihuangBean = pesisongBeans.get(holder.getAdapterPosition());
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemDeleteListener(holder.getAdapterPosition(),
                            guihuangBean);
                }
            }
        });
    }

    public void setOnDeleteListenerClickListener(OnOnDeleteClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnOnDeleteClickListener {
        void onItemDeleteListener(int pos, PesisongBean.ListBean listBean);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivDelete)
        ImageView ivDelete;
        @BindView(R.id.tvName)
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

