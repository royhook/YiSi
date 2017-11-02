package yisi.adplugin.bean;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * Created by chenql on 2017/6/22.
 */

public class AdConfig extends GenericJson {
    @Key
    boolean is_open;//广告总开关
    @Key
    RewardInsertAd reward_insert;


    public void setRewardAd(RewardInsertAd rewardInsertAd) {
        reward_insert = rewardInsertAd;
    }

    public RewardInsertAd getRewardAd() {
        return reward_insert;
    }
}
