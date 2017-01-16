package com.yisi.picture.bean;

/**
 * Created by roy on 2017/2/23.
 */

public class Cache {
    long time;
    String json;

    public void setJson(String json) {
        this.json = json;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public String getJson() {
        return json;
    }
}
