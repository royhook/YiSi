package com.yisi.picture.net.cache;

import com.yisi.picture.utils.DirManager;
import com.yisi.picture.utils.FileUtil;

import java.io.File;

/**
 * Created by roy on 2017/2/23.
 * 以id为文件名进行缓存接口
 */

public class CacheManager {

    private CacheManager() {

    }

    public static CacheManager getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        static final CacheManager instance = new CacheManager();
    }

    public void putCache(String cacheId, String json) {
        String cachePath = DirManager.getInstance().getCachePath() + "/" + cacheId;
        FileUtil.createFile(cachePath);
        FileUtil.writeSting(json, false, cachePath, false);
    }

    public String readCache(String cacheId) {
        String cachePath = DirManager.getInstance().getCachePath() + "/" + cacheId;
        File file = new File(cachePath);
        if (!file.exists())
            return "";
        String json = FileUtil.readTxtFile(file);
        if (json == null)
            return "";
        return json;
    }

}
