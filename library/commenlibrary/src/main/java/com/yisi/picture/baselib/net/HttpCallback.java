package com.yisi.picture.baselib.net;

import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.yisi.picture.baselib.utils.LogUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chenql on 2017/5/29.
 */

public abstract class HttpCallback<T> extends StringCallback {

    public abstract void onSuccess(T t);

    @Override
    public void onSuccess(String s, Call call, Response response) {
        try {
            Log.d("studio",s);
            T result = parseResponse(s);
            onSuccess(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onCacheSuccess(String s, Call call) {
        try {
            T result = parseResponse(s);
            onSuccess(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private T parseResponse(String response) {
        Type type = getType();
        T result = null;
        if (response == null) {
            return null;
        }
        try {
            Gson gson = new Gson();
            result = gson.fromJson(response, type);
        } catch (Exception e) {
            LogUtils.d(e.getMessage());
        }
        return result;
    }

    public Type getType() {
        Type type;
        Class<?> clazz = this.getClass();
        try {
            ParameterizedType mType = (ParameterizedType) clazz.getGenericInterfaces()[0];
            type = mType.getActualTypeArguments()[0];
        } catch (Throwable e) {
            type = ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return type;
    }
}
