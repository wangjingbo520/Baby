package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.libray.basetools.view.imageview.CircleImageView;
import com.sunbaby.app.R;
import com.sunbaby.app.bean.PersonBean;
import com.sunbaby.app.callback.IPersonView;
import com.sunbaby.app.common.base.BaseCameraActivity;
import com.sunbaby.app.common.utils.GlideImageLoader;
import com.sunbaby.app.presenter.PersonPresenter;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author 王静波
 * @date 2018/7/6
 * describe 个人资料
 */
public class PersonActivity extends BaseCameraActivity implements IPersonView {
    @BindView(R.id.ivUser)
    CircleImageView ivUser;
    @BindView(R.id.tvNichen)
    TextView tvNichen;
    @BindView(R.id.tvPhoneNumber)
    TextView tvPhoneNumber;
    @BindView(R.id.tvSex)
    TextView tvSex;
    private PersonPresenter personPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("个人资料");
        personPresenter = new PersonPresenter(mContext, this);
        personPresenter.personalData(getUser().getUserId());
    }

    @OnClick({R.id.llTouxiang, R.id.llPassword, R.id.llPhoneNumber})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.llTouxiang:
                showCameraPopwindow(this.getWindow().getDecorView(), false, false);
                break;
            case R.id.llPassword:
                UpdatePaswordActivity.start(this);
                break;
            case R.id.llPhoneNumber:
                startTo(UpdatePhoneNumberActivity.class, false);
                break;
            default:
                break;
        }
    }

    @Override
    public void personalData(PersonBean personBean) {
        tvNichen.setText(personBean.getUserName());
        tvPhoneNumber.setText(personBean.getMobile());
        GlideImageLoader.loadImage(mContext, personBean.getPhoto(), ivUser);
        tvSex.setText(personBean.getSex());
    }
}
