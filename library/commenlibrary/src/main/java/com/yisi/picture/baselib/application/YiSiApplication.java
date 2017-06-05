package com.yisi.picture.baselib.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by roy on 2017/1/13.
 */

public class YiSiApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    public static Context mGlobleContext;
    private static Handler mGlobleHandler = new Handler(Looper.getMainLooper());
    private RefWatcher mRefWatcher;
    public static boolean isChange = false;

    @Override
    public void onCreate() {
        super.onCreate();

        mGlobleContext = this;
        mRefWatcher = LeakCanary.install(this);
    }

    public static void postMainThread(Runnable runnable) {
        mGlobleHandler.post(runnable);
    }

    public static void postDelay(Runnable runnable, int delayTime) {
        mGlobleHandler.postDelayed(runnable, delayTime);
    }
}

