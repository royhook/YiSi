package com.yisi.picture.baselib.utils;

import java.util.Locale;

/**
 * Created by chenql on 2017/10/19.
 */

public class DeviceUtils {

    public static String getSystemLaungue() {
        Locale mSystemLanguageList[] = Locale.getAvailableLocales();
        //获取系统当前使用的语言
        String lan = Locale.getDefault().getLanguage();

        return lan;
    }


    public static boolean isChinese() {
        //判断语言是否是中文
        return getSystemLaungue().contains("zh");
    }
}
