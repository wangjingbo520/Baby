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
import com.sunbaby.app.common.utils.UIUtils;
import com.sunbaby.app.common.widget.CommomDialog;
import com.sunbaby.app.common.widget.wheel.util.WheelUtils;
import com.sunbaby.app.common.widget.wheel.widget.WheelViewDialog;
import com.sunbaby.app.presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 注册
 */
public class RegisterActivity extends BaseViewActivity implements CommomDialog.DialogCallk,
        IRegisterView {
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
    //    @BindView(R.id.tv5)
//    TextView tv5;
    @BindView(R.id.etDetailAdress)
    EditText etDetailAdress;
    private CommomDialog commomDialog;
    private RegisterPresenter registerPresenter;
    private WheelViewDialog dialog;

    /**
     * 用来判断省市区
     */
    private int type = 0;
    /**
     * 省id
     */
    private String provinceId = "";
    //
    /**
     * 市id
     */
    private String citId = "";
    /**
     * 区id
     */
    private String district = "";

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
        setLayout(R.layout.activity_register);
        setTitle("注册");
        registerPresenter = new RegisterPresenter(this, this);
        dialog = new WheelViewDialog(this);
    }

    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.btnRegister, R.id.tvGetCode})
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
                tv2.setText("选择市");
                tv3.setText("选择区");
                tv4.setText("幼儿园/班级");
                //  tv5.setText("班级");
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
                    tv3.setText("选择区");
                    tv4.setText("幼儿园/班级");
                    //    tv5.setText("班级");
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
                    tv4.setText("幼儿园/班级");
                    //  tv5.setText("班级");
                    registerPresenter.regionList("2", citId + "");
                } else {
                    ToastUtil.showMessage("请先选择市");
                }
                break;
            case R.id.tv4:
                //幼儿园名字,班级
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
//            case R.id.tv5:
//                //班级
//                if (!TextUtils.isEmpty(kindergartenName)) {
//                    type = 4;
//                    etDetailAdress.setText("");
//                    registerPresenter.kindergarten("2", "", kindergartenName);
//                } else {
//                    ToastUtil.showMessage("请先选择您所在的幼儿园");
//                }
//                break;
            case R.id.btnRegister:
                //提交注册
                register();
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
        registerPresenter.sendSms(mobile, "REGISTER_SMSCODE_SCENE");
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
            showYoueryuan("幼儿园/班级", youerYuan);
        }
//        else if (4 == type) {
//            //班级
//            showYoueryuan("班级", youerYuan);
//        }
    }

    @Override
    public void onRegisterSucess(Object object) {
        if (commomDialog == null) {
            commomDialog = new CommomDialog(this, this, "注册成功", "立即前往登录");
        }
        commomDialog.show();
    }

    private void showAreaDialog(String name, final Areabean areabean) {
        dialog.setTitle(name).setItems(areabean.getRegionList()).setButtonText("确 定")
                .setOnDialogItemClickListener(new WheelViewDialog.OnDialogItemClickListener() {
                    @Override
                    public void onItemClick(int position, Object s) {
                        switch (type) {
                            case 0:
                                //省
                                tv1.setText(areabean.getRegionList().get(position).getName());
                                provinceId = areabean.getRegionList().get(position).getId() + "";
                                break;
                            case 1:
                                //市
                                tv2.setText(areabean.getRegionList().get(position).getName());
                                citId = areabean.getRegionList().get(position).getId() + "";
                                break;
                            case 2:
                                //区
                                tv3.setText(areabean.getRegionList().get(position).getName());
                                district = areabean.getRegionList().get(position).getId() + "";
                                break;
                            default:
                                break;
                        }
                    }
                }).setDialogStyle(Color.parseColor
                ("#fcb810")).setCount(5).show();
    }


    private void showYoueryuan(String name, final YouerYuan youerYuan) {
        dialog.setTitle(name).setItems(youerYuan.getRegionList()).setButtonText("确 定")
                .setOnDialogItemClickListener(new WheelViewDialog.OnDialogItemClickListener() {
                    @Override
                    public void onItemClick(int position, Object s) {
                        switch (type) {
                            case 3:
                                tv4.setText(youerYuan.getRegionList().get(position).getName() + "" +
                                        " " + youerYuan.getRegionList().get(position)
                                        .getClassName());
                                kindergartenName = youerYuan.getRegionList().get(position)
                                        .getName();
                                kindergartenClass = youerYuan.getRegionList().get(position)
                                        .getName();
                                areaId = youerYuan.getRegionList().get(position).getId() + "";
                                break;
//                            case 4:
//                                tv5.setText(youerYuan.getRegionList().get(position).getName());
//                                kindergartenClass = youerYuan.getRegionList().get(position)
//                                        .getName();
//                                areaId = youerYuan.getRegionList().get(position).getId() + "";
//                                break;
                            default:
                                break;
                        }
                    }
                }).setDialogStyle(Color.parseColor
                ("#fcb810")).setCount(5).show();
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
