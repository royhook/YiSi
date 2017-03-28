package com.yisi.picture.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Log;

import com.yisi.picture.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by roy on 2017/2/11.
 */

public class BitmapUtils {
    /**
     * 图片裁剪
     *
     * @return
     */
    public static Bitmap cutImage(Bitmap bitmap) {
        // 获得图片的宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //目前先仅仅处理竖图片
        if (width > height)
            return bitmap;
        // 设置想要的大小
        float sample = height / (float) width;
        int newHeight = ViewUtils.getDimen(R.dimen.px1200);

        int newWidth = (int) (newHeight / sample);
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,
                true);
    }

    /**
     * 图片质量压缩
     */
    public static Bitmap zipBitmap(Bitmap image) {
        Log.d("size", "改之前的大小" + image.getByteCount() + "");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 2;
        options.inJustDecodeBounds = false;

        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] bytes = baos.toByteArray();
            ByteArrayInputStream isBm = new ByteArrayInputStream(bytes);
            return BitmapFactory.decodeStream(isBm, new Rect(), options);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }

}
