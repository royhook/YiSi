package com.yisi.picture.picturemodel.net;

import com.yisi.picture.baselib.application.YiSiApplication;

import java.util.HashMap;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by roy on 2017/2/23.
 */

public class BmobRequest {
    private Builder mBuilder;

    public BmobRequest(Builder builder) {
        this.mBuilder = builder;
    }


    public <T> void request(final FindListener<T> listener) {

        BmobQuery<T> bmobQuery = new BmobQuery<>();
        if (YiSiApplication.isChange) {
            mBuilder.readCache = false;
        }
        if (mBuilder.readCache) {
            bmobQuery.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
        } else {
            bmobQuery.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
        }
        if (mBuilder.getWhereEqualTo() != null) {
            for (Map.Entry<String, Object> entry : mBuilder.getWhereEqualTo().entrySet()) {
                bmobQuery.addWhereEqualTo(entry.getKey(), entry.getValue());
            }
        }

        if (mBuilder.whereNotEqualTo != null) {
            for (Map.Entry<String, Object> entry : mBuilder.whereNotEqualTo.entrySet()) {
                bmobQuery.addWhereNotEqualTo(entry.getKey(), entry.getValue());
            }
        }

        bmobQuery.setMaxCacheAge(mBuilder.cacheTime);
        bmobQuery.setLimit(mBuilder.getLimit());
        bmobQuery.order(mBuilder.getOrder());
        bmobQuery.setSkip(mBuilder.getSkip());
        bmobQuery.findObjects(listener);
    }

    public static class Builder {
        private HashMap<String, Object> whereEqualTo = new HashMap<>();
        private HashMap<String, Object> whereNotEqualTo = new HashMap<>();

        private int limit = 10;
        private String order = "-createdAt";
        private long cacheTime = 1000 * 60 * 60 * 24;//默认一天
        private boolean putCache;
        private boolean readCache;
        private int skip;
        //过滤数据


        public Builder() {

        }

        public HashMap<String, Object> getWhereEqualTo() {
            return whereEqualTo;
        }

        public int getLimit() {
            return limit;
        }

        public String getOrder() {
            return order;
        }

        public Builder addEqualTo(String key, Object values) {
            whereEqualTo.put(key, values);
            return this;
        }

        public Builder setSkip(int skip) {
            this.skip = skip;
            return this;
        }

        public int getSkip() {
            return skip;
        }

        public Builder setWhereEqualTo(HashMap<String, Object> whereEqualTo) {
            this.whereEqualTo = whereEqualTo;
            return this;
        }

        public Builder setWhereNotEqualTo(HashMap<String, Object> whereNotEqualTo) {
            this.whereNotEqualTo = whereNotEqualTo;
            return this;
        }

        public Builder setLimit(int limit) {
            this.limit = limit;
            return this;
        }

        public Builder setPutCache(boolean putCache) {
            this.putCache = putCache;
            return this;
        }

        public Builder setReadCache(boolean readCache) {
            this.readCache = readCache;
            return this;
        }

        public Builder setCacheTime(long cacheTime) {
            this.cacheTime = cacheTime;
            return this;
        }

        public long getCacheTime() {
            return cacheTime;
        }

        public Builder setOrder(String order) {
            this.order = order;
            return this;
        }

        public BmobRequest build() {
            return new BmobRequest(this);
        }
    }
}
