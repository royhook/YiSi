package com.yisi.picture.picturemodel.bean;

import java.util.List;

/**
 * Created by chenql on 2017/6/1.
 */

public class AliPageBean {
    int allPages;
    List<AliImage> contentlist;

    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

    public void setContentlist(List<AliImage> contentlist) {
        this.contentlist = contentlist;
    }

    public int getAllPages() {
        return allPages;
    }

    public List<AliImage> getContentlist() {
        return contentlist;
    }
}
