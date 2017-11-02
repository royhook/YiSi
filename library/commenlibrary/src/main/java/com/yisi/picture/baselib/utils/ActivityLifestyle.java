package com.yisi.picture.baselib.utils;

import android.app.Activity;


/**
 * <strong>Kamcord控制类（单例）</strong> <br/>
 * <font color="green">供SDKWrapper中的KamcordAssist类进行调用</font>
 *
 * @author chenql
 */
public class ActivityLifestyle {
    private static ActivityLifestyle mInstance;
    /**
     * 游戏当前Activity上下文对象
     */
    private Activity mActivity;

    /**
     * 构造方法私有化
     */
    private ActivityLifestyle(Activity activity) {

        this.mActivity = activity;
    }

    public ActivityLifestyle() {

    }

    public void free() {
        mActivity = null;
    }


    public void setGameCurrentActivity(Activity gameCurrentActivity) {
        mActivity = gameCurrentActivity;
    }


    public static ActivityLifestyle getInstance() {
        if (mInstance != null) {
            return mInstance;
        }
        mInstance = new ActivityLifestyle();
        return mInstance;
    }


    public Activity getActivity() {
        return mActivity;
    }


}
