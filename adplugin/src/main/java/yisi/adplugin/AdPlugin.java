package yisi.adplugin;

import android.widget.RelativeLayout;

import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.android.store.DataStore;
import com.kinvey.java.store.StoreType;
import com.yisi.picture.baselib.application.YiSiApplication;

import java.util.List;

import yisi.adplugin.bean.AdConfig;
import yisi.adplugin.business.BaseAdBusiness;
import yisi.adplugin.business.IAdCallback;
import yisi.adplugin.business.RewardBusiness;
import yisi.adplugin.business.ScreenAdBusiness;
import yisi.adplugin.place.InterstitialPreloadAdPlace;
import yisi.adplugin.utils.KyxSDKGlobal;

/**
 * Created by chenql on 2017/10/26.
 */

public class AdPlugin {
    /**
     * 展示广告
     */
    public static void showRewardAd(final IAdCallback callback) {
        KyxSDKGlobal.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                InterstitialPreloadAdPlace adPlace = (InterstitialPreloadAdPlace) RewardBusiness.getInstance().mBaseAdPlace;
                if (adPlace != null) {
                    adPlace.setIAdCallback(callback);
                    adPlace.show();
                }
            }
        });
    }


    /**
     * 请求广告
     */
    private static void requestRewardAd() {
        RewardBusiness.getInstance().requestAdData();
    }


    public static void showScreenAd(RelativeLayout container, final IAdCallback callback) {
        ScreenAdBusiness.getInstance().mRelativeLayout = container;
        ScreenAdBusiness.getInstance().setAdCallback(callback);
        ScreenAdBusiness.getInstance().requestAdData();
    }

    public static void distoryScreenAd() {
        ScreenAdBusiness.getInstance().mRelativeLayout = null;
    }

    public static void init() {
        KyxSDKGlobal.mContext = YiSiApplication.mGlobleContext;
        requestAdConfig();
    }

    private static void requestAdConfig() {
        DataStore<AdConfig> dataStore = DataStore.collection("AdConfig", AdConfig.class, StoreType.CACHE, YiSiApplication.getKinveyClient());
        dataStore.find(new KinveyListCallback<AdConfig>() {
            @Override
            public void onSuccess(List<AdConfig> list) {
                if (list != null) {
                    AdConfig adConfig = list.get(0);
                    if (adConfig != null) {
                        BaseAdBusiness.mAdconfig = adConfig;
                        requestRewardAd();
                    }
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }
}
