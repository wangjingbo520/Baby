/*
 * Copyright (C) 2016 venshine.cn@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sunbaby.app.common.widget.wheel.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.sunbaby.app.bean.Areabean;
import com.sunbaby.app.bean.YouerYuan;
import com.sunbaby.app.common.widget.wheel.widget.WheelItem;


/**
 * 滚轮数组适配器
 *
 */
public class ArrayWheelAdapter<T> extends BaseWheelAdapter<T> {

    private Context mContext;

    public ArrayWheelAdapter(Context context) {
        mContext = context;
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new WheelItem(mContext);
        }
        WheelItem wheelItem = (WheelItem) convertView;
        T item = getItem(position);
        if (wheelItem instanceof CharSequence) {
            if (item instanceof Areabean.RegionListBean) {
                wheelItem.setText(((Areabean.RegionListBean) item).getName());
            } else if (item instanceof YouerYuan.RegionListBean) {
                wheelItem.setText(((YouerYuan.RegionListBean) item).getName() + "  " + ((YouerYuan
                        .RegionListBean) item).getClassName());
            }
        } else {
            if (item instanceof Areabean.RegionListBean) {
                wheelItem.setText(((Areabean.RegionListBean) item).getName());
            } else if (item instanceof YouerYuan.RegionListBean) {
                wheelItem.setText(((YouerYuan.RegionListBean) item).getName() + "  " + ((YouerYuan
                        .RegionListBean) item).getClassName());
            }
        }
        return convertView;
    }

}
