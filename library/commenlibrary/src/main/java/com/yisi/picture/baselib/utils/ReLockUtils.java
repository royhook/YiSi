package com.yisi.picture.baselib.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yisi.picture.baselib.application.YiSiApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenql on 2017/11/2.
 */

public class ReLockUtils {

    private static List<String> sHadRelockList = new ArrayList<>();//已经解锁的套图数，提前初始化

    public static void saveId(String id) {
        sHadRelockList.add(id);
    }

    public static boolean idIsExist(String id) {
        return sHadRelockList.contains(id);
    }

    public static void startRelock() {
        String json = PreferencesUtils.getString(YiSiApplication.mGlobleContext, PreferenceKey.MY_COLLECT_IMAGE);
        List<String> list = new Gson().fromJson(json, new TypeToken<List<String>>() {
        }.getType());
        if (list != null && list.size() > 0) {
            sHadRelockList.addAll(list);
        }
    }

    public static void saveRelock() {
        String json = new Gson().toJson(sHadRelockList);
        PreferencesUtils.putString(YiSiApplication.mGlobleContext, json, "");
    }

}
