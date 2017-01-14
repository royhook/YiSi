package com.yisi.picture.application;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * Created by roy on 2017/1/13.
 */

public class YiSiApplication extends Application {
    public static final String BMOB_APPID = "9b7629a191656e19839a10be2a27363b";
    private static Handler mGlobleHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate() {
        super.onCreate();
        initBmob();
    }

    public static void postMainThread(Runnable runnable) {
        mGlobleHandler.post(runnable);
    }

    public static void postDelay(Runnable runnable, int delayTime) {
        mGlobleHandler.postDelayed(runnable, delayTime);
    }

    private void initBmob() {
        BmobConfig config =new BmobConfig.Builder(this)
        //设置appkey
        .setApplicationId("Your Application ID")
        //请求超时时间（单位为秒）：默认15s
        .setConnectTimeout(30)
        //文件分片上传时每片的大小（单位字节），默认512*1024
        .setUploadBlockSize(1024*1024)
        //文件的过期时间(单位为秒)：默认1800s
        .setFileExpiration(2500)
        .build();
        Bmob.initialize(config);
    }
}
