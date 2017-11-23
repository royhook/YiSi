package yisi.adplugin.business;

import android.view.View;

public interface OnNativeAdShowCallback {
        void onViewCreated(View view);

        void onAdClick(View view);
    }