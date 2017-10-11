package com.yisi.picture.picturemodel.bean;

import com.google.api.client.util.Key;

/**
 * Created by chenql on 2017/10/11.
 */

public class RecommandImage {
    @Key
    String img_url;//图片原质量Url

    @Key
    int recommand_data;

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getRecommand_data() {
        return recommand_data;
    }

    public String getImg_url() {
        return img_url;
    }
}
