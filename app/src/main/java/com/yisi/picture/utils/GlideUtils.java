package com.yisi.picture.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.yisi.picture.application.YiSiApplication;

import java.io.File;

import static android.R.attr.radius;


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
     * @param imageUrl
     * @param imageView
     * @param loadListener
     */
    private static void displayImage(final String imageUrl, final ImageView imageView, int defultRes, final LoaderListener
            loadListener) {

        Glide.with(YiSiApplication.mGlobleContext).load(imageUrl).asBitmap().placeholder(defultRes).thumbnail(0.1f).listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                if (loadListener != null)
                    loadListener.loadFail(e.getMessage());
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                if (loadListener != null)
                    loadListener.loadSuccess();
                return false;
            }
        }).into(imageView);
    }

    public static void displayImage(String imageUrl, final ImageView imageView, int defultRes) {
        displayImage(imageUrl, imageView, defultRes, null);
    }


    public static void displayImage(String imageUrl, ImageView imageView) {
        Glide.with(YiSiApplication.mGlobleContext).load(imageUrl).into(imageView);
    }


    public static void displayImageByFile(final ImageView imageView, File file) {

    }

    /**
     * 加载缩略图
     *
     * @param url
     * @param imageView
     */
    public static void displayImageWithThrun(String url, ImageView imageView, final LoaderListener loaderListener) {
        Glide.with(YiSiApplication.mGlobleContext).load(url).thumbnail(0.1f).bitmapTransform(new CropTransform(YiSiApplication.mGlobleContext)).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                if (loaderListener != null)
                    loaderListener.loadFail(e.getMessage());
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                if (loaderListener != null)
                    loaderListener.loadSuccess();
                return false;
            }
        }).into(imageView);
    }


    public static void stopLoad() {
        Glide.with(YiSiApplication.mGlobleContext).pauseRequests();
    }

    public static void resumeLoad() {
        Glide.with(YiSiApplication.mGlobleContext).resumeRequests();
    }

    public interface LoaderListener {
        void loadSuccess();

        void loadFail(String errorMessage);
    }

    private static class CropTransform extends BitmapTransformation {

        CropTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return BitmapUtils.cutImage(toTransform);
        }

        @Override
        public String getId() {
            return getClass().getName() + Math.round(radius);
        }
    }
}

