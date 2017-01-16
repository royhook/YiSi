package com.yisi.picture.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by roy on 2017/1/19.
 */

public class HotImage extends BmobObject {
    String imgUrl;

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
