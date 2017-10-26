package yisi.adplugin.bean;

/**
 * Created by chenql on 2017/6/22.
 */

public class AdConfig {

    boolean is_open;//广告总开关
    RewardAd mRewardAd;


    public void setRewardAd(RewardAd rewardAd) {
        mRewardAd = rewardAd;
    }

    public RewardAd getRewardAd() {
        return mRewardAd;
    }
}
