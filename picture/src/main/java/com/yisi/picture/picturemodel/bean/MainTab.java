package com.yisi.picture.picturemodel.bean;

import android.text.TextUtils;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.kinvey.java.model.KinveyMetaData;
import com.yisi.picture.baselib.utils.DeviceUtils;

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
    @Key
    private String cn_title;
    private String en_title;

    public MainTab() {
    }  //GenericJson classes must have a public empty constructor

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String getTitle() {
        String lan = DeviceUtils.getSystemLaungue();
        if (!TextUtils.isEmpty(lan)) {
            if (lan.contains("zh")) {
                return cn_title;
            }
        }
        return en_title;
    }

    public Integer getType_id() {
        return type_id;
    }


    public void setCn_title(String cn_title) {
        this.cn_title = cn_title;
    }

    public void setEn_title(String en_title) {
        this.en_title = en_title;
    }
}
