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

    @Key
    String type_id;//散图的图片类型id


    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getType_id() {
        return type_id;
    }

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

}
