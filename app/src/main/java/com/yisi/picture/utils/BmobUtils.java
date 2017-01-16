package com.yisi.picture.utils;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by roy on 2017/1/21.
 */

public class BmobUtils {
    /**
     * 查询全部数据
     *
     * @param findListener
     *
     */
    public static <T>  void request(FindListener<T> findListener) {
        BmobQuery<T> bmobQuery = new BmobQuery<>();
        bmobQuery.findObjects(findListener);
    }

    /**
     * 通过某项属性查询数据
     * @param key
     * @param value
     * @param findListener
     * @param <T>
     */
    public static <T>  void request(String key, Object value, FindListener<T> findListener) {
        BmobQuery<T> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo(key, value);
        bmobQuery.findObjects(findListener);
    }
}
