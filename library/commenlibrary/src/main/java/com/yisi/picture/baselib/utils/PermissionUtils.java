package com.yisi.picture.baselib.utils;

import android.Manifest;
import android.content.Context;

import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.yisi.picture.baselib.R;
import com.yisi.picture.baselib.application.YiSiApplication;

import java.util.List;

/**
 * Created by chenql on 2017/4/6.
 */

public class PermissionUtils {
    static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static void requestWriteSDCard(final OnRequestCallback callback) {
        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, YiSiApplication.getStringResource(R.string.writesd_permission), callback);
    }

    public static void requestReadPhoneState(final OnRequestCallback callback) {
        requestPermission(Manifest.permission.READ_PHONE_STATE, YiSiApplication.getStringResource(R.string.readsd_permission), callback);
    }

    public static void requestAccessLocation(final OnRequestCallback callback) {
        requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, YiSiApplication.getStringResource(R.string.accesslocation_permission), callback);

    }

    private static void requestPermission(String permission, final OnRequestCallback callback) {
        String[] permissions = new String[]{permission};
        requestPermissions(permissions, callback);
    }

    private static void requestPermission(String permission, String defineMessage, final OnRequestCallback callback) {
        AcpOptions options = new AcpOptions.Builder()
                .setPermissions(permission)
                .setRationalMessage(defineMessage)
                .build();
        Acp.getInstance(mContext).request(options, new AcpListener() {
            @Override
            public void onGranted() {
                if (callback != null)
                    callback.onSuccess();
            }

            @Override
            public void onDenied(List<String> permissions) {
                if (callback != null)
                    callback.onFail();
            }
        });
    }

    public static void requestPermissions(String[] permissions, final OnRequestCallback callback) {
        requestPermissions(permissions, "", callback);
    }

    public static void requestPermissions(String[] permissions, String defineMessage, final OnRequestCallback callback) {
        AcpOptions options = new AcpOptions.Builder()
                .setPermissions(permissions)
                .setRationalMessage(defineMessage)
                .build();
        Acp.getInstance(mContext).request(options, new AcpListener() {
            @Override
            public void onGranted() {
                if (callback != null)
                    callback.onSuccess();
            }

            @Override
            public void onDenied(List<String> permissions) {
                if (callback != null)
                    callback.onFail();
            }
        });
    }

    public interface OnRequestCallback {
        void onSuccess();

        void onFail();
    }
}
