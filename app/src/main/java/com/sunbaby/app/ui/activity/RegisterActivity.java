package com.sunbaby.app.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sunbaby.app.R;
import com.sunbaby.app.bean.Areabean;
import com.sunbaby.app.bean.YouerYuan;
import com.sunbaby.app.callback.IRegisterView;
import com.sunbaby.app.common.base.BaseViewActivity;
import com.sunbaby.app.common.utils.ToastUtil;
import com.sunbaby.app.common.widget.CommomDialog;
import com.sunbaby.app.common.widget.wheel.widget.WheelViewDialog;
import com.sunbaby.app.presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe 注册
 */
public class RegisterActivity extends BaseViewActivity implements CommomDialog.DialogCallk,
        IRegisterView, WheelViewDialog.OnDialogItemClickListener {
    @BindView(R.id.etPhoneNumber)
    EditText etPhoneNumber;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.tvGetCode)
    TextView tvGetCode;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etSurePassword)
    EditText etSurePassword;
    @BindView(R.id.etChildName)
    EditText etChildName;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.etDetailAdress)
    EditText etDetailAdress;
    private CommomDialog commomDialog;
    private RegisterPresenter registerPresenter;
    private WheelViewDialog dialog;
    private int type = 0;
    /**
     * 省id
     */
    private String provinceId;
    //
    /**
     * 市id
     */
    private String citId;
    /**
     * 区id
     */
    private String district;

    /**
     * 幼儿园名字
     */
    private String kindergartenName;
    /**
     * 班级
     */
    private String kindergartenClass;
    /**
     * 班级id
     */
    private String areaId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("注册");
        registerPresenter = new RegisterPresenter(this, this);
        dialog = new WheelViewDialog(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }


    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.btnRegister, R.id.tvGetCode})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvGetCode:
                //获取验证码
                getCode();
                break;
            case R.id.tv1:
                //省
                type = 0;
                citId = "";
                district = "";
                kindergartenName = "";
                kindergartenClass = "";
                areaId = "";
                etDetailAdress.setText("");
                tv2.setText("");
                tv3.setText("");
                tv4.setText("");
                tv5.setText("");
                registerPresenter.regionList("0", "0");
                break;
            case R.id.tv2:
                //市
                if (!TextUtils.isEmpty(provinceId + "")) {
                    type = 1;
                    district = "";
                    kindergartenName = "";
                    kindergartenClass = "";
                    areaId = "";
                    etDetailAdress.setText("");
                    tv3.setText("");
                    tv4.setText("");
                    tv5.setText("");
                    registerPresenter.regionList("1", provinceId + "");
                } else {
                    ToastUtil.showMessage("请先选择省");
                }
                break;
            case R.id.tv3:
                //区
                if (!TextUtils.isEmpty(citId + "")) {
                    type = 2;
                    kindergartenName = "";
                    kindergartenClass = "";
                    areaId = "";
                    etDetailAdress.setText("");
                    tv3.setText("");
                    tv4.setText("");
                    tv5.setText("");
                    registerPresenter.regionList("2", district + "");
                } else {
                    ToastUtil.showMessage("请先选择市");
                }
                break;
            case R.id.tv4:
                //幼儿园名字
                if (!TextUtils.isEmpty(district + "")) {
                    type = 3;
                    kindergartenClass = "";
                    areaId = "";
                    etDetailAdress.setText("");
                    registerPresenter.kindergarten("1", district + "", "");
                } else {
                    ToastUtil.showMessage("请先选择您所在的区");
                }
                break;
            case R.id.tv5:
                //班级
                if (!TextUtils.isEmpty(kindergartenName)) {
                    type = 4;
                    etDetailAdress.setText("");
                    registerPresenter.kindergarten("2", "", kindergartenName);
                } else {
                    ToastUtil.showMessage("请先选择您所在的幼儿园");
                }
                break;
            case R.id.btnRegister:
                //提交注册
                register();
            //    startTo(JoinmemberActivity.class, false);
                break;
            default:
                break;
        }
    }

    private void getCode() {
        String mobile = etPhoneNumber.getText().toString();
        if (TextUtils.isEmpty(mobile)) {
            showToast("请先输入手机号码");
            return;
        }
        registerPresenter.sendSms(mobile, "0");
    }

    @Override
    public void sure() {
        //注册成功,点击跳转
        startTo(LoginActivity.class, true);
    }

    @Override
    public void onGetCodeSucceed() {
        //获取验证码成功
        ToastUtil.showMessage("短信已发送,请查收");
    }

    @Override
    public void regionList(Areabean areabeans) {
        if (0 == type) {
            //省
            showAreaDialog("省", areabeans);
        } else if (1 == type) {
            //市
            showAreaDialog("市", areabeans);
        } else if (2 == type) {
            //区
            showAreaDialog("区", areabeans);
        }
    }

    @Override
    public void kindergarten(YouerYuan youerYuan) {
        if (3 == type) {
            //幼儿园名字
            showYoueryuan("幼儿园名字", youerYuan);
        } else {
            //班级
            showYoueryuan("班级", youerYuan);
        }
    }

    @Override
    public void onRegisterSucess(Object object) {
        if (commomDialog == null) {
            commomDialog = new CommomDialog(this, this, "注册成功", "立即前往登录");
        }
        commomDialog.show();
    }

    private void showAreaDialog(String name, Areabean areabean) {
        dialog.setTitle(name).setItems(areabean.getRegionList()).setButtonText("确定")
                .setOnDialogItemClickListener(this).setDialogStyle(Color.parseColor
                ("#6699ff")).setCount(5).show();
    }


    private void showYoueryuan(String name, YouerYuan youerYuan) {
        dialog.setTitle(name).setItems(youerYuan.getRegionList()).setButtonText("确定")
                .setOnDialogItemClickListener(this).setDialogStyle(Color.parseColor
                ("#6699ff")).setCount(5).show();
    }

    @Override
    public void onItemClick(int position, Object s) {
        if (s instanceof Areabean.RegionListBean) {
            switch (type) {
                case 0:
                    //省
                    tv1.setText(((Areabean.RegionListBean) s).getName());
                    provinceId = ((Areabean.RegionListBean) s).getId() + "";
                    break;
                case 1:
                    //市
                    tv2.setText(((Areabean.RegionListBean) s).getName());
                    citId = ((Areabean.RegionListBean) s).getId() + "";
                    break;
                case 2:
                    //区
                    tv3.setText(((Areabean.RegionListBean) s).getName());
                    district = ((Areabean.RegionListBean) s).getId() + "";
                    break;
                case 3:
                    //幼儿园名称
                    tv4.setText(((Areabean.RegionListBean) s).getName());
                    kindergartenName = ((Areabean.RegionListBean) s).getName();
                    break;
                case 4:
                    //班级
                    tv5.setText(((Areabean.RegionListBean) s).getName());
                    kindergartenClass = ((Areabean.RegionListBean) s).getName();
                    areaId = ((Areabean.RegionListBean) s).getName();
                    break;
                default:
                    break;
            }
        }
    }

    private void register() {
        String mobile = etPhoneNumber.getText().toString();
        String smsCode = etCode.getText().toString();
        String passWord = etPassword.getText().toString();
        String rePassWord = etSurePassword.getText().toString();
        String userName = etChildName.getText().toString();
        String addr = etDetailAdress.getText().toString();
        if (TextUtils.isEmpty(mobile)) {
            ToastUtil.showMessage("请先输入手机号码");
            return;
        }
        if (TextUtils.isEmpty(smsCode)) {
            ToastUtil.showMessage("请先输入短信验证码");
            return;
        }

        if (TextUtils.isEmpty(passWord)) {
            ToastUtil.showMessage("短信验证码");
            return;
        }
        if (TextUtils.isEmpty(rePassWord)) {
            ToastUtil.showMessage("请再次输入密码");
            return;
        }

        if (!passWord.equals(rePassWord)) {
            ToastUtil.showMessage("两次输入密码不一致");
            return;
        }

        if (TextUtils.isEmpty(userName)) {
            showToast("请输入孩子姓名");
            return;
        }

        if (TextUtils.isEmpty(provinceId)) {
            showToast("请先选择省份");
            return;
        }

        if (TextUtils.isEmpty(citId)) {
            showToast("请先选择市区");
            return;
        }

        if (TextUtils.isEmpty(kindergartenName)) {
            showToast("请先选择幼儿园");
            return;
        }

        if (TextUtils.isEmpty(areaId)) {
            showToast("请先选择幼儿园所在的班级");
            return;
        }

        if (TextUtils.isEmpty(addr)) {
            showToast("请先输入您的详细地址");
            return;
        }
        registerPresenter.register(mobile, smsCode, passWord, rePassWord, userName, addr,
                provinceId, citId, district, areaId, kindergartenName, kindergartenClass);
    }


}
