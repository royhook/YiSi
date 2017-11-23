package yisi.adplugin.business;


import yisi.adplugin.place.AdMobInterstitialAd;
import yisi.adplugin.place.BatNativeInterstitialAd;
import yisi.adplugin.bean.Ad;
import yisi.adplugin.bean.BaseAd;
import yisi.adplugin.place.LuomiNative;

/**
 * Created by chenql on 2017/6/5.
 */

public class RewardBusiness extends BaseAdBusiness {

    private static RewardBusiness ourInstance = new RewardBusiness();

    public static RewardBusiness getInstance() {
        return ourInstance;
    }

    @Override
    protected BaseAd getAdPlanment() {
        return mAdconfig.getRewardAd();
    }

    @Override
    public void bindAdPlatform(Ad ad) {
        switch (ad.getProvider()) {
            case PLATFORM_ADMOB:
                mBaseAdPlace = new AdMobInterstitialAd();
                break;

            case PLATFORM_ADMOB_NATIVE:
                break;

            case PLATFORM_FACEBOOK:
                break;

            case PLATFORM_FB_NATIVE:

                break;
            case PLATFORM_BAT:
                mBaseAdPlace = new BatNativeInterstitialAd();
                break;

            case PLATFORM_LUOMI:
                mBaseAdPlace = new LuomiNative();
                break;

            default:
                break;
        }
    }

    @Override
    public String getPlanmentString() {
        return "Reward Ad";
    }
}
