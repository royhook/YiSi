package yisi.adplugin.place;

import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yisi.picture.baselib.utils.ViewUtils;

import yisi.adplugin.business.IAdCallback;
import yisi.adplugin.utils.ImitateClickUtils;
import yisi.adplugin.utils.KyxSDKGlobal;
import yisi.adplugin.R;
import yisi.adplugin.utils.WindowUtils;

/**
 * Created by chenql on 2017/7/10.
 */

public abstract class InterstitialPreloadAdPlace extends WindowAdPlace {

    protected boolean isReady;

    static IAdCallback mIAdCallback;

    public void setIAdCallback(IAdCallback IAdCallback) {
        mIAdCallback = IAdCallback;
    }

    public abstract void showAd();

    protected WindowManager.LayoutParams getWindowLayoutParams() {

        mWindowType = WindowUtils.getWindowType();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(mWindowType);
        layoutParams.format = PixelFormat.TRANSPARENT;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.gravity = Gravity.CENTER;
        return layoutParams;
    }

    @Override
    public void onAdPresent() {
        super.onAdPresent();
    }

    @Override
    public void onAdLoad() {
        isReady = true;
        if (mIAdCallback != null)
            mIAdCallback.onLoad();
        super.onAdLoad();
    }

    @Override
    public void onAdSkip() {
        if (mIAdCallback != null)
            mIAdCallback.onSkip();
        isReady = false;
        super.onAdSkip();
    }

    @Override
    public void onAdFailed(String message) {
        isReady = false;
        super.onAdFailed(message);
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public boolean isReady() {
        return isReady;
    }

    public void show() {
        //如果调用的时候 没有准备好 自动走下一个
        if (isReady) {
            showAd();
        } else {
            goNextAd();
        }
    }

    public void createWindowView(final View view, final int chance) {
        if (view.getParent() != null) {
            onAdFailed("ad has parent");
            return;
        }
        final RelativeLayout mBannerRootView = new RelativeLayout(KyxSDKGlobal.mContext);
        RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rllp.addRule(RelativeLayout.CENTER_IN_PARENT);
        mBannerRootView.addView(view, rllp);
        ImageView closeView = new ImageView(KyxSDKGlobal.mContext);
        closeView.setBackgroundResource(R.drawable.tencent_close);
        if (ImitateClickUtils.hitProbability(chance))
            closeView.setClickable(false);
        else {
            closeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeWindow(true);
                    onAdSkip();
                }
            });
        }
        RelativeLayout.LayoutParams closeParams = new RelativeLayout.LayoutParams(ViewUtils.getDimen(R.dimen.px50), ViewUtils.getDimen(R.dimen.px50));
        closeParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mBannerRootView.addView(closeView, closeParams);
        mLayoutParams = getWindowLayoutParams();
        mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        windowView = mBannerRootView;
        addWindow();
    }

}
