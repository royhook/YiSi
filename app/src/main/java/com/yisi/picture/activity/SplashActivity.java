package com.yisi.picture.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.yisi.picture.MainActivity;
import com.yisi.picture.R;
import com.yisi.picture.activity.inter.ISplashActivity;
import com.yisi.picture.application.YiSiApplication;

/**
 * Created by roy on 2017/1/13.
 */

public class SplashActivity extends AppCompatActivity implements ISplashActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView imageView = (ImageView) findViewById(R.id.iv_splash_main);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_splash_in);
        imageView.startAnimation(animation);
        goMainPage();
    }

    private void goMainPage() {
        YiSiApplication.postDelay(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }
}
