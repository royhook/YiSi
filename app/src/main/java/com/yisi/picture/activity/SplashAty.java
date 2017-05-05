package com.yisi.picture.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.yisi.picture.R;
import com.yisi.picture.activity.inter.ISplashAty;
import com.yisi.picture.application.YiSiApplication;
import com.yisi.picture.base.BaseActivity;
import com.yisi.picture.utils.GlideUtils;
import com.yisi.picture.utils.PreferencesUtils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by roy on 2017/1/13.
 */

public class SplashAty extends BaseActivity implements ISplashAty {
    public static final String BMOB_APPID = "9b7629a191656e19839a10be2a27363b";
    public static final String BMOB_YISI_APPID = "bd680cd370f7e8716b941d9d37872d2e";
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goMainPage();
        initBmob();
        GlideUtils.initGlide(this);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_splash);
        relativeLayout = findView(R.id.splash_ad_view);
    }

    @Override
    protected void initData() {

    }

    private void goMainPage() {
        Intent intent = new Intent(SplashAty.this, MainActivity.class);
        startActivity(intent);
        SplashAty.this.finish();
        ShareSDK.initSDK(this);
    }


    private void initBmob() {
        String bmobId = PreferencesUtils.getString(this, PreferencesUtils.KEY.KEY_BMOB_ID, BMOB_APPID);
        String preBmobId = PreferencesUtils.getString(this, PreferencesUtils.KEY.KEY_PRE_BMOB_ID, BMOB_APPID);
        //如果没有发生改变，就不改变
        if (bmobId.equals(preBmobId)) {
            YiSiApplication.isChange = false;
        }
        //发生了改变
        else {
            YiSiApplication.isChange = true;
        }
        BmobConfig config = new BmobConfig.Builder(this)
                //设置appkey
                .setApplicationId(bmobId)
                //请求超时时间（单位为秒）：默认15s
                .setConnectTimeout(30)
                //文件分片上传时每片的大小（单位字节），默认512*icon
                .setUploadBlockSize(1024 * 1024)
                //文件的过期时间(单位为秒)：默认1800s
                .setFileExpiration(2500)
                .build();
        Bmob.initialize(config);
        PreferencesUtils.putString(this, PreferencesUtils.KEY.KEY_PRE_BMOB_ID, bmobId);
    }
}
