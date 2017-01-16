package com.yisi.picture.utils;

import android.content.Context;
import android.view.WindowManager;

import com.yisi.picture.application.YiSiApplication;

/**
 * Created by roy on 2017/2/12.
 */

public class DisplayUtils {
    private static WindowManager wm = (WindowManager) YiSiApplication.mGlobleContext.getSystemService(Context.WINDOW_SERVICE);

    public static int getScreenWidth() {
        return wm.getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight() {
        return wm.getDefaultDisplay().getHeight();
    }
}
