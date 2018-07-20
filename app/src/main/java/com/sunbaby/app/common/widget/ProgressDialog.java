package com.sunbaby.app.common.widget;

/**
 * @author 王静波
 * @date 2018/7/20
 * describe
 */

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sunbaby.app.R;

public class ProgressDialog extends AlertDialog {
    private Context context;
    private LayoutInflater inflater;

    private TextView tvDes;

    public ProgressDialog(Context context) {
        super(context, R.style.dialog);
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.progress_dialog, null);
        tvDes = (TextView) view.findViewById(R.id.tvDes);
        setContentView(view);
        setCanceledOnTouchOutside(false);
    }

    public void dismiss() {
        if (isShowing()) {
            super.dismiss();
        }
    }

    public void setDes(String des) {
        tvDes.setText("" + des);
    }
}

