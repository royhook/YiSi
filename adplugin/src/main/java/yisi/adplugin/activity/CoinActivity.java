package yisi.adplugin.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yisi.picture.baselib.R;
import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.base.BaseActivity;

import yisi.adplugin.AdPlugin;
import yisi.adplugin.business.IAdCallback;

/**
 * Created by chenql on 2017/10/30.
 */

public class CoinActivity extends BaseActivity {
    ImageView mCloseView;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_coin);
        final Button button = findView(R.id.btn_seead);
        mCloseView = findView(R.id.setting_activity_back);
        mCloseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setClickable(false);
                AdPlugin.showRewardAd(new IAdCallback() {

                    @Override
                    public void onSkip() {
                        button.setClickable(false);
                    }

                    @Override
                    public void onLoad() {
                        button.setClickable(true);
                    }

                    @Override
                    public void onUnAvaliable() {

                    }
                });
            }
        });
    }

    @Override
    protected void initData() {

    }

    public static Intent getCoinIntent() {
        Intent intent = new Intent();
        intent.setClass(YiSiApplication.mGlobleContext, CoinActivity.class);
        return intent;
    }
}
