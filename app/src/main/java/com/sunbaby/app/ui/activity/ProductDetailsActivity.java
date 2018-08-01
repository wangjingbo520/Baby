package com.sunbaby.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.sunbaby.app.EventbusConstant;
import com.sunbaby.app.MainActivity;
import com.sunbaby.app.R;
import com.sunbaby.app.bean.ProductBean;
import com.sunbaby.app.callback.IProductView;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.utils.NDialog;
import com.sunbaby.app.event.EventMessage;
import com.sunbaby.app.presenter.ProductPresenter;
import com.sunbaby.app.ui.fragment.product.ProductDetailFragment;
import com.sunbaby.app.ui.fragment.product.ProductFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 商品详情
 */
public class ProductDetailsActivity extends BaseActivity implements IProductView {

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
    private String goods_id = "";

    private ProductPresenter productPresenter;


    public static void start(Context context, String goods_id) {
        Intent starter = new Intent(context, ProductDetailsActivity.class);
        starter.putExtra("goods_id", goods_id);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_product_details);
        setTitle("商品详情");
        goods_id = getIntent().getStringExtra("goods_id");
        alertDialog = new NDialog(mContext);
        productPresenter = new ProductPresenter(mContext, this);
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
                if (userIsLogin(false)) {
                    if (!TextUtils.isEmpty(goods_id)) {
                        addPeisong();
                    }
                }
                break;
            default:
                break;
        }
    }

    private void addPeisong() {
        alertDialog.setTitleSize(20)
                .setTitle("提示")
                .setMessage("确认要添加到配送箱吗?")
                .setTitleCenter(false)
                .setMessageCenter(false)
                .setMessageSize(18)
                .setMessageColor(ContextCompat.getColor(mContext, R.color.textColor3))
                .setNegativeTextColor(ContextCompat.getColor(mContext, R.color.textColor3))
                .setPositiveTextColor(ContextCompat.getColor(mContext, R.color.textColor3))
                .setButtonCenter(false)
                .setButtonSize(18)
                .setCancleable(true)
                .setOnConfirmListener(new NDialog.OnConfirmListener() {
                    @Override
                    public void onClick(int which) {
                        //which,0代表NegativeButton，1代表PositiveButton
                        if (1 == which) {
                            productPresenter.joinDistributionBox(goods_id);
                        }
                    }
                });
        alertDialog.create(NDialog.CONFIRM).show();
    }

    private void initFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (index) {
            case 0:
                if (productFragment == null) {
                    productFragment = ProductFragment.newInstance(goods_id);
                    transaction.add(R.id.main_frame, productFragment);
                } else {
                    transaction.show(productFragment);
                }
                break;
            case 1:
                if (productDetailFragment == null) {
                    productDetailFragment = ProductDetailFragment.newInstance(goods_id);
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

    @Override
    public void queryGoodsDetails(ProductBean productBean) {

    }

    @Override
    public void joinDistributionBox(Object object) {
        //添加到配送箱
        EventBus.getDefault().post(new EventMessage(EventbusConstant.PEISONG_FRAGMENT));
        showToast("恭喜您,已成功添加到配送箱");
    }

}
