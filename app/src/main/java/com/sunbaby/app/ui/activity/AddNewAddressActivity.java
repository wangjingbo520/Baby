package com.sunbaby.app.ui.activity;

import android.content.Context;
import android.content.Intent;
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
import com.sunbaby.app.callback.IAddAdress;
import com.sunbaby.app.callback.IRegisterView;
import com.sunbaby.app.common.base.BaseViewActivity;
import com.sunbaby.app.common.utils.ToastUtil;
import com.sunbaby.app.common.widget.SwitchButton;
import com.sunbaby.app.common.widget.wheel.util.WheelUtils;
import com.sunbaby.app.common.widget.wheel.widget.WheelViewDialog;
import com.sunbaby.app.presenter.AddNewAddressPresenter;
import com.sunbaby.app.presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wangjingbo
 * @date 2018/7/6
 * describe 添加收货地址
 */
public class AddNewAddressActivity extends BaseViewActivity implements IAddAdress, SwitchButton
        .OnCheckedChangeListener {
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPhoneNumber)
    EditText etPhoneNumber;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.etDetailAdress)
    EditText etDetailAdress;
    @BindView(R.id.switchButton)
    SwitchButton switchButton;

    /**
     * 省id
     */
    private String provinceId = "";
    //
    /**
     * 市id
     */
    private String cityId = "";
    /**
     * 区id
     */
    private String district = "";

    /**
     * 幼儿园id
     */
    private String areaId;

    /**
     * 是否默认收货地址  0默认地址    1非默认地址
     */
    private String status = "1";

    /**
     * 用来判断省市区
     */
    private int type = 1;

    private AddNewAddressPresenter addNewAddressPresenter;
    private WheelViewDialog dialog;

    public static void start(Context context) {
        Intent starter = new Intent(context, AddNewAddressActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_add_new_address);
        setTitle("添加收货地址");
        setRightText("保存");
        dialog = new WheelViewDialog(this);
        switchButton.setOnCheckedChangeListener(this);
        addNewAddressPresenter = new AddNewAddressPresenter(mContext, this);
    }

    @Override
    public void onRightLisenter() {
        super.onRightLisenter();
        //保存地址
        saveAdress();
    }

    private void saveAdress() {
        String name = etName.getText().toString().trim();
        String mobile = etPhoneNumber.getText().toString().trim();
        String detailedAddress = etDetailAdress.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            showToast("请先输入收货人的名字");
            return;
        }
        if (TextUtils.isEmpty(mobile)) {
            showToast("请先输入收货人的手机号码");
            return;
        }
        if (TextUtils.isEmpty(provinceId)) {
            showToast("请先选择省份");
            return;
        }
        if (TextUtils.isEmpty(cityId)) {
            showToast("请先选择所在市区");
            return;
        }
        if (TextUtils.isEmpty(district)) {
            showToast("请选择区");
            return;
        }
        if (TextUtils.isEmpty(areaId)) {
            showToast("请选择幼儿园");
            return;
        }

        addNewAddressPresenter.insertAddress(getUserId(), mobile, status, provinceId, cityId,
                district, areaId,
                detailedAddress, name);
    }

    @Override
    public void insertAddress() {
        //新增地址成功
        finish();
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
        }
    }

    private void showYoueryuan(String name, final YouerYuan youerYuan) {
        dialog.setTitle(name).setItems(youerYuan.getRegionList()).setButtonText("确 定")
                .setOnDialogItemClickListener(new WheelViewDialog.OnDialogItemClickListener() {
                    @Override
                    public void onItemClick(int position, Object s) {
                        switch (type) {
                            case 3:
                                areaId = youerYuan.getRegionList().get(position)
                                        .getId() + "";
                                tv4.setText(youerYuan.getRegionList().get(position).getName());
                                break;

                            default:
                                break;
                        }
                    }
                }).setDialogStyle(Color.parseColor
                ("#6699ff")).setCount(5).show();
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
                                cityId = areabean.getRegionList().get(position).getId() + "";
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

    @Override
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {
        if (isChecked) {
            //选中了,默认地址
            status = "0";
        } else {
            //未选中,非默认地址
            status = "1";
        }
    }

    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv1:
                //选择省
                type = 0;
                cityId = "";
                district = "";
                areaId = "";
                etDetailAdress.setText("");
                tv2.setText("选择市");
                tv3.setText("选择区");
                tv4.setText("幼儿园");
                addNewAddressPresenter.regionList("0", "0");
                break;
            case R.id.tv2:
                //选择市
                if (!TextUtils.isEmpty(provinceId + "")) {
                    type = 1;
                    district = "";
                    areaId = "";
                    etDetailAdress.setText("");
                    tv3.setText("选择区");
                    tv4.setText("幼儿园");
                    addNewAddressPresenter.regionList("1", provinceId + "");
                } else {
                    ToastUtil.showMessage("请先选择省");
                }
                break;
            case R.id.tv3:
                //选择区
                if (!TextUtils.isEmpty(cityId + "")) {
                    type = 2;
                    areaId = "";
                    etDetailAdress.setText("");
                    tv4.setText("幼儿园");
                    addNewAddressPresenter.regionList("2", cityId + "");
                } else {
                    ToastUtil.showMessage("请先选择市");
                }
                break;
            case R.id.tv4:
                //选择幼儿园名字
                if (!TextUtils.isEmpty(district + "")) {
                    type = 3;
                    etDetailAdress.setText("");
                    addNewAddressPresenter.kindergarten("1", district + "", "");
                } else {
                    ToastUtil.showMessage("请先选择您所在的区");
                }
                break;
            default:
                break;
        }
    }


}
