package com.yisi.picture.picturemodel.bean;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.kinvey.java.model.KinveyMetaData;

/**
 * Created by chenql on 2017/10/11.
 */

public class GlobalSetting extends GenericJson {
    //是第几期推荐,默认是第一期
    @Key
    int recommand_data = 1;
    @Key("_id")
    private String id;

    @Key("_kmd")
    private KinveyMetaData meta;
    @Key("_acl")
    private KinveyMetaData.AccessControlList acl;

    public void setRecommand_data(int recommand_data) {
        this.recommand_data = recommand_data;
    }

    public int getRecommand_data() {
        return recommand_data;
    }
}
