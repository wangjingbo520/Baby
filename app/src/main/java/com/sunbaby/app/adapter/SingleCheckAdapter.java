package com.sunbaby.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;

import com.sunbaby.app.R;
import com.sunbaby.app.common.utils.ToastUtil;

import java.util.HashMap;
import java.util.List;

/**
 * @author 王静波
 * @date 2018/7/17
 * describe  支付类型,后台可动态配置
 */
public class SingleCheckAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public HashMap<Integer, Boolean> states = new HashMap<Integer, Boolean>();

    //获取选中的值
    private void getSelectPosition() {
        for (int i = 0; i < getCount(); i++) {
            if (states.get(i) != null && states.get(i)) {
                ToastUtil.showMessage(i + "已选中");
            }
        }
    }

    public SingleCheckAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(final int position, View arg1, ViewGroup arg2) {
        ViewHolder holder = null;
        if (arg1 == null) {
            holder = new ViewHolder();
            arg1 = View.inflate(context, R.layout.layout_single_check, null);
            holder.radioButton = arg1.findViewById(R.id.rb);
            arg1.setTag(holder);
        } else {
            holder = (ViewHolder) arg1.getTag();
        }

        arg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //把所有的按钮的状态设置为没选中
                for (int i = 0; i < getCount(); i++) {
                    states.put(i, false);
                }
                states.put(position, true);
                notifyDataSetChanged();
            }
        });

        //上面是点击后设置状态，但是也是需要设置显示样式,通过判断状态设置显示的样式
        if (states.get((Integer) position) == null || states.get((Integer) position) == false) {
            //true说明没有被选中
            holder.radioButton.setChecked(false);
        } else {
            holder.radioButton.setChecked(true);
        }
        return arg1;
    }

    static class ViewHolder {
        RadioButton radioButton;
    }
}
