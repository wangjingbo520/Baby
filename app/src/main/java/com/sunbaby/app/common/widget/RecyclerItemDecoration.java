package com.sunbaby.app.common.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author wangjingbo
 * @date 2018/7/11
 * describe
 */
public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {
    private int itemSpace;
    private int itemNum;

    private int space;

    public RecyclerItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.left = space;
        outRect.bottom = space;
        //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
        if (parent.getChildLayoutPosition(view) %3==0) {
            outRect.left = 0;
        }
    }




}
