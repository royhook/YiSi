package yisi.adplugin.place;

import android.annotation.SuppressLint;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import yisi.adplugin.bean.Ad;
import yisi.adplugin.utils.KyxSDKGlobal;

/**
 * Created by chenql on 2017/5/12.
 */

public class AdMobInterstitialAd extends InterstitialPreloadAdPlace {
    InterstitialAd mInterstitialAd;

    @SuppressLint("MissingPermission")
    @Override
    public void startShowAd(Ad ad, String appid, String adid) {
        onAdRequest();
        mInterstitialAd = new InterstitialAd(KyxSDKGlobal.mContext);
        mInterstitialAd.setAdUnitId(adid);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                onAdSkip();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                onAdFailed("admob error code:" + i);
            }

            @Override
            public void onAdLoaded() {
                onAdLoad();
            }

            @Override
            public void onAdOpened() {
                onAdPresent();
            }

            @Override
            public void onAdClicked() {
                onAdClick();
            }
        });
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("FB378E95C6CB5D40953B6BEB6286D3A9").build();
        mInterstitialAd.loadAd(adRequest);
    }


    @Override
    public void showAd() {
        try {
            mInterstitialAd.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
