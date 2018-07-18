package com.sunbaby.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sunbaby.app.R;
import com.sunbaby.app.WebViewActivity;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.widget.floawlayout.FlowLayout;
import com.sunbaby.app.common.widget.floawlayout.TagAdapter;
import com.sunbaby.app.common.widget.floawlayout.TagFlowLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 加入会员2
 */
public class JoinmemberActivity2 extends BaseActivity {

    @BindView(R.id.tag1)
    TagFlowLayout tag1;
    @BindView(R.id.tag2)
    TagFlowLayout tag2;

    private TagAdapter<String> mAdapter;
    private LayoutInflater mInflater;

    private String[] mVals1 = new String[]{"图书会员", "玩具会员", "总会员"};
    private String[] mVals2 = new String[]{"1年（￥500）", "2年（￥900）", "3年（￥1300）"};


    public static void start(Context context) {
        Intent starter = new Intent(context, JoinmemberActivity2.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("加入会员");
        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_joinmember2;
    }

    private void initData() {
        tag1.setAdapter(mAdapter = new TagAdapter<String>(mVals1) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv, tag1, false);
                tv.setText(s);
                return tv;
            }
        });

        tag2.setAdapter(mAdapter = new TagAdapter<String>(mVals2) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv, tag2, false);
                tv.setText(s);
                return tv;
            }
        });
    }

    @OnClick(R.id.btnKaitong)
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnKaitong:
                WebViewActivity.start(mContext, "http://www.baidu.com");
                break;
            default:
                break;
        }
    }
}
