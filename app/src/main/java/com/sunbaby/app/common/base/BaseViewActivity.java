package com.sunbaby.app.common.base;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.sunbaby.app.common.utils.ToastUtil;

/**
 * @author 王静波
 * @date 2018/7/16
 * describe
 */
public class BaseViewActivity extends BaseActivity {
    private AlertDialog alertDialog;
    private int checkedItemId = -1;

    /**
     * 展示单选列表Dialog
     */
    public void showSingleListDialog(String value) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(value);
        builder.setIcon(android.R.drawable.btn_star);
        final String[] addres = {"湖南", "广东", "山东", "广东", "山东", "广东", "山东", "广东", "山东", "广东",
                "山东", "广东", "山东", "广东", "山东", "广东", "山东", "广东", "山东", "广东", "山东", "广东", "山东",
                "广东", "山东", "广东", "山东", "广东", "山东", "广东", "山东"};
        builder.setSingleChoiceItems(addres, 0, new DialogInterface
                .OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                checkedItemId = i;
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ToastUtil.showMessage(addres[checkedItemId]);

            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }




}
