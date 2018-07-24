package com.sunbaby.app.test;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunbaby.app.R;
import com.sunbaby.app.common.utils.statusbartils.Eyes;
import com.sunbaby.app.statusview.StatusLayoutManager;

import butterknife.Unbinder;

/**
 * @author wangjingbo
 * @date 2018/7/18
 * describe
 */
public abstract class TestBaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected final String TAG = this.getClass().getSimpleName();
    public Context mContext;
    public TextView tvTitle;
    private LayoutInflater inflater;
    private FrameLayout flContent;
    private FrameLayout flTitle;
    private FrameLayout flBack;
    private TextView tvRight;
    private ImageView iv_right;
    private Unbinder mUnbinder;
    protected StatusLayoutManager statusLayoutManager;

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
        initStatusLayout();
        flContent.addView(statusLayoutManager.getRootLayout());
    }

    protected abstract void initStatusLayout();



    /**
     * 设置标题栏标题
     *
     * @param title
     */
    public void setTitle(String title) {
        tvTitle.setText(title);
    }





}

