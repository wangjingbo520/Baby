package com.sunbaby.app.common.widget.floawlayout;

import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sunbaby.app.MyApplication;
import com.sunbaby.app.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wangjingbo
 * @date 2018/7/10
 * describe
 */
public abstract class TagAdapter<T> {
    private List<T> mTagDatas;
    private OnDataChangedListener mOnDataChangedListener;
    @Deprecated
    private HashSet<Integer> mCheckedPosList = new HashSet<Integer>();

    public TagAdapter(List<T> datas) {
        mTagDatas = datas;
    }

    @Deprecated
    public TagAdapter(T[] datas) {
        mTagDatas = new ArrayList<T>(Arrays.asList(datas));
    }

    interface OnDataChangedListener {
        void onChanged();
    }

    void setOnDataChangedListener(OnDataChangedListener listener) {
        mOnDataChangedListener = listener;
    }

    @Deprecated
    public void setSelectedList(int... poses) {
        Set<Integer> set = new HashSet<>();
        for (int pos : poses) {
            set.add(pos);
        }
        setSelectedList(set);
    }


    @Deprecated
    public void setSelectedList(Set<Integer> set) {
        mCheckedPosList.clear();
        if (set != null) {
            mCheckedPosList.addAll(set);
        }
        notifyDataChanged();
    }

    public void clearSelect() {
        mCheckedPosList.clear();
        Set<Integer> set = new HashSet<>();
        mCheckedPosList.addAll(set);
        notifyDataChanged();
    }

    @Deprecated
    HashSet<Integer> getPreCheckedList() {
        return mCheckedPosList;
    }


    public int getCount() {
        return mTagDatas == null ? 0 : mTagDatas.size();
    }

    public void notifyDataChanged() {
        if (mOnDataChangedListener != null)
            mOnDataChangedListener.onChanged();
    }

    public T getItem(int position) {
        return mTagDatas.get(position);
    }

    public abstract View getView(FlowLayout parent, int position, T t);


    public void onSelected(int position, View view) {
        Log.d("zhy", "onSelected " + position);
        TextView textView = (TextView) view;
        textView.setTextColor(ContextCompat.getColor(MyApplication.context, R.color.white));
    }

    public void unSelected(int position, View view) {
        TextView textView = (TextView) view;
        textView.setTextColor(ContextCompat.getColor(MyApplication.context, R.color.textColor3));
        Log.d("zhy", "unSelected " + position);
    }

    public boolean setSelected(int position, T t) {
        return false;
    }


}
