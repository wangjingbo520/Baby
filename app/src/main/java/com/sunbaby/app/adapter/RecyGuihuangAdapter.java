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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 王静波
 * @date 2018/7/10
 * describe
 */
public class RecyGuihuangAdapter extends RecyclerView.Adapter<RecyGuihuangAdapter.ViewHolder> {

    private int secret = 0;
    private String title = "";
    private Context context;
    private List<GuihuangBean> mMyLiveList;
    private OnCheckBoxClickListener onCheckBoxClickListener;
    private OnItemClickListener onItemClickListener;

    public RecyGuihuangAdapter(Context context) {
        this.context = context;
    }

    public void notifyAdapter(List<GuihuangBean> myLiveList, boolean isAdd) {
        if (!isAdd) {
            this.mMyLiveList = myLiveList;
        } else {
            this.mMyLiveList.addAll(myLiveList);
        }
        notifyDataSetChanged();
    }

    public List<GuihuangBean> getMyLiveList() {
        if (mMyLiveList == null) {
            mMyLiveList = new ArrayList<>();
        }
        return mMyLiveList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .recy_item_daiguihuang, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mMyLiveList.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GuihuangBean guihuangBean = mMyLiveList.get(holder.getAdapterPosition());
        holder.checkBox.setVisibility(View.VISIBLE);
        if (guihuangBean.isSelect()) {
            holder.checkBox.setImageResource(R.mipmap.icon_chk_s);
        } else {
            holder.checkBox.setImageResource(R.mipmap.icon_chk_n);
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCheckBoxClickListener != null) {
                    onCheckBoxClickListener.onCheckboxClickListener(holder.getAdapterPosition(),
                            mMyLiveList);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClickListener(holder.getAdapterPosition(),
                            mMyLiveList);
                }
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnCheckBoxClickListener(OnCheckBoxClickListener onCheckBoxClickListener) {
        this.onCheckBoxClickListener = onCheckBoxClickListener;
    }


    public interface OnCheckBoxClickListener {
        void onCheckboxClickListener(int pos, List<GuihuangBean> myLiveList);
    }

    public interface OnItemClickListener {
        void onItemClickListener(int pos, List<GuihuangBean> myLiveList);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.check_box)
        ImageView checkBox;
        @BindView(R.id.iv_picture)
        ImageView ivPicture;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tvOrder)
        TextView tvOrder;
        @BindView(R.id.tvDaiguihuang)
        TextView tvDaiguihuang;
        @BindView(R.id.tvShenqing)
        TextView tvShenqing;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

