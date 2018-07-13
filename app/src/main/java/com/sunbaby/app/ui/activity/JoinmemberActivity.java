package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.widget.floawlayout.FlowLayout;
import com.sunbaby.app.common.widget.floawlayout.TagAdapter;
import com.sunbaby.app.common.widget.floawlayout.TagFlowLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 加入会员
 */
public class JoinmemberActivity extends BaseActivity {

    @BindView(R.id.tag1)
    TagFlowLayout tag1;
    @BindView(R.id.tag2)
    TagFlowLayout tag2;

    private TagAdapter<String> mAdapter;
    private LayoutInflater mInflater;

    private String[] mVals1 = new String[]{"图书会员", "玩具会员", "总会员"};
    private String[] mVals2 = new String[]{"1年（￥500）", "2年（￥900）", "3年（￥1300）"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_joinmember);
        setTitle("加入会员");
        mInflater = LayoutInflater.from(mContext);
        initData();
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
                startTo(PayActivity.class, true);
                break;
            default:
                break;
        }
    }


}
