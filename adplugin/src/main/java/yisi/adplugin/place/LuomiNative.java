package yisi.adplugin.place;

import android.view.View;

import com.luomi.lm.ad.ADType;
import com.luomi.lm.ad.Advertisement;
import com.luomi.lm.ad.DRAgent;
import com.luomi.lm.ad.IAdNativeSuccessBack;
import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.utils.ActivityLifestyle;

import yisi.adplugin.bean.Ad;
import yisi.adplugin.bean.NativeAdInfo;
import yisi.adplugin.business.OnNativeAdShowCallback;

/**
 * Created by chenql on 2017/11/23.
 */

public class LuomiNative extends InterstitialPreloadAdPlace {
    Advertisement mAdvertisement;
    private NativeAdInfo mNativeAdInfo;

    @Override
    public void showAd() {
        showNativeAd(mNativeAdInfo, new OnNativeAdShowCallback() {
            @Override
            public void onViewCreated(View view) {
                DRAgent.getInstance().uploadShow(ActivityLifestyle.getInstance().getActivity(), mAdvertisement);
            }

            @Override
            public void onAdClick(View view) {
                DRAgent.getInstance().uploadShowCLick(ActivityLifestyle.getInstance().getActivity(), mAdvertisement);
                goNextAd();
            }
        });
    }

    @Override
    public void startShowAd(Ad ad, String appid, String adid) {
        DRAgent.getInstance().getOpenNativeAd(ActivityLifestyle.getInstance().getActivity(), ADType.MESSAGE_BIG_IMG, new IAdNativeSuccessBack() {
            @Override
            public void onSuccess(final Advertisement advertisement) {
                YiSiApplication.postMainThread(new Runnable() {
                    @Override
                    public void run() {
                        onAdLoad();
                        mAdvertisement = advertisement;
                        mNativeAdInfo = new NativeAdInfo();
                        String text = advertisement.getWenzi();
                        String title = text.substring(0, 2);
                        mNativeAdInfo.setTitle(title);
                        mNativeAdInfo.setDesc(advertisement.getWenzi());
                        mNativeAdInfo.setImageUrl(advertisement.getImgurl());
                        mNativeAdInfo.setIconUrl(advertisement.getImgurl());
                    }
                });

            }

            @Override
            public void onFailed(final String infoStr) {
                YiSiApplication.postMainThread(new Runnable() {
                    @Override
                    public void run() {
                        onAdFailed(infoStr);
                    }
                });
            }
        });
    }
}
