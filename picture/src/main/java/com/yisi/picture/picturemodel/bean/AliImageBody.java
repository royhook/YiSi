package com.yisi.picture.picturemodel.bean;

/**
 * Created by chenql on 2017/6/1.
 */

public class AliImageBody {

    int ret_code;
    AliPageBean pagebean;

    public void setPagebean(AliPageBean pagebean) {
        this.pagebean = pagebean;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public int getRet_code() {
        return ret_code;
    }

    public AliPageBean getPagebean() {
        return pagebean;
    }
}
