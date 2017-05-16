package com.yisi.picture.baselib.utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by roy on 2017/2/12.
 */

public class DisplayUtils {
    static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    private static WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

    public static int getScreenWidth() {
        return wm.getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight() {
        return wm.getDefaultDisplay().getHeight();
    }
}
