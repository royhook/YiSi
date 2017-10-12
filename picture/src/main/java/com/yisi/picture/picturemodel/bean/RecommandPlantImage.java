package com.yisi.picture.picturemodel.bean;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.kinvey.java.model.KinveyMetaData;

import java.util.List;

/**
 * Created by chenql on 2017/9/11.
 */

public class RecommandPlantImage extends GenericJson {
    @Key("_id")
    private String id;
    @Key
    private String title;
    @Key
    String img_url;
    @Key
    List<Image> img_list;
    //这是第几期的套图推荐
    @Key
    int recommand_data;
    @Key("_kmd")
    private KinveyMetaData meta;
    @Key("_acl")
    private KinveyMetaData.AccessControlList acl;
    @Key
    boolean rotation;

    public void setRotation(boolean rotation) {
        this.rotation = rotation;
    }

    public boolean isRotation() {
        return rotation;
    }

    public RecommandPlantImage() {
    }  //GenericJson classes must have a public empty constructor


    public void setImage_list(List<Image> image_list) {
    this.img_list = image_list;
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
        return img_list;
    }

    public String getName() {
        return title;
    }
}
