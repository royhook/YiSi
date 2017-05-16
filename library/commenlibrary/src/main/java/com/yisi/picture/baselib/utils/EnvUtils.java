package com.yisi.picture.baselib.utils;

import android.content.Context;

/**
 * Created by chenql on 2017/5/3.
 */

public class EnvUtils {
    public static final String BMOB_APPID = "9b7629a191656e19839a10be2a27363b";
    public static final String BMOB_YISI_APPID = "bd680cd370f7e8716b941d9d37872d2e";
    static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    //是否为Yisi Mode
    public static boolean isYisiMode() {
        return !PreferencesUtils.getString(mContext, PreferencesUtils.KEY.KEY_BMOB_ID, BMOB_APPID).equals(BMOB_APPID);
    }
}
