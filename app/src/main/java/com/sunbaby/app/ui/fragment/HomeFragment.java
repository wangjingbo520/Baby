package com.sunbaby.app.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseFragment;
import com.sunbaby.app.common.widget.HomeFragmentDialog;
import com.sunbaby.app.ui.activity.ClassificationActivity;
import com.sunbaby.app.ui.activity.LoginActivity;
import com.sunbaby.app.ui.activity.RegisterActivity;

import butterknife.OnClick;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe  首页
 */
public class HomeFragment extends BaseFragment implements HomeFragmentDialog.DialogCallk {

    private HomeFragmentDialog homeFragmentDialog;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        homeFragmentDialog = new HomeFragmentDialog(mContext, this, "", "");
    }

    @OnClick({R.id.tvLogin, R.id.tvRegister, R.id.llSuiji, R.id.llFenlei})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvLogin:
                //登录
                startActivity(new Intent(mContext, LoginActivity.class));
                mContext.finish();
                break;
            case R.id.tvRegister:
                //注册
                startActivity(new Intent(mContext, RegisterActivity.class));
                mContext.finish();
                break;
            case R.id.llSuiji:
                //随机
                homeFragmentDialog.show();
                break;
            case R.id.llFenlei:
                //图书分类查看
                ClassificationActivity.start(mContext);
                break;
            default:
                break;
        }
    }

    @Override
    public void position(int position) {
        if (0 == position) {
            //继续随机
        } else {
            //加入配送箱
        }

    }

//    {R.id.tvLogin, R.id.tvRegister}
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.tvLogin:
//                break;
//            case R.id.tvRegister:
//                break;
//            case R.id.tvOrder:
    //  startActivity(new Intent(mContext, MyOrderActivity.class));
    //  startActivity(new Intent(mContext, PayActivity.class));
    //   startActivity(new Intent(mContext, SearchActivity.class));
    //      startActivity(new Intent(mContext, ClassificationActivity.class));
//                startActivity(new Intent(mContext, ManageAddressActivity.class));
//                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//                builder.setTitle("提示");
//                builder.setMessage("清除成功!");
//                builder.setCancelable(false);
//                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        ToastUtil.show("hahhaha");
//                    }
//                });
//
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
    //        break;
//            default:
//                break;
//        }
//    }
//
}
