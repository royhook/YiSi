package yisi.adplugin.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by wallace on 15/12/18.
 */
public class KyxSDKGlobal {
    public static Context mContext;
    public static Context mBackContext;
    private static Handler mMainHandler = new Handler();

    /**
     * 运作在主线程
     *
     * @param runner
     */
    public static void runOnMainThread(Runnable runner) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            runner.run();
        } else {
            if (mMainHandler == null) {
                mMainHandler = new Handler();
            }
            mMainHandler.post(runner);
        }
    }

    public static void postDelayed(Runnable runnable, long time) {
        if (mMainHandler == null) {
            mMainHandler = new Handler();
        }
        mMainHandler.postDelayed(runnable, time);
    }

    public static void removeHandlerCallbacks(Runnable runnable) {
        if (mMainHandler == null)
            mMainHandler = new Handler();
        mMainHandler.removeCallbacks(runnable);
    }

}
