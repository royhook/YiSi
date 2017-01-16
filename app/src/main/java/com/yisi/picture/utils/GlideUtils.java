package com.yisi.picture.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.yisi.picture.application.YiSiApplication;

import java.io.File;


/**
 * Create With AndroidStudio
 * Author chenql
 * Data  2016-8-17.
 */
public class GlideUtils {

    static {
        initGlide(YiSiApplication.mGlobleContext);
    }

    private static GlideBuilder glideBuilder;

    private static void checkBuilder(Context context) {
        if (glideBuilder == null) {
            glideBuilder = new GlideBuilder(context);
        }
    }

    /**
     * 初始化Glide
     *
     * @param context
     */
    public static void initGlide(Context context) {
        checkBuilder(context);
        int memoryCacheSize = 3 * 1024 * 1024;
        glideBuilder.setMemoryCache(new LruResourceCache(memoryCacheSize));//设置内存大小
        glideBuilder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);//这个格式不支持透明度，但是内存占用很小
        glideBuilder.setBitmapPool(new LruBitmapPool(memoryCacheSize));//设置缓存内存大小
    }


    /**
     * 加载图片 指定context
     * 指定ImageView
     *
     * @param context
     * @param imageUrl
     * @param imageView
     * @param loadListener
     */
    private static void displayImage(final Context context, final String imageUrl, final ImageView imageView, Drawable drawable, final LoaderListener
            loadListener) {

        Glide.with(context).load(imageUrl).asBitmap().placeholder(drawable).error(drawable).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                if (imageView != null)
                    imageView.setImageBitmap(resource);

                loadListener.loadSuccess(resource);
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                loadListener.loadFail(e.getMessage());
            }
        });

    }


    /**
     * 默认application context
     * 加载图片，
     * 默认drawable
     * 指定ImageView
     *
     * @param imageUrl
     * @param imageView
     * @param loaderListener
     */
    public static void displayImage(String imageUrl, ImageView imageView, LoaderListener loaderListener) {
        displayImage(YiSiApplication.mGlobleContext, imageUrl, imageView, null, loaderListener);
    }

    public static void displayImage(String imageUrl, ImageView imageView){
        Glide.with(YiSiApplication.mGlobleContext).load(imageUrl).into(imageView);
    }

    /**
     * 不指定Drawable
     *
     * @param imageUrl
     * @param imageView
     * @param drawableResouce
     * @param loaderListener
     */
    public static void displayImage(String imageUrl, ImageView imageView, @DrawableRes int drawableResouce, LoaderListener loaderListener) {
        Drawable drawable = YiSiApplication.mGlobleContext.getResources().getDrawable(drawableResouce);
        displayImage(YiSiApplication.mGlobleContext, imageUrl, imageView, drawable, loaderListener);
    }

    /**
     * 需要处理回调的bitmao
     * 不指定 ImageView
     *
     * @param imageUrl
     * @param loaderListener
     */
    public static void displayImage(final String imageUrl, final LoaderListener loaderListener) {
        displayImage(imageUrl, null, loaderListener);
    }


    public static void displayImageByFile(final ImageView imageView, File file) {
        if (imageView == null)
            return;
    }

    public interface LoaderListener {
        void loadSuccess(Bitmap bitmap);

        void loadFail(String errorMessage);
    }
}

