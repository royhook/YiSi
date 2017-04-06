package com.yisi.picture.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by roy on 2017/2/23.
 */

public class DirManager {
    private String localPathDirName = "YiSi";
    private String localCacheDirName = "cache";
    private String localDownloadDirName = "download";

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
        initDownloadPath();
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

    public String getDownloadPath() {
        return getLocalPath() + "/" + localDownloadDirName;
    }

    private void initDownloadPath() {
        String cachePath = getLocalPath() + "/" + localDownloadDirName;
        File file = new File(cachePath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
