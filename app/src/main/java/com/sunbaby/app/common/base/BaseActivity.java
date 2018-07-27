package com.sunbaby.app.common.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunbaby.app.R;
import com.sunbaby.app.common.utils.ToastUtil;
import com.sunbaby.app.common.utils.statusbartils.Eyes;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 主要是来处理一些view 操作
 */
public abstract class BaseActivity extends MyBaseActivity implements View.OnClickListener {
    protected final String TAG = this.getClass().getSimpleName();
    public Context mContext;
    public TextView tvTitle;
    private LayoutInflater inflater;
    private FrameLayout flContent;
    private FrameLayout flTitle;
    private FrameLayout flBack;
    private TextView tvRight;
    private ImageView iv_right;
    private Button button_retry;
    private Unbinder mUnbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.base_title);
        Eyes.setStatusBarLightMode(this, Color.WHITE);
        initView();
    }

    private void initView() {
        flContent = findViewById(R.id.fl_content);
        flTitle = findViewById(R.id.fl_title);
        flBack = findViewById(R.id.fl_back);
        iv_right = findViewById(R.id.iv_right);
        tvTitle = findViewById(R.id.tv_title);
        tvRight = findViewById(R.id.tv_right);
        findViewById(R.id.fl_back).setOnClickListener(this);
        findViewById(R.id.fl_right).setOnClickListener(this);
        inflater = LayoutInflater.from(this);
    }


    /**
     * 重写设置Activity布局文件
     *
     * @param layoutId activity引用的布局
     */
    public void setLayout(int layoutId) {
        flContent.removeAllViews();
        View view = inflater.inflate(layoutId, null);
        flContent.addView(view);
        mUnbinder = ButterKnife.bind(this);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setBackLayoutVisiable(boolean visiable) {
        if (!visiable) {
            flBack.setVisibility(View.GONE);
        }
    }

    public void setTitleLayoutVisiable(boolean visiable) {
        if (!visiable) {
            flTitle.setVisibility(View.GONE);
        }
    }

    public void setRightText(String more) {
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(more);
        if (TextUtils.isEmpty(more)) {
            findViewById(R.id.fl_right).setOnClickListener(null);
        } else {
            findViewById(R.id.fl_right).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fl_back) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context
                    .INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
            }
            finish();
        } else if (view.getId() == R.id.fl_right) {
            //更多
            onRightLisenter();
        }
    }

    /**
     * 公共出右侧按键点击事件
     */
    public void onRightLisenter() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    public void showToast(String msg) {
        ToastUtil.showMessage(msg);
    }
}
