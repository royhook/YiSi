package com.yisi.picture.picturemodel.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by chenql on 2017/5/29.
 */

public class AliAlbum implements MultiItemEntity {
    String name;
    List<AliPicture> list;

    public void setName(String name) {
        this.name = name;
    }

    public void setList(List<AliPicture> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public List<AliPicture> getList() {
        return list;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
