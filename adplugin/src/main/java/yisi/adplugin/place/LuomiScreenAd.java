package yisi.adplugin.place;

import android.view.View;

import com.luomi.lm.ad.ADType;
import com.luomi.lm.ad.DRAgent;
import com.luomi.lm.ad.IAdSuccessBack;
import com.yisi.picture.baselib.utils.ActivityLifestyle;
import com.yisi.picture.baselib.utils.LogUtils;

import yisi.adplugin.bean.Ad;
import yisi.adplugin.business.BaseAdPlace;
import yisi.adplugin.business.ScreenAdBusiness;

/**
 * Created by chenql on 2017/11/22.
 */

public class LuomiScreenAd extends BaseAdPlace {

    @Override
    public void startShowAd(Ad ad, String appid, String adid) {
        LogUtils.d("luomi");
        DRAgent.getInstance().init(ActivityLifestyle.getInstance().getActivity(), appid, true);
        DRAgent.getInstance().getOpenView(ActivityLifestyle.getInstance().getActivity(), ADType.FULL_SCREEN, true, new IAdSuccessBack() {
            @Override
            public void onError(final String result) {
                onAdFailed(result);
                LogUtils.d(result);
            }

            @Override
            public void onClick(String result) {
                onAdClick();
            }

            @Override
            public void OnSuccess(String result) {
                LogUtils.d(result);
                onAdSkip();
            }

            @Override
            public void OnLoadAd(View view) {
                LogUtils.d("load");
                ScreenAdBusiness.getInstance().mRelativeLayout.addView(view);
            }
        });
    }

}
