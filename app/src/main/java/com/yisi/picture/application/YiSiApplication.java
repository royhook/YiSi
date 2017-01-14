package com.yisi.picture.application;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by roy on 2017/1/13.
 */

public class YiSiApplication extends Application {
    private static Handler mGlobleHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static void postMainThread(Runnable runnable) {
        mGlobleHandler.post(runnable);
    }

    public static void postDelay(Runnable runnable, int delayTime) {
        mGlobleHandler.postDelayed(runnable, delayTime);
    }
}
