package com.sunbaby.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
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
    EditText tvNichen;
    @BindView(R.id.tvPhoneNumber)
    TextView tvPhoneNumber;
    @BindView(R.id.tvSex)
    TextView tvSex;

    private PersonPresenter personPresenter;

    //头像
    private String photo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("个人资料");
        personPresenter = new PersonPresenter(mContext, this);
        personPresenter.personalData(getUser().getUserId() + "");
    }

    @OnClick({R.id.llTouxiang, R.id.llPassword, R.id.llPhoneNumber, R.id.btnSave})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.llTouxiang:
                //修改头像
                showCameraPopwindow(this.getWindow().getDecorView(), false, false);
                break;
            case R.id.llPassword:
                //修改密码
                UpdatePaswordActivity.start(this);
                break;
            case R.id.llPhoneNumber:
                //修改手机号码
                startTo(UpdatePhoneNumberActivity.class, false);
                break;
            case R.id.btnSave:
                //保存
                save();
                break;
            default:
                break;
        }
    }

    private void save() {
        String userName = tvNichen.getText().toString().trim();
        //性别 0 保密 1 男 2 女
        personPresenter.updatePersonal(photo, userName, getUserId(), "");
    }

    @Override
    public void personalData(PersonBean personBean) {
        tvNichen.setText(personBean.getUserName());
        tvPhoneNumber.setText(personBean.getMobile());
        GlideImageLoader.loadImage(mContext, personBean.getPhoto(), ivUser);
        tvSex.setText(personBean.getSex());
    }

    @Override
    public void updatePersonal() {
        //修改个人资料成功
        finish();
    }

}
