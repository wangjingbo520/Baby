package com.sunbaby.app.common.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunbaby.app.R;

/**
 * @author 王静波
 * @date 2018/7/9
 * describe
 */
public class SavePicPopWindow extends BasePopWindow implements View.OnClickListener {
    private Context context;
    private View conentView;

    private TextView tvTep1;
    private TextView tvTep2;
    private TextView tvTep3;


    private View.OnClickListener itemsOnClick;

    public SavePicPopWindow(Context context, View.OnClickListener itemsOnClick) {
        super(context);
        this.context = context;
        this.itemsOnClick = itemsOnClick;
        initView();
        initData();
        addListener();
    }

    private void addListener() {
        tvTep1.setOnClickListener(itemsOnClick);
        tvTep2.setOnClickListener(itemsOnClick);
        tvTep3.setOnClickListener(this);
    }

    private void initData() {

    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.v_save_pic, null);
        tvTep1 = (TextView) conentView.findViewById(R.id.btn_take_photo);
        tvTep2 = (TextView) conentView.findViewById(R.id.btn_pick_photo);
        tvTep3 = (TextView) conentView.findViewById(R.id.tv_tep3);
        setContentView(conentView);
//        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        setWidth(context.getResources().getDimensionPixelOffset(R.dimen.dp_355));
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        // 设置弹出窗体可点击
        this.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        this.setBackgroundDrawable(dw);
        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.pop_bot_style);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    @Override
    protected void onsetCancle() {
        super.onsetCancle();
        if (mOnCancelCallbck != null) {
            mOnCancelCallbck.onCancelCallback();
        }
    }

    private OnCancelCallbck mOnCancelCallbck;

    public interface OnCancelCallbck {
        public void onCancelCallback();
    }

    public void setmOnCancelCallbck(OnCancelCallbck mOnCancelCallbck) {
        this.mOnCancelCallbck = mOnCancelCallbck;
    }
}
