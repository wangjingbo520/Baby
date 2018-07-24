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
 * @author 王静波
 * @date 2018/7/11
 * describe
 */
public class HomeFragmentDialog extends Dialog implements View.OnClickListener {
    private TextView tvName;
    private TextView tvContent;
    private DialogCallk dialogCallk;
    private Context context;
    private ImageView ivPic;
    private QueryGoodsByRandBean queryGoodsByRandBean;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvLeft:
                if (dialogCallk != null&&queryGoodsByRandBean!=null) {
                    dialogCallk.position(0,queryGoodsByRandBean);
                }
                dismiss();
                break;
            case R.id.tvRight:
                if (dialogCallk != null&&queryGoodsByRandBean!=null) {
                    dialogCallk.position(1,queryGoodsByRandBean);
                }
                dismiss();
                break;
            default:
                break;
        }
    }

    public void setData(QueryGoodsByRandBean queryGoodsByRandBean) {
        this.queryGoodsByRandBean = queryGoodsByRandBean;
        tvName.setText(queryGoodsByRandBean.getGoods_name());
        GlideImageLoader.loadImage(context, queryGoodsByRandBean.getPic_url(), ivPic);
    }

    public interface DialogCallk {
        void position(int postion,QueryGoodsByRandBean queryGoodsByRandBean);
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
        tvName = findViewById(R.id.tvName);
        ivPic = findViewById(R.id.ivPic);
        tvContent = findViewById(R.id.tvContent);
        //   tvContent.setText(content);
    }

}

