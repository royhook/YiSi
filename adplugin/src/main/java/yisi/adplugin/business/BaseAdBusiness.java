package yisi.adplugin.business;

import android.util.Log;
import android.util.SparseArray;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import yisi.adplugin.bean.Ad;
import yisi.adplugin.bean.AdConfig;
import yisi.adplugin.bean.AdTracker;
import yisi.adplugin.bean.BaseAd;
import yisi.adplugin.utils.KyxSDKGlobal;

/**
 * Created with Android Studio
 * </p>
 * Authour:xiaxf
 * </p>
 * Date:16/4/27.
 */

public abstract class BaseAdBusiness implements IAdCallProxy {

    /**********  新增广告位 需要在OwnAdStatistics#getAdTypeByPositionId方法中注册 统计自主广告 ***/

    public static final int PLATFORM_FACEBOOK = 1;//FaceBook
    public static final int PLATFORM_ADMOB = 2;//Admob
    public static final int PLATFORM_FB_NATIVE = 5;//FB Native
    public static final int PLATFORM_ADMOB_NATIVE = 6;//Admob Native
    public static final int PLATFORM_BAT_NATIVE = 8;//Bat Media广告
    public static final int PLATFORM_BAT_FULL = 9;//Bat Full广告
    public static final int PLATFORM_LUOMI = 3;//洛米广告


    public List<Ad> mAdList = new ArrayList<>();
    public int mAdPlatform;
    public BaseAdPlace mBaseAdPlace;

    public int mAdPresentTimes = 0;//记录广告展示数量
    public int mAdLoopErrorTimes;//失败循环的次数(广告列表中广告源没有一次展现)
    public int mAdAllowedErrorLoopTimes = 2;//允许失败循环的次数
    public int mAdAllowedProviderErrorTimes = 3;//厂商允许失败的次数

    public SparseArray<AdTracker> mAdTracker;//每个广告位对应多个厂商，用map保存，key为privider

    public static AdConfig mAdconfig = new AdConfig();


    /***
     * 请求广告数据
     */
    public void requestAdData() {
        if (mAdTracker == null) {
            mAdTracker = new SparseArray<>();
        }
        //重新初始化 请求错误数量
        mAdPresentTimes = 0;

        if (mAdconfig == null) {
            return;
        }
        BaseAd baseAd = getAdPlanment();
        if (!isPlantmentAvaliable(baseAd)) {
            return;
        }
        if (mAdList.size() == 0) {
            if (baseAd.getAds() == null)
                return;
            //判断ad是否开启
            for (Ad ad : baseAd.getAds()) {
                if (ad.is_open()) {
                    mAdList.add(ad);
                }
            }
        }
        //配置广告数量!=0 , 并且广告不是全都关闭的情况下，gonext。
        goNextAd();
    }

    protected abstract BaseAd getAdPlanment();

    protected boolean isPlantmentAvaliable(BaseAd baseAd) {
        //为空，或者当前广告位关闭时，不可用
        if (baseAd == null || !baseAd.is_open() || baseAd.getAds() == null || baseAd.getAds().size() == 0)
            return false;

        List<Ad> ads = baseAd.getAds();

        //判断当前广告位内广告源都关闭的时候，不可用
        for (int i = 0; i < ads.size(); i++) {
            //有一个广告源开启这个广告位就是可用的
            if (ads.get(i).is_open()) {
                return true;
            }
        }
        return false;
    }

    public String getPlanmentString() {
        return "";
    }

    private void sortAdList() {
        Collections.sort(mAdList, new Comparator<Ad>() {
            @Override
            public int compare(Ad arg0, Ad arg1) {
                Integer time0 = arg0.getWeight();
                Integer time1 = arg1.getWeight();
                return time1.compareTo(time0);
            }
        });
    }

    public void goNextAd() {
        mBaseAdPlace = null;
        //有广告返回
        if (mAdList != null && mAdList.size() > 0) {
            //重新按权重排序
            sortAdList();
            Ad ad = mAdList.get(0);
            mAdList.remove(ad);
            if (needBindAdPlatform(ad)) {
                mAdPlatform = ad.getProvider();
                bindAdPlatform(ad);
                if (mBaseAdPlace != null) {
                    mBaseAdPlace.setBaseAdBusiness(this);
                    onAdPlaceValid(ad);
                } else {
                    goNextAd();
                }
            } else {
                goNextAd();
            }
        } else {
            //没有广告返回 或者广告都已经过期，或者都显示过了
            onAdListInvalid();
        }
    }

    /**
     * 判断当前广告源是否可用
     *
     * @param ad
     * @return
     */
    protected boolean needBindAdPlatform(Ad ad) {
        //如果广告源关闭，不可用
        if (!ad.is_open()) {
            return false;
        } else if (isBeyondErrorTimes(ad))
            return false;

        return true;
    }

    //是否广告厂商失败次数超过最大次数
    protected boolean isBeyondErrorTimes(Ad ad) {
        AdTracker adTracker = mAdTracker.get(ad.getProvider());
        //该厂商还未失败过
        if (adTracker == null) {
            return false;
        } else {
            int errorTimes = adTracker.getErrorTimes();
            return errorTimes >= mAdAllowedProviderErrorTimes;
        }
    }


    public abstract void bindAdPlatform(Ad ad);

    /**
     * 没有可展示的广告
     */
    public void onAdListInvalid() {
        KyxSDKGlobal.postDelayed(new Runnable() {
            @Override
            public void run() {
                //先判断是否为失败循环
                if (mAdPresentTimes == 0) {
                    Log.d("yisi", "完全失败:" + mAdPlatform);
                    mAdLoopErrorTimes++;
                }
                if (mAdLoopErrorTimes >= mAdAllowedErrorLoopTimes) {
                    Log.d("yisi", "已经达到失败次数:" + mAdPlatform);
                    return;
                }
                requestAdData();
            }
        }, 2000);
    }

    /**
     * 请求广告列表失败
     */
    public void onAdListError() {
    }

    /**
     * 广告位可用
     *
     * @param ad
     */
    protected void onAdPlaceValid(Ad ad) {
        mBaseAdPlace.startAd(ad, ad.getApp_id(), ad.getAd_id());
    }

    @Override
    public void onAdRequest() {
        onEvent("Request");
    }

    @Override
    public void onAdPresent() {
        onEvent("Present");

        mAdPresentTimes++;
    }

    @Override
    public void onAdClick() {
        onEvent("Click");

    }

    @Override
    public void onAdFailed(String message) {
        KyxSDKGlobal.postDelayed(new Runnable() {
            @Override
            public void run() {
                goNextAd();
            }
        }, 1000);
        onEvent("Failed");
    }

    public void onEvent(String adEvent) {
        onEvent(adEvent, "");
    }

    public void onEvent(String adEvent, String message) {
        String ad_origen = "";
        switch (mAdPlatform) {
            case PLATFORM_ADMOB:
                ad_origen = "Admob";
                break;

            case PLATFORM_FACEBOOK:
                ad_origen = "FaceBook";
                break;

            case PLATFORM_FB_NATIVE:
                ad_origen = "FaceBook Native";
                break;

            case PLATFORM_ADMOB_NATIVE:
                ad_origen = "Admob Native";
                break;

            case PLATFORM_BAT_NATIVE:
                ad_origen = "BAT Native";
                break;

            case PLATFORM_LUOMI:
                ad_origen = "Luomi Native";
                break;
            default:

                break;
        }

        Log.d("osevent", "origen=" + ad_origen + "    " + "placement=" + getPlanmentString() + "    " + "adEvent=" + adEvent);
    }

    @Override
    public void onAdSkip() {
        goNextAd();
    }

    @Override
    public void onAdLoad() {
        onEvent("Load");
    }

}
