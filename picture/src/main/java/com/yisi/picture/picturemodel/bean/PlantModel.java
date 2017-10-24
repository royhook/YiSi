package com.yisi.picture.picturemodel.bean;

import android.text.TextUtils;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.kinvey.java.model.KinveyMetaData;
import com.yisi.picture.baselib.utils.DeviceUtils;

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
    @Key
    private int weight;
    @Key
    private String titile_cn;

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public PlantModel() {
    }  //GenericJson classes must have a public empty constructor


    public void setTitle(String title) {
        this.title = title;
    }

    public void setModel_list(List<PlantType> model_list) {
        this.model_list = model_list;
    }

    public String getTitle() {
        String lan = DeviceUtils.getSystemLaungue();
        if (!TextUtils.isEmpty(lan)) {
            if (lan.contains("zh") && !TextUtils.isEmpty(titile_cn)) {
                return titile_cn;
            }
        }
        return title;
    }

    public List<PlantType> getModel_list() {
        return model_list;
    }
}
