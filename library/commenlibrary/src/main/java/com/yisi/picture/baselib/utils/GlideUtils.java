package com.yisi.picture.baselib.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import java.io.File;

import static android.R.attr.radius;


/**
 * Create With AndroidStudio
 * Author chenql
 * Data  2016-8-17.
 */
public class GlideUtils {


    private static GlideBuilder glideBuilder;

    private static Context mContext;

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
        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();
        int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);
        glideBuilder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);//这个格式不支持透明度，但是内存占用很小
        glideBuilder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));//设置缓存内存大小
        mContext = context;
    }

    public static void displayImageAndDownLoad(String url) {
        Glide.with(mContext).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                if (resource != null)
                    BitmapUtils.downloadBitmap(resource);
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {

            }
        });
    }

    public static void displayImageAndDownLoad(String url, SimpleTarget<Bitmap> target) {
        Glide.with(mContext).load(url).asBitmap().into(target);
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

        Glide.with(mContext).load(imageUrl).skipMemoryCache(true).thumbnail(0.1f).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                if (loadListener != null)
                    loadListener.loadFail(e.getMessage());
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
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
        displayImage(imageUrl, imageView, 0, null);
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
        Glide.with(mContext).load(url).thumbnail(0.1f).bitmapTransform(new CropTransform(mContext)).listener(new RequestListener<String, GlideDrawable>() {
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

    public static void displayCircleImage(String url, ImageView imageView) {
//        Glide.with(mContext).load(url).transform(new GlideRoundTransform(mContext, 2)).into(imageView);
        Glide.with(mContext).load(url).asBitmap().transform(new GlideRoundTransform(mContext, 10)).into(imageView);
    }


    public static void stopLoad() {
        Glide.with(mContext).pauseRequests();
    }

    public static void resumeLoad() {
        Glide.with(mContext).resumeRequests();
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

    private static class ZipTransform extends BitmapTransformation {

        public ZipTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return BitmapUtils.zipBitmap(toTransform);
        }

        @Override
        public String getId() {
            return getClass().getName() + Math.round(radius);
        }
    }

    public static class GlideCircleTransform extends BitmapTransformation {
        public GlideCircleTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            // TODO this could be acquired from the pool too
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName();
        }
    }

    public static class GlideRoundTransform extends BitmapTransformation {

        private static float radius = 0;

        public GlideRoundTransform(Context context) {
            this(context, 4);
        }

        public GlideRoundTransform(Context context, int dp) {
            super(context);
            this.radius = dp;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return roundCrop(pool, toTransform);
        }

        private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName() + Math.round(radius);
        }
    }
}

