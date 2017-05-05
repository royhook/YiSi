package com.yisi.picture.utils;

import com.yisi.picture.activity.SplashAty;
import com.yisi.picture.application.YiSiApplication;

/**
 * Created by chenql on 2017/5/3.
 */

public class EnvUtils {
    //是否为Yisi Mode
    public static boolean isYisiMode() {
        return !PreferencesUtils.getString(YiSiApplication.mGlobleContext, PreferencesUtils.KEY.KEY_BMOB_ID, SplashAty.BMOB_APPID).equals(SplashAty.BMOB_APPID);
    }
}
