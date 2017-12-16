package com.yisi.picture.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.RelativeLayout;

import com.yisi.picture.R;
import com.yisi.picture.activity.inter.ISplashAty;
import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.base.BaseActivity;
import com.yisi.picture.baselib.utils.BitmapUtils;
import com.yisi.picture.baselib.utils.DirManager;
import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.baselib.utils.LogUtils;
import com.yisi.picture.baselib.utils.PermissionUtils;
import com.yisi.picture.baselib.utils.ViewUtils;

import yisi.adplugin.AdPlugin;
import yisi.adplugin.business.IAdCallback;
import yisi.adplugin.business.ScreenAdBusiness;

/**
 * Created by roy on 2017/1/13.
 */

public class SplashAty extends BaseActivity implements ISplashAty {
    RelativeLayout relativeLayout;
    private boolean hasGoMain = false;
    private boolean isPause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlideUtils.initGlide(YiSiApplication.mGlobleContext);
        ViewUtils.init(YiSiApplication.mGlobleContext);
        DirManager.getInstance().init();
        BitmapUtils.initBitmapUtils(YiSiApplication.mGlobleContext);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isPause) {
            goMainPage();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPause = true;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_splash);
        relativeLayout = findView(R.id.splash_ad_view);
        PermissionUtils.init(YiSiApplication.mGlobleContext);
        String[] permissions;
        permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        PermissionUtils.requestPermissions(permissions, "请授予以下权限", new PermissionUtils.OnRequestCallback() {
            @Override
            public void onSuccess() {
                AdPlugin.init();
                AdPlugin.showScreenAd(relativeLayout, new IAdCallback() {
                    @Override
                    public void onSkip() {
                        goMainPage();
                    }

                    @Override
                    public void onLoad() {

                    }

                    @Override
                    public void onUnAvaliable() {
                        goMainPage();
                    }
                });
                //最多8s后进入主界面
                YiSiApplication.postDelay(new Runnable() {
                    @Override
                    public void run() {
                        goMainPageAuto();
                    }
                }, 10000);
            }

            @Override
            public void onFail() {
                Snackbar.make(relativeLayout, R.string.no_permission, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void goMainPageAuto() {
        if (hasGoMain || ScreenAdBusiness.getInstance().isDoingClick) {
            LogUtils.d("已经进入游戏了,或者广告被点击了");
            return;
        }
        goMainPage();
    }

    private void goMainPage() {
        if (hasGoMain) {
            return;
        }
        hasGoMain = true;
        Intent intent = new Intent(SplashAty.this, MainActivity.class);
        startActivity(intent);
        SplashAty.this.finish();
        overridePendingTransition(0, 0);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AdPlugin.distoryScreenAd();
    }
}
