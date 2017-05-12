package com.yisi.picture.baselib.utils;

import android.view.View;

import com.yisi.picture.application.YiSiApplication;

/**
 * Created by roy on 2017/2/11.
 */

public class ViewUtils {
    public static int getDimen(int resId) {
        return YiSiApplication.mGlobleContext.getResources().getDimensionPixelOffset(resId);
    }

    public static <T> T findView(View view, int id) {
        return (T) view.findViewById(id);
    }
}
