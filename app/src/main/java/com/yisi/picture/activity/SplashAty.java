package com.yisi.picture.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.yisi.picture.R;
import com.yisi.picture.activity.inter.ISplashAty;
import com.yisi.picture.application.YiSiApplication;
import com.yisi.picture.base.BaseActivity;
import com.yisi.picture.utils.GlideUtils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * Created by roy on 2017/1/13.
 */

public class SplashAty extends BaseActivity implements ISplashAty {
    public static final String BMOB_APPID = "9b7629a191656e19839a10be2a27363b";

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
        ImageView imageView = (ImageView) findViewById(R.id.iv_splash_main);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_splash_in);
        imageView.startAnimation(animation);
    }

    @Override
    protected void initData() {

    }

    private void goMainPage() {
        YiSiApplication.postDelay(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashAty.this, MainActivity.class);
                startActivity(intent);
                SplashAty.this.finish();
            }
        }, 3000);
    }


    private void initBmob() {
        BmobConfig config = new BmobConfig.Builder(this)
                //设置appkey
                .setApplicationId(BMOB_APPID)
                //请求超时时间（单位为秒）：默认15s
                .setConnectTimeout(30)
                //文件分片上传时每片的大小（单位字节），默认512*1024
                .setUploadBlockSize(1024 * 1024)
                //文件的过期时间(单位为秒)：默认1800s
                .setFileExpiration(2500)
                .build();
        Bmob.initialize(config);
    }
}
