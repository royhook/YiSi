package yisi.adplugin.business;

import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import yisi.adplugin.bean.Ad;
import yisi.adplugin.bean.BaseAd;
import yisi.adplugin.bean.ScreenAd;
import yisi.adplugin.place.LuomiScreenAd;

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
                mBaseAdPlace = new LuomiScreenAd();
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
        Ad luomiAd = new Ad();
        luomiAd.setApp_id("0a2176b02af2902d825a29ef71fb9d58");
        luomiAd.setAd_id("0a2176b02af2902d825a29ef71fb9d58");
        luomiAd.setWeight(10);
        luomiAd.setProvider(PLATFORM_LUOMI);
        ads.add(luomiAd);
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
}
