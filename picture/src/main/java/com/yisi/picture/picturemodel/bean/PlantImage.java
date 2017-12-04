package com.yisi.picture.picturemodel.bean;

import android.text.TextUtils;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.kinvey.java.model.KinveyMetaData;
import com.yisi.picture.baselib.utils.DeviceUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chenql on 2017/9/11.
 */

public class PlantImage extends GenericJson implements Serializable {
    @Key("_id")
    private String id;
    @Key
    private String title;
    @Key
    private Integer plant_id;
    @Key
    private int coin = 200;
    @Key
    String img_url;
    @Key
    List<Image> image_list;
    @Key
    private String title_cn;
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

    public String getWebpImageUrl() {
        return img_url + "!normalimage";
    }

    public String getFwfhUrl(int width, int height) {
        return img_url + "!/fwfh/" + width + "x" + height + "/rotate/auto";
    }


    public List<Image> getImage_list() {
        return image_list;
    }

    public String getName() {
        String lan = DeviceUtils.getSystemLaungue();
        if (!TextUtils.isEmpty(lan)) {
            if (lan.contains("zh") && !TextUtils.isEmpty(title_cn)) {
                return title_cn;
            }
        }
        return title;
    }

    public String getId() {
        return id;
    }

    public int getCoin() {
        return coin;
    }

    public String getScaleUrl(int width, int height) {
        return img_url + "!/crop/" + width + "x" + height + "a80a60";
    }

    public String getWrapWidth(int height) {
        return img_url + "!/fh/" + height;
    }

    public String getScale(int scale) {
        return img_url + "!/scale/" + scale;
    }

    public void setId(String id) {
        this.id = id;
    }
}
