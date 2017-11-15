package com.yisi.picture.baselib.application;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDexApplication;

import com.kinvey.android.Client;
import com.kinvey.android.model.User;
import com.kinvey.android.store.UserStore;
import com.kinvey.java.cache.KinveyCachedClientCallback;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.io.IOException;

/**
 * Created by roy on 2017/1/13.
 */

public class YiSiApplication extends MultiDexApplication {
    @SuppressLint("StaticFieldLeak")
    public static Context mGlobleContext;
    private static Handler mGlobleHandler = new Handler(Looper.getMainLooper());
    private RefWatcher mRefWatcher;
    public static boolean isChange = false;
    private static Client mKinveyClient;


    @Override
    public void onCreate() {
        super.onCreate();

        mGlobleContext = this;
        mRefWatcher = LeakCanary.install(this);
        mKinveyClient = new Client
                .Builder("kid_BJEr--R9b", "d7b13213fd244c238555b2655a42f687", this)
                .setBaseUrl("https://ys.xn--nqvpe700br9kjqa.com")
                .build();
        try {
            UserStore.login(mKinveyClient, new KinveyCachedClientCallback<User>() {
                @Override
                public void onSuccess(User user) {

                }

                @Override
                public void onFailure(Throwable throwable) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void postMainThread(Runnable runnable) {
        mGlobleHandler.post(runnable);
    }

    public static void postDelay(Runnable runnable, int delayTime) {
        mGlobleHandler.postDelayed(runnable, delayTime);
    }

    public static Client getKinveyClient() {
        return mKinveyClient;
    }
}

