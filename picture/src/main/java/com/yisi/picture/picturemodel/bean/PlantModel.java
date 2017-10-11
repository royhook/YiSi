package com.yisi.picture.picturemodel.bean;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.kinvey.java.model.KinveyMetaData;

import java.util.List;

/**
 * Created by chenql on 2017/9/4.
 * 套图模块
 */

public class PlantModel extends GenericJson {

    @Key("_id")
    private String id;
    @Key
    private String title;
    @Key
    private List<PlantType> model_list;

    @Key("_kmd")
    private KinveyMetaData meta;
    @Key("_acl")
    private KinveyMetaData.AccessControlList acl;

    public PlantModel() {
    }  //GenericJson classes must have a public empty constructor


    public void setTitle(String title) {
        this.title = title;
    }

    public void setModel_list(List<PlantType> model_list) {
        this.model_list = model_list;
    }

    public String getTitle() {
        return title;
    }

    public List<PlantType> getModel_list() {
        return model_list;
    }
}
