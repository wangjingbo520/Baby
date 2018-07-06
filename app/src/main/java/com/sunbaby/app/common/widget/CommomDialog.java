package com.sunbaby.app.common.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.sunbaby.app.R;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe  通用
 */
public class CommomDialog extends Dialog {
    private TextView tvTitle;
    private TextView tv_content;
    private DialogCallk dialogCallk;

    public interface DialogCallk {
        void sure();
    }

    public CommomDialog(Context context, DialogCallk dialogCallk, String title, String content) {
        super(context, R.style.CustomDialog);
        this.dialogCallk = dialogCallk;
        bindView(title, content);
    }

    private void bindView(String title, String content) {
        setContentView(R.layout.dialog_common);
        tvTitle = findViewById(R.id.tvTitle);
        tv_content = findViewById(R.id.tv_content);
        tvTitle.setText(title);
        tv_content.setText(content);
        findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (dialogCallk!=null){
                    dialogCallk.sure();
                }
            }
        });
    }

}
