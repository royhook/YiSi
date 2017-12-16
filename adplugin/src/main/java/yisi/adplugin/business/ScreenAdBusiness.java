package yisi.adplugin.business;

import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import yisi.adplugin.bean.Ad;
import yisi.adplugin.bean.BaseAd;
import yisi.adplugin.bean.ScreenAd;
import yisi.adplugin.place.BatScreenAd;

/**
 * Created by chenql on 2017/11/22.
 */

public class ScreenAdBusiness extends BaseAdBusiness {

    ScreenAd mScreenAd;

    IAdCallback mAdCallback;

    public boolean isDoingClick = false;

    public void setAdCallback(IAdCallback adCallback) {
        mAdCallback = adCallback;
    }

    private static ScreenAdBusiness ourInstance = new ScreenAdBusiness();

    public static ScreenAdBusiness getInstance() {

        return ourInstance;
    }

    private ScreenAdBusiness() {
        initScreenAd();
    }

    public RelativeLayout mRelativeLayout;

    @Override
    protected BaseAd getAdPlanment() {
        return mScreenAd;
    }

    @Override
    public void bindAdPlatform(Ad ad) {
        switch (ad.getProvider()) {
            case PLATFORM_LUOMI:
                break;
            case PLATFORM_BAT:
                mBaseAdPlace = new BatScreenAd();
                break;
            default:
                break;
        }
    }

    @Override
    public String getPlanmentString() {
        return "Screen Ad";
    }

    public void initScreenAd() {
        List<Ad> ads = new ArrayList<>();
        Ad batmob = new Ad();
        batmob.setApp_id("WWDT0A60JKTXL1YECO6WUGUA");
        batmob.setAd_id("12550_68533");
        batmob.setWeight(10);
        batmob.setProvider(BaseAdBusiness.PLATFORM_BAT);
        ads.add(batmob);
        mScreenAd = new ScreenAd();
        mScreenAd.setAds(ads);
    }

    @Override
    public void onAdSkip() {
        super.onAdSkip();
        if (mAdCallback != null)
            mAdCallback.onSkip();
    }

    @Override
    public void onAdClick() {
        super.onAdClick();
        isDoingClick = true;
    }

    @Override
    public void onAdFailed(String message) {
        if (mAdCallback != null)
            mAdCallback.onUnAvaliable();
        super.onAdFailed(message);
    }
}
