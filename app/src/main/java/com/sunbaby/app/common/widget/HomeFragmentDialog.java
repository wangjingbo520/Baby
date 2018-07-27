package com.sunbaby.app.common.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunbaby.app.R;
import com.sunbaby.app.bean.QueryGoodsByRandBean;
import com.sunbaby.app.common.utils.GlideImageLoader;

/**
 * @author wangjingbo
 * @date 2018/7/11
 * describe
 */
public class HomeFragmentDialog extends Dialog implements View.OnClickListener {
    private TextView tvName;
    private DialogCallk dialogCallk;
    private Context context;
    private ImageView ivPic;
    private QueryGoodsByRandBean queryGoodsByRandBean;
    /**
     * 1 图书 2 玩具
     */
    private String type;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvLeft:
                if (dialogCallk != null && queryGoodsByRandBean != null) {
                    dialogCallk.position(0, queryGoodsByRandBean, type);
                }
                break;
            case R.id.tvRight:
                if (dialogCallk != null && queryGoodsByRandBean != null) {
                    dialogCallk.position(1, queryGoodsByRandBean, type);
                }
                dismiss();
                break;
            case R.id.ivDismiss:
                dismiss();
                break;
            default:
                break;
        }
    }

    public void setData(QueryGoodsByRandBean queryGoodsByRandBean, String type) {
        this.queryGoodsByRandBean = queryGoodsByRandBean;
        this.type = type;
        tvName.setText(queryGoodsByRandBean.getGoods_name());
        GlideImageLoader.loadImage(context, queryGoodsByRandBean.getPic_url(), ivPic);
    }

    public interface DialogCallk {
        void position(int postion, QueryGoodsByRandBean queryGoodsByRandBean, String type);
    }

    public HomeFragmentDialog(Context context, DialogCallk dialogCallk, String title, String
            content) {
        super(context, R.style.CustomDialog);
        this.dialogCallk = dialogCallk;
        this.context = context;
        bindView(title, content);
    }

    private void bindView(String title, String content) {
        setContentView(R.layout.dialog_homefragment);
        findViewById(R.id.tvRight).setOnClickListener(this);
        findViewById(R.id.tvLeft).setOnClickListener(this);
        findViewById(R.id.ivDismiss).setOnClickListener(this);
        tvName = findViewById(R.id.tvName);
        ivPic = findViewById(R.id.ivPic);
    }

}

