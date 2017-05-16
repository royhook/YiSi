package com.yisi.picture.baselib.utils;

import android.content.Context;
import android.view.View;

/**
 * Created by roy on 2017/2/11.
 */

public class ViewUtils {
    static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static int getDimen(int resId) {
        return mContext.getResources().getDimensionPixelOffset(resId);
    }

    public static <T> T findView(View view, int id) {
        return (T) view.findViewById(id);
    }
}
