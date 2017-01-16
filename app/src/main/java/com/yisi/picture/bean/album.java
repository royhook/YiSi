package com.yisi.picture.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by roy on 2017/1/24.
 */

public class Album extends BmobObject {
    String name;
    String img_url;
    int type;
    int weight;

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setTitle(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getTitle() {
        return name;
    }
}
