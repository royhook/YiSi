package com.yisi.picture.picturemodel.bean;

import android.text.TextUtils;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.yisi.picture.baselib.utils.DeviceUtils;

/**
 * Created by chenql on 2017/9/4.
 * 套图模块分类 图片名字， 和图片主要显示的url
 */

public class PlantType extends GenericJson {
    @Key
    public int id;
    @Key
    public String img_url;
    @Key
    public String title;
    @Key
    public String title_cn;


    public void setTitle_cn(String title_cn) {
        this.title_cn = title_cn;
    }

    public String getTitle_cn() {
        return title_cn;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage_url(String image_url) {
        this.img_url = image_url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getImage_url() {
        return img_url;
    }

    public String getWebpImageUrl() {
        return img_url + "!/format/webp";
    }

    public String getTitle() {
        String lan = DeviceUtils.getSystemLaungue();
        if (!TextUtils.isEmpty(lan)) {
            if (lan.contains("zh") && !TextUtils.isEmpty(title_cn)) {
                return title_cn;
            }
        }
        return title;
    }
}
