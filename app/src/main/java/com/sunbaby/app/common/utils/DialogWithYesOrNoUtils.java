package com.sunbaby.app.common.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.sunbaby.app.R;

/**
 * @author Wangjingbo
 * @date 2018/7/24
 * describe
 */
public class DialogWithYesOrNoUtils {
    private static NDialog nDialog = null;

    public static void showDialog(Context context, String content, final DialogCallBack callBack) {
        if (nDialog == null) {
            nDialog = new NDialog(context);
            nDialog.setTitleSize(20)
                    .setTitle("提示")
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
                                callBack.exectEvent();
                            }
                        }
                    });
        }
        nDialog.setMessage(content);
        nDialog.create(NDialog.CONFIRM).show();
    }

    public interface DialogCallBack {
        void exectEvent();
    }

}
