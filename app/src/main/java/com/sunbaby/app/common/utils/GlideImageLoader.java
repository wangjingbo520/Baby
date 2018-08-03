package com.sunbaby.app.common.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sunbaby.app.R;

/**
 * @author wangjingbo
 * @date 2018/7/9
 * describe
 */
public class GlideImageLoader {

    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions().placeholder(R.mipmap.default_pic).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }

    public static void loadImage(Context context, String url, ImageView imageView, int resId) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions().placeholder(resId).error(resId).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }

    public static void loadImage(Context context, ImageView imageView, int resId) {
        Glide.with(context)
                .load(resId)
                .into(imageView);
    }


}
