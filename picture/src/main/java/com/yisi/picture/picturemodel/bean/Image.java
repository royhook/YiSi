package com.yisi.picture.picturemodel.bean;

/**
 * Created by chenql on 2017/8/30.
 * 最终展示出来的图片都叫Image
 */

public class Image {

    String url;//图片原质量Url

    String thumb_ur;//缩略图

    String scale_url;//压缩图

    public void setScale_url(String scale_url) {
        this.scale_url = scale_url;
    }

    public void setThumb_ur(String thumb_ur) {
        this.thumb_ur = thumb_ur;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getScale_url() {
        return scale_url;
    }

    public String getThumb_ur() {
        return thumb_ur;
    }

    public String getUrl() {
        return url;
    }
}
