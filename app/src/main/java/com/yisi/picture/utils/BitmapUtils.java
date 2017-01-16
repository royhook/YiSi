package com.yisi.picture.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.yisi.picture.R;

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
        if (width > height)
            return bitmap;
        // 设置想要的大小
        int newWidth = ViewUtils.getDimen(R.dimen.px800);
        int newHeight = ViewUtils.getDimen(R.dimen.px1200);
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
}
