package com.yisi.picture.picturemodel.bean;

/**
 * Created by chenql on 2017/5/29.
 */

public class AliPicture {
    int id;
    String name;
    String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
