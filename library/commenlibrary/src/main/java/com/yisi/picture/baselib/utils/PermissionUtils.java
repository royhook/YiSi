package com.yisi.picture.baselib.utils;

import android.Manifest;

import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.yisi.picture.application.YiSiApplication;

import java.util.List;

/**
 * Created by chenql on 2017/4/6.
 */

public class PermissionUtils {

    public static void requestWriteSDCard(final OnRequestCallback callback) {
        AcpOptions options = new AcpOptions.Builder().setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE).build();
        Acp.getInstance(YiSiApplication.mGlobleContext).request(options, new AcpListener() {
            @Override
            public void onGranted() {
                callback.onSuccess();
            }

            @Override
            public void onDenied(List<String> permissions) {
                callback.onFail();
            }
        });
    }

    public interface OnRequestCallback {
        void onSuccess();

        void onFail();
    }
}
