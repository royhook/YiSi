package com.yisi.picture.picturemodel.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by roy on 2017/1/19.
 */

public class YiSiImage extends BmobObject {
    String img_url;
    int type_id;
    int page;
    String img_scale;

    public void setImg_scale(String img_scale) {
        this.img_scale = img_scale;
    }

    public String getImg_scale() {
        return img_scale;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setType_id(int find_id) {
        this.type_id = find_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getImg_url() {
        return img_url;
    }
}
