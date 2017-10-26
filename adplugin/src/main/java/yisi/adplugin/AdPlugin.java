package yisi.adplugin;

import android.view.View;

import com.yisi.picture.baselib.application.YiSiApplication;

import yisi.adplugin.business.RewardBusiness;
import yisi.adplugin.place.InterstitialPreloadAdPlace;
import yisi.adplugin.utils.KyxSDKGlobal;

/**
 * Created by chenql on 2017/10/26.
 */

public class AdPlugin {
    /**
     * 展示广告
     */
    public static void showRewardAd() {
        KyxSDKGlobal.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                InterstitialPreloadAdPlace adPlace = (InterstitialPreloadAdPlace) RewardBusiness.getInstance().mBaseAdPlace;
                if (adPlace != null) {
                    adPlace.show();
                }
            }
        });
    }

    /**
     * 请求广告
     */
    public static void requestRewardAd() {
        RewardBusiness.getInstance().requestAdData();
    }


    public static void showScreenAd(View view) {

    }

    public static void init() {
        KyxSDKGlobal.mContext = YiSiApplication.mGlobleContext;
    }
}
