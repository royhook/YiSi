package com.yisi.picture.picturemodel.bean;

import java.util.List;

/**
 * Created by chenql on 2017/5/29.
 */

public class AliBody {
    List<AliAlbum> list;
    int ret_code;

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public int getRet_code() {
        return ret_code;
    }

    public void setList(List<AliAlbum> list) {
        this.list = list;
    }

    public List<AliAlbum> getList() {
        return list;
    }
}
