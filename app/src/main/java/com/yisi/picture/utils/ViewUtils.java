package com.yisi.picture.utils;

import com.yisi.picture.application.YiSiApplication;

/**
 * Created by roy on 2017/2/11.
 */

public class ViewUtils {
    public static int getDimen(int resId) {
        return YiSiApplication.mGlobleContext.getResources().getDimensionPixelOffset(resId);
    }
}
