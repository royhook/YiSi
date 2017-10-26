package yisi.adplugin.business;

import yisi.adplugin.utils.KyxSDKGlobal;
import yisi.adplugin.bean.Ad;
import yisi.adplugin.bean.AdTracker;

/**
 * Created with Android Studio
 * </p>
 * Authour:xiaxf
 * </p>
 * Date:16/5/4.
 */

public abstract class BaseAdPlace implements IAdCallProxy {
    protected Ad ad;


    public Ad getAd() {
        return ad;
    }

    public BaseAdBusiness mBaseAdBusiness;

    public void setBaseAdBusiness(BaseAdBusiness baseAdBusiness) {
        mBaseAdBusiness = baseAdBusiness;
    }

    public void startAd(Ad ad, String appid, String adid) {
        this.ad = ad;
        startShowAd(ad, appid, adid);
    }

    public abstract void startShowAd(Ad ad, String appid, String adid);

    public void goNextAd() {
        if (isForget())
            return;

        if (mBaseAdBusiness != null) {
            KyxSDKGlobal.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mBaseAdBusiness.goNextAd();
                }
            }, 1000);
        }
    }

    @Override
    public void onAdRequest() {
        if (isForget())
            return;

        if (mBaseAdBusiness != null)
            mBaseAdBusiness.onAdRequest();
    }

    @Override
    public void onAdPresent() {
        if (isForget())
            return;

        if (mBaseAdBusiness != null)
            mBaseAdBusiness.onAdPresent();
    }

    @Override
    public void onAdClick() {
        if (isForget())
            return;

        if (mBaseAdBusiness != null)
            mBaseAdBusiness.onAdClick();
    }

    @Override
    public void onAdSkip() {
        if (isForget())
            return;

        if (mBaseAdBusiness != null)
            mBaseAdBusiness.onAdSkip();
    }

    @Override
    public void onAdFailed(String message) {
        if (isForget())
            return;

        if (mBaseAdBusiness != null) {
            //记录每个厂商失败的次数
            AdTracker adTracker = mBaseAdBusiness.mAdTracker.get(ad.getProvider());
            //没有根据厂商取到adtracker
            if (adTracker == null) {
                adTracker = new AdTracker();
                adTracker.setErrorTimes(1);
            }
            //已经拿到了adtracker
            else {
                //更新错误次数
                int errorTime = adTracker.getErrorTimes();
                errorTime++;
                adTracker.setErrorTimes(errorTime);
            }
            mBaseAdBusiness.mAdTracker.put(ad.getProvider(), adTracker);
            mBaseAdBusiness.onAdFailed(message);
        }
    }

    @Override
    public void onAdLoad() {
        if (isForget()) {
            return;
        }

        if (mBaseAdBusiness != null) {
            mBaseAdBusiness.onAdLoad();
        }
    }

    //判断当前广告位是否已经被遗忘了,如果已经被遗忘 就不要统计当前事件了，以免影响到别的广告位
    private boolean isForget() {
        return mBaseAdBusiness == null || ad == null || mBaseAdBusiness.mAdPlatform != ad.getProvider();
    }
}
