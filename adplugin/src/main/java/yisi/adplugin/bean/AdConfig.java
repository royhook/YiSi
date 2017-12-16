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
    @Key
    RewardVideoAd reward_video;
    @Key
    ScreenAd screen_ad;

    public void setScreen_ad(ScreenAd screen_ad) {
        this.screen_ad = screen_ad;
    }

    public ScreenAd getScreen_ad() {
        return screen_ad;
    }

    public void setRewardAd(RewardInsertAd rewardInsertAd) {
        reward_insert = rewardInsertAd;
    }

    public RewardInsertAd getRewardAd() {
        return reward_insert;
    }

    public void setReward_video(RewardVideoAd reward_video) {
        this.reward_video = reward_video;
    }

    public RewardVideoAd getReward_video() {
        return reward_video;
    }

    public RewardInsertAd getReward_insert() {
        return reward_insert;
    }
}
