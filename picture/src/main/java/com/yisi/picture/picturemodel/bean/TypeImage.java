package com.yisi.picture.picturemodel.bean;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.kinvey.java.model.KinveyMetaData;

/**
 * Created by chenql on 2017/12/5.
 */

public class TypeImage extends GenericJson {

    @Key("_id")
    private String id;
    @Key
    private Image type_img;
    @Key
    private Integer type_id;
    @Key("_kmd")
    private KinveyMetaData meta;
    @Key("_acl")
    private KinveyMetaData.AccessControlList acl;

    public TypeImage() {
    }  //GenericJson classes must have a public empty constructor

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setImage(Image type_img) {
        this.type_img = type_img;
    }

    public Image getImage() {
        return type_img;
    }
}
