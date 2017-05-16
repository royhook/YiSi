package com.yisi.picture.picturemodel.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by roy on 2017/2/16.
 */

public class PlantBrowse extends BmobObject{
    private int plant_id;//套图id 为了查询
    private String img_url;//套图展示的缩略图
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setPlant_id(int plant_id) {
        this.plant_id = plant_id;
    }

    public String getImg_url() {
        return img_url;
    }

    public int getPlant_id() {
        return plant_id;
    }
}

