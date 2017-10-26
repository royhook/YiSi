package yisi.adplugin.place;

import android.view.View;

import com.etap.AdError;
import com.etap.EtapAdType;
import com.etap.EtapBuild;
import com.etap.EtapInterstitial;
import com.etap.EtapLib;
import com.etap.EtapRectangleBanner;
import com.etap.IAdListener;
import com.yisi.picture.baselib.utils.LogUtils;

import yisi.adplugin.utils.KyxSDKGlobal;
import yisi.adplugin.bean.Ad;

/**
 * Created by chenql on 2017/9/4.
 */

public class BatNativeInterstitialAd extends InterstitialPreloadAdPlace {

    private EtapInterstitial interstitialAd;
    private EtapRectangleBanner rectangleBannerAd;

    @Override
    public void startShowAd(Ad ad, String appid, String adid) {
        LogUtils.d(appid);
        LogUtils.d(adid);
        onAdRequest();
        EtapLib.init(KyxSDKGlobal.mContext, appid);
        EtapBuild.Builder build = new EtapBuild.Builder(KyxSDKGlobal.mContext,
                adid,
                EtapAdType.Banner.MEDIUM_300X250.getType(),
                new IAdListener() {
                    @Override
                    public void onAdLoadFinish(Object obj) {
                        if (obj != null) {
                            if (obj instanceof EtapInterstitial) {
                                interstitialAd = (EtapInterstitial) obj;
                            } else if (obj instanceof EtapRectangleBanner) {
                                rectangleBannerAd = (EtapRectangleBanner) obj;
                            }
                            onAdLoad();
                        } else {
                            onAdFailed("obj is null");
                        }
                    }

                    @Override
                    public void onAdError(AdError error) {
                        onAdFailed(error.name());
                    }

                    @Override
                    public void onAdClosed() {
                        onAdSkip();
                    }


                    @Override
                    public void onAdShowed() {
                        onAdPresent();
                    }

                    @Override
                    public void onAdClicked() {
                        onAdClick();
                        goNextAd();
                    }
                });

        EtapLib.load(build.build());
    }

    @Override
    public void showAd() {
        if (interstitialAd != null && interstitialAd.isAdLoaded()) {
            interstitialAd.show();
        }
        if (rectangleBannerAd != null && rectangleBannerAd.isAdLoaded()) {
            View view = rectangleBannerAd.getView();
            createWindowView(view, ad.getChance());
        }
    }
}
