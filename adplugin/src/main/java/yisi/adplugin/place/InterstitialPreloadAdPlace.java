package yisi.adplugin.place;

import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.baselib.utils.ViewUtils;

import yisi.adplugin.R;
import yisi.adplugin.bean.NativeAdInfo;
import yisi.adplugin.business.IAdCallback;
import yisi.adplugin.business.OnNativeAdShowCallback;
import yisi.adplugin.utils.ImitateClickUtils;
import yisi.adplugin.utils.KyxSDKGlobal;
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
        if (ImitateClickUtils.hitProbability(chance)) {
            closeView.setClickable(false);
        } else {
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


    protected void showNativeAd(NativeAdInfo nativeAdInfo, final OnNativeAdShowCallback callback) {
        final View rootView = LayoutInflater.from(KyxSDKGlobal.mContext).inflate(R.layout.insert_native_ad, null, false);
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.iv_insertimg);
        ImageView closeView = (ImageView) rootView.findViewById(R.id.iv_insert_close);

        final WindowManager.LayoutParams params = getWindowLayoutParams();
        params.height = ViewUtils.getDimen(R.dimen.px700);
        params.width = ViewUtils.getDimen(R.dimen.px1020);

        final ImageView iconView = (ImageView) rootView.findViewById(R.id.iv_insert_icon);
        Glide.with(KyxSDKGlobal.mContext).load(nativeAdInfo.getImageUrl()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                imageView.setImageBitmap(resource);
                mLayoutParams = params;
                windowView = rootView;
                addWindow();
                onAdPresent();//展示成功
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
            }
        });


        if (callback != null)
            callback.onViewCreated(rootView);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAdClick();
                if (callback != null)
                    callback.onAdClick(v);
            }
        });
        closeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAdSkip();
            }
        });

        if (TextUtils.isEmpty(nativeAdInfo.getIconUrl()) || TextUtils.isEmpty(nativeAdInfo.getTitle())) {
            rootView.findViewById(R.id.rl_inserttxt).setVisibility(View.GONE);
            return;
        }

        TextView textView = (TextView) rootView.findViewById(R.id.tv_insert_desc);
        textView.setText(nativeAdInfo.getDesc());
        GlideUtils.displayImage(nativeAdInfo.getIconUrl(), iconView);
    }

}
