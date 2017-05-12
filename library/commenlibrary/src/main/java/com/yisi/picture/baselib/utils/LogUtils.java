package com.yisi.picture.baselib.utils;

import android.util.Log;

import com.yisi.picture.BuildConfig;

/**
 * Created by roy on 2017/1/16.
 */

public class LogUtils {

    public static void d(String content) {
        if (BuildConfig.DEBUG) {
            Log.d("ysssssss", content);
        }
    }

}
