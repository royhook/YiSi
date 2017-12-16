package yisi.adplugin.place;

import android.text.TextUtils;
import android.view.View;

import com.etap.AdError;
import com.etap.EtapAdType;
import com.etap.EtapBuild;
import com.etap.EtapLib;
import com.etap.EtapNative;
import com.etap.IAdListener;

import java.util.List;
import java.util.Map;

import yisi.adplugin.bean.Ad;
import yisi.adplugin.bean.NativeAdInfo;
import yisi.adplugin.business.ScreenAdBusiness;
import yisi.adplugin.utils.KyxSDKGlobal;

/**
 * Created by chenql on 2017/12/15.
 */

public class BatScreenAd extends ScreenNativeAdPlace {
    EtapNative mNative;

    @Override
    public void startShowAd(final Ad ad, String appid, String adid) {
        EtapLib.init(KyxSDKGlobal.mContext, appid);
        EtapBuild.Builder build = new EtapBuild.Builder(KyxSDKGlobal.mContext, adid, EtapAdType.NATIVE.getType(), new IAdListener() {
            @Override
            public void onAdLoadFinish(Object o) {
                mNative = (EtapNative) o;
                List<com.etap.Ad> ads = mNative.getAds();
                if (ads.size() > 0) {
                    com.etap.Ad oAd = ads.get(0);
                    showAd(oAd);
                } else {
                    onAdFailed("no ad");
                }
            }

            @Override
            public void onAdError(AdError adError) {
                onAdFailed(adError.getMsg());
            }

            @Override
            public void onAdShowed() {
                onAdPresent();
            }

            @Override
            public void onAdClosed() {
                onAdSkip();
            }

            @Override
            public void onAdClicked() {
                onAdClick();
            }
        });
        build.setAdsNum(1);
        build.setCreatives(com.etap.Ad.AD_CREATIVE_SIZE_1200x627);
        EtapLib.load(build.build());
    }

    private void showAd(final com.etap.Ad ad) {
        Map<String, List<String>> creatives = ad.getCreatives();
        List<String> images = creatives.get("1200x627");
        if (images != null && images.size() > 0) {
            String url = images.get(0);
            if (TextUtils.isEmpty(url)) {
                onAdFailed("");
                return;
            }
            NativeAdInfo nativeAdInfo = new NativeAdInfo();
            nativeAdInfo.setImageUrl(url);
            nativeAdInfo.setIconUrl(ad.getIcon());
            nativeAdInfo.setDesc(ad.getDescription());
            nativeAdInfo.setTitle(ad.getName());
            showNativeAd(nativeAdInfo, new IAdCallback() {
                @Override
                public void onAdPresent(View view) {
                    mNative.registerView(view, ad);
                    ((ScreenAdBusiness) mBaseAdBusiness).mRelativeLayout.addView(view);
                }
            });

        } else {
            onAdFailed("no ad");
        }
    }
}
