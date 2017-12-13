package com.yisi.picture.picturemodel.bean;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * Created by chenql on 2017/8/30.
 * 最终展示出来的图片都叫Image
 */

public class Image extends GenericJson {

    @Key("_id")
    private String id;
    @Key
    private String img_url;

    public Image() {
    }  //GenericJson classes must have a public empty constructor

    public void setUrl(String url) {
        this.img_url = url;
    }

    public String getUrl() {
        return img_url;
    }

    public String getWebpUrl() {
        return img_url + "!/format/webp";
    }

    public String getScaleUrl(int width, int height) {
        return img_url + "!/crop/" + width + "x" + height + "a80a60";
    }

    public String getWrapWidth(int height) {
        return img_url + "!/fh/" + height;
    }

    public String getFwfhUrl(int width, int height) {
        return img_url + "!/fwfh/" + width + "x" + height + "/rotate/auto";
    }

    public String getBigImageUrl() {
        return img_url + "!bigimg";
    }

    public String getNormalImageUrl() {
        return img_url + "!normalimage";
    }
}
