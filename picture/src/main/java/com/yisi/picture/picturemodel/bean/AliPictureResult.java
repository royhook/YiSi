package com.yisi.picture.picturemodel.bean;

/**
 * Created by chenql on 2017/5/29.
 */

public class AliPictureResult<T> {
    T showapi_res_body;

    public void setShowapi_res_body(T showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public T getShowapi_res_body() {
        return showapi_res_body;
    }
}
