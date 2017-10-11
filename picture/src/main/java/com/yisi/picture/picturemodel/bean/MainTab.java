package com.yisi.picture.picturemodel.bean;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.kinvey.java.model.KinveyMetaData;

/**
 * Created by roy on 2017/1/21.
 */

public class MainTab extends GenericJson {
    @Key("_id")
    private String id;
    @Key
    private String title;
    @Key
    private Integer type_id;
    @Key("_kmd")
    private KinveyMetaData meta;
    @Key("_acl")
    private KinveyMetaData.AccessControlList acl;

    public MainTab() {
    }  //GenericJson classes must have a public empty constructor

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getType_id() {
        return type_id;
    }
}
