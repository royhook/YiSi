package com.yisi.picture.picturemodel.bean;

import cn.bmob.v3.BmobObject;

import static android.R.attr.type;

/**
 * Created by roy on 2017/2/17.
 */

public class Plant extends BmobObject {
    private String img_url;
    private int type_id;//对应套图的type

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getType() {
        return type_id;
    }

    public void setType(int type) {
        this.type_id = type;
    }

    public String getImg_url() {
        return img_url;
    }
}
