package yisi.adplugin.bean;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * Created by chenql on 2017/7/20.
 */

public class Ad extends GenericJson {
    @Key
    private String app_id;
    @Key
    private String ad_id;
    @Key
    private int weight;
    @Key
    private int provider;
    @Key
    private int chance = 0;//几率
    @Key
    private boolean is_open = true;
    @Key
    private boolean is_wifi = true;

    public void setIs_wifi(boolean is_wifi) {
        this.is_wifi = is_wifi;
    }

    public boolean is_wifi() {
        return is_wifi;
    }

    public void setIs_open(boolean is_open) {
        this.is_open = is_open;
    }

    public boolean is_open() {
        return is_open;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public int getChance() {
        return chance;
    }

    public void setProvider(int provider) {
        this.provider = provider;
    }

    public int getProvider() {
        return provider;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
