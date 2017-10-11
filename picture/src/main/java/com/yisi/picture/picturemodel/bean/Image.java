package com.yisi.picture.picturemodel.bean;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * Created by chenql on 2017/8/30.
 * 最终展示出来的图片都叫Image
 */

public class Image extends GenericJson {
    @Key
    String img_url;//图片原质量Url


    public void setUrl(String url) {
        this.img_url = url;
    }

    public String getUrl() {
        return img_url;
    }
}
