package com.yisi.picture.picturemodel.bean;

import java.util.List;

/**
 * Created by chenql on 2017/6/1.
 */

public class AliImage {
    String typeName;
    String title;
    List<AliImageUrl> list;

    public void setList(List<AliImageUrl> list) {
        this.list = list;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<AliImageUrl> getList() {
        return list;
    }

    public String getTitle() {
        return title;
    }

    public String getTypeName() {
        return typeName;
    }
}
