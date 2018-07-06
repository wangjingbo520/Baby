package com.sunbaby.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author 王静波
 * @date 2018/7/6
 * describe
 */
public class MainTabAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList_fragment;
    private String[] mList_title;

    public MainTabAdapter(FragmentManager fm, List<Fragment> list_fragment, String[] list_title) {
        super(fm);

        mList_fragment = list_fragment;
        mList_title = list_title;
    }

    @Override
    public Fragment getItem(int position) {
        return mList_fragment.get(position);
    }

    @Override
    public int getCount() {
        return mList_fragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList_title[position];
    }
}

