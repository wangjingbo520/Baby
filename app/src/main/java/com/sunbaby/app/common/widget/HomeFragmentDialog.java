package com.sunbaby.app.common.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.sunbaby.app.R;

/**
 * @author 王静波
 * @date 2018/7/11
 * describe
 */
public class HomeFragmentDialog extends Dialog implements View.OnClickListener {
    private TextView tvTitle;
    private TextView tv_content;
    private DialogCallk dialogCallk;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvLeft:
                if (dialogCallk != null) {
                    dialogCallk.position(0);
                }
                dismiss();
                break;
            case R.id.tvRight:
                if (dialogCallk != null) {
                    dialogCallk.position(1);
                }
                dismiss();
                break;
            default:
                break;
        }
    }

    public interface DialogCallk {
        void position(int postion);
    }

    public HomeFragmentDialog(Context context, DialogCallk dialogCallk, String title, String
            content) {
        super(context, R.style.CustomDialog);
        this.dialogCallk = dialogCallk;
        bindView(title, content);
    }

    private void bindView(String title, String content) {
        setContentView(R.layout.dialog_homefragment);
        findViewById(R.id.tvRight).setOnClickListener(this);
        findViewById(R.id.tvLeft).setOnClickListener(this);
    }

}

