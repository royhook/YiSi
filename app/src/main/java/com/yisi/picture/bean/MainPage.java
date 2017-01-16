package com.yisi.picture.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by roy on 2017/1/21.
 */

public class MainPage extends BmobObject {
    String title;
    int type_id;

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
