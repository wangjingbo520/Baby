package com.sunbaby.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.sunbaby.app.MainActivity;
import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.ui.fragment.product.ProductDetailFragment;
import com.sunbaby.app.ui.fragment.product.ProductFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 商品详情
 */
public class ProductDetailsActivity extends BaseActivity {

    @BindView(R.id.tvLeft)
    TextView tvLeft;
    @BindView(R.id.viewLeft)
    View viewLeft;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.viewRight)
    View viewRight;
    private ProductFragment productFragment;
    private ProductDetailFragment productDetailFragment;

    public static void start(Context context) {
        Intent starter = new Intent(context, ProductDetailsActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_product_details);
        setTitle("商品详情");
        initFragment(0);
    }

    @OnClick({R.id.llLeft, R.id.llRight, R.id.tvAdd})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.llLeft:
                //商品
                initFragment(0);
                tvLeft.setTextColor(ContextCompat.getColor(this, R.color.themeColor));
                viewLeft.setVisibility(View.VISIBLE);
                viewRight.setVisibility(View.INVISIBLE);
                tvRight.setTextColor(ContextCompat.getColor(this, R.color.textColor3));
                break;
            case R.id.llRight:
                //商品详情
                initFragment(1);
                tvLeft.setTextColor(ContextCompat.getColor(this, R.color.textColor3));
                viewLeft.setVisibility(View.INVISIBLE);
                viewRight.setVisibility(View.VISIBLE);
                tvRight.setTextColor(ContextCompat.getColor(this, R.color.themeColor));
                break;
            case R.id.tvAdd:
                //加入配送
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(MainActivity.MAININDEX, "1");
                startActivity(intent);
            default:
                break;
        }
    }

    private void initFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (index) {
            case 0:
                if (productFragment == null) {
                    productFragment = ProductFragment.newInstance();
                    transaction.add(R.id.main_frame, productFragment);
                } else {
                    transaction.show(productFragment);
                }
                break;
            case 1:
                if (productDetailFragment == null) {
                    productDetailFragment = ProductDetailFragment.newInstance();
                    transaction.add(R.id.main_frame, productDetailFragment);
                } else {
                    transaction.show(productDetailFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (productFragment != null) {
            transaction.hide(productFragment);
        }
        if (productDetailFragment != null) {
            transaction.hide(productDetailFragment);
        }
    }

}
