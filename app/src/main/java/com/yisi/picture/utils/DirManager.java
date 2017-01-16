package com.yisi.picture.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by roy on 2017/2/23.
 */

public class DirManager {
    private String localPathDirName = "YiSi";
    private String localCacheDirName = "cache";

    private DirManager() {

    }

    public static DirManager getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        static final DirManager instance = new DirManager();
    }

    public void init() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + localPathDirName;
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
        initCachePath();
    }

    public String getLocalPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + localPathDirName;
    }

    private void initCachePath() {
        String cachePath = getLocalPath() + "/" + localCacheDirName;
        File file = new File(cachePath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public String getCachePath() {
        return getLocalPath() + "/" + localCacheDirName;
    }


}
