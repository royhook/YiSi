package yisi.adplugin.business;


import yisi.adplugin.bean.Ad;
import yisi.adplugin.bean.BaseAd;
import yisi.adplugin.place.AdMobRewardVideo;

/**
 * Created by chenql on 2017/6/5.
 */

public class RewardVideoBusiness extends BaseAdBusiness {

    private static RewardVideoBusiness ourInstance = new RewardVideoBusiness();

    public static RewardVideoBusiness getInstance() {
        return ourInstance;
    }

    @Override
    protected BaseAd getAdPlanment() {
        return mAdconfig.getReward_video();
    }

    @Override
    public void bindAdPlatform(Ad ad) {
        switch (ad.getProvider()) {
            case PLATFORM_ADMOB:
                mBaseAdPlace = new AdMobRewardVideo();
                break;


            default:

                break;
        }
    }

    @Override
    public String getPlanmentString() {
        return "Reward Video Ad";
    }

    @Override
    public void onAdFailed(String message) {

        super.onAdFailed(message);
    }
}
