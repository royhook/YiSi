package yisi.adplugin.place;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import yisi.adplugin.bean.Ad;
import yisi.adplugin.utils.KyxSDKGlobal;

/**
 * Created by chenql on 2017/12/15.
 */

public class AdMobRewardVideo extends InterstitialPreloadAdPlace {
    private RewardedVideoAd mRewardedVideoAd;

    @Override
    public void startShowAd(Ad ad, String appid, String adid) {
        onAdRequest();
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(KyxSDKGlobal.mContext);
        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                onAdLoad();
            }

            @Override
            public void onRewardedVideoAdOpened() {
                onAdPresent();
            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {
                onAdSkip();
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
                addCoin(1000);
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {
                onAdClick();
            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {

            }
        });
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("FB378E95C6CB5D40953B6BEB6286D3A9").build();
        mRewardedVideoAd.loadAd(adid, adRequest);
    }

    @Override
    public void showAd() {
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
    }
}
