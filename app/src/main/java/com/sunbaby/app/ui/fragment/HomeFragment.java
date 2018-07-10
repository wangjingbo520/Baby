package com.sunbaby.app.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseFragment;
import com.sunbaby.app.ui.activity.ManageAddressActivity;

import butterknife.OnClick;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe  首页
 */
public class HomeFragment extends BaseFragment {

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

    }



    public void onViewClicked(View view) {
        switch (view.getId()) {
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
            default:
                break;
        }

    }
}
