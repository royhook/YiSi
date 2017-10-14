package com.yisi.picture.picturemodel.bean;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.kinvey.java.model.KinveyMetaData;

import java.util.List;

/**
 * Created by chenql on 2017/9/11.
 */

public class PlantImage extends GenericJson {
    @Key("_id")
    private String id;
    @Key
    private String title;
    @Key
    private Integer plant_id;
    @Key
    String img_url;
    @Key
    List<Image> image_list;
    @Key("_kmd")
    private KinveyMetaData meta;
    @Key("_acl")
    private KinveyMetaData.AccessControlList acl;

    public PlantImage() {
    }  //GenericJson classes must have a public empty constructor


    public void setImage_list(List<Image> image_list) {
        this.image_list = image_list;
    }

    public void setImage_url(String image_url) {
        this.img_url = image_url;
    }

    public void setName(String name) {
        this.title = name;
    }

    public String getImage_url() {
        return img_url;
    }

    public List<Image> getImage_list() {
        return image_list;
    }

    public String getName() {
        return title;
    }
}