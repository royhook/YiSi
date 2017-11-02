package yisi.adplugin.bean;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

import java.util.List;

/**
 * Created by chenql on 2017/8/16.
 * 场景广告位
 */

public class BaseAd extends GenericJson {

    @Key
    private boolean is_open = true;//控制该场景广告位是否打开 true 打开 false 关闭

    @Key
    private List<Ad> ad_list;//控制该场景广告位的广告下发列表


    public void setAds(List<Ad> ads) {
        ad_list = ads;
    }

    public List<Ad> getAds() {
        return ad_list;
    }

    public boolean is_open() {
        return is_open;
    }

    public void setIs_open(boolean is_open) {
        this.is_open = is_open;
    }
}
