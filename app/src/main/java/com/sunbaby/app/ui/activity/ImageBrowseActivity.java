package com.sunbaby.app.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.sunbaby.app.R;
import com.sunbaby.app.common.base.BaseActivity;
import com.sunbaby.app.common.widget.HackyViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wangjingbo
 * @date 2018/8/1
 * describe  商品大图查看
 */
public class ImageBrowseActivity extends BaseActivity {
    @BindView(R.id.view_pager)
    HackyViewPager mViewPager;
    private ArrayList<String> imageUrls;
    private int selectedIndex;

    public static void start(Activity context, ArrayList<String> urls, int selectedIndex) {
        Intent starter = new Intent(context, ImageBrowseActivity.class);
        starter.putExtra("urls", urls);
        starter.putExtra("selectedIndex", selectedIndex);
        context.startActivity(starter);
        context.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_browse);
        ButterKnife.bind(this);
        selectedIndex = getIntent().getExtras().getInt("selectedIndex");
        imageUrls = getIntent().getExtras().getStringArrayList("urls");
        mViewPager.setAdapter(new ViewPagerAdapter(imageUrls));
        mViewPager.setCurrentItem(selectedIndex);

    }

    private class ViewPagerAdapter extends PagerAdapter {
        private PhotoView mPhotoView;
        private ArrayList<String> imageUrls;

        private ViewPagerAdapter(ArrayList<String> imageUrls) {
            this.imageUrls = imageUrls;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = LayoutInflater.from(ImageBrowseActivity.this).inflate(
                    R.layout.photoview_layout, null);
            mPhotoView = view.findViewById(R.id.photoview);
            mPhotoView.setOnPhotoTapListener(new OnPhotoTapListener() {
                @Override
                public void onPhotoTap(ImageView imageView, float v, float v1) {
                    finish();
                }
            });

            Glide.with(mContext).load(imageUrls.get(position))
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .error(R.mipmap.default_pic))
                    .transition(new DrawableTransitionOptions().crossFade(800))
                    .into(mPhotoView);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return imageUrls.size() > 0 ? imageUrls.size() : 0;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
