package com.yisi.picture.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.tencent.stat.MtaSDkException;
import com.tencent.stat.StatService;
import com.yisi.picture.R;
import com.yisi.picture.activity.inter.IMainAty;
import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.base.BaseActivity;
import com.yisi.picture.baselib.rx.Event;
import com.yisi.picture.baselib.rx.RxBus;
import com.yisi.picture.baselib.rx.RxKey;
import com.yisi.picture.baselib.utils.ReLockUtils;
import com.yisi.picture.baselib.utils.ViewUtils;
import com.yisi.picture.picturemodel.database.YisiDatabase;
import com.yisi.picture.picturemodel.fragment.MainPageFragment;
import com.yisi.picture.presenter.MainAtyPreImpl;

import rx.functions.Action1;
import yisi.adplugin.activity.CoinActivity;
import yisi.adplugin.utils.CoinUtils;

public class MainActivity extends BaseActivity implements IMainAty, NavigationView.OnNavigationItemSelectedListener {

    private CommonTabLayout mCommonTabLayout;
    private MainAtyPreImpl mMainAtyPre;
    private MainPageFragment mMainFragment;
    private DrawerLayout mDrawer;
    private Toolbar mToolbar;
    private TextView mCoinView;
    private NavigationView mNavigationView;
    private long mLastClickTime;
    private long mCurrentClickTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        YisiDatabase.getInstance().init();
        initFragment();
        ReLockUtils.startRelock();
    }

    @Override
    protected void initPresenter() {
        mMainAtyPre = new MainAtyPreImpl(this);
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_main);
        mCommonTabLayout = findView(R.id.main_commenTab);
        mToolbar = findView(R.id.tl_fragment_main);
        mToolbar.setTitle(R.string.tab_picture);
        mToolbar.setNavigationIcon(R.mipmap.category);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer();
            }
        });
        mDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, R.string.open, R.string.close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();
        mNavigationView = findViewById(R.id.nav_view);
        String coin = getText(R.string.my_coin) + String.valueOf(CoinUtils.getUsrCoin());
        mNavigationView.setNavigationItemSelectedListener(this);
        View view = mNavigationView.getHeaderView(0);
        mCoinView = ViewUtils.findView(view, R.id.tv_coin);
        mCoinView.setText(coin);
        RxBus.getInstance().toObservable(Event.class).subscribe(new Action1<Event>() {
            @Override
            public void call(Event event) {
                if (event.getTag().equals(RxKey.COIN_EXCHANGE)) {
                    String coin = getText(R.string.my_coin) + String.valueOf(CoinUtils.getUsrCoin());
                    mCoinView.setText(coin);
                }
            }
        });

        initStateEvent();
    }

    private void initStateEvent() {
        String appkey = "AA23YHN56VBQ";
        // 初始化并启动MTA
        try {
            // 第三个参数必须为：com.tencent.stat.common.StatConstants.VERSION
            StatService.startStatService(this, appkey,
                    com.tencent.stat.common.StatConstants.VERSION);
            Log.d("MTA", "MTA初始化成功");
        } catch (MtaSDkException e) {
            // MTA初始化失败
            Log.d("MTA", "MTA初始化失败" + e);
        }
    }

    @Override
    protected void initData() {
        mMainAtyPre.initDatas();
    }

    public void openDrawer() {
        mDrawer.openDrawer(GravityCompat.START);
    }


    private void initFragment() {
        mMainFragment = new MainPageFragment();
        mMainFragment.setOnLeftViewClick(new MainPageFragment.onLeftViewClick() {
            @Override
            public void onClick() {
                openDrawer();
            }
        });
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_content, mMainFragment, "main")
                .commitAllowingStateLoss();
        showMainPage();
//        mCommonTabLayout.setCurrentTab(0);
    }

    private void hideFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().hide(fragment).commitAllowingStateLoss();
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().show(fragment).commitAllowingStateLoss();
    }

    @Override
    public void showMainPage() {
    }

    @Override
    public void showAlbumPage() {
        hideFragment(mMainFragment);
    }

    @Override
    public void showPlansPage() {
        hideFragment(mMainFragment);
    }

    @Override
    public CommonTabLayout getCommonTab() {
        return mCommonTabLayout;
    }

    @Override
    public Context getMainContext() {
        return this;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_collect:
                Intent intent = new Intent(this, CollectionActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_coin:
                Intent coinIntent = CoinActivity.getCoinIntent();
                startActivity(coinIntent);
                break;

            case R.id.nav_setting:
                Intent settingIntent = new Intent(this, SettingActivity.class);
                startActivity(settingIntent);
                break;
            default:

                break;
        }
        YiSiApplication.postDelay(new Runnable() {
            @Override
            public void run() {
                mDrawer.closeDrawers();
            }
        }, 1000);
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ReLockUtils.saveRelock();
    }

    @Override
    public void onBackPressed() {
        //两次间隔小于2s的时候才真正退出
        mCurrentClickTime = System.currentTimeMillis();
        if (Math.abs(mCurrentClickTime - mLastClickTime) > 2000) {
            Snackbar.make(mCommonTabLayout, R.string.exit_click_again, Snackbar.LENGTH_SHORT).show();
            mLastClickTime = mCurrentClickTime;
        } else {
            super.onBackPressed();
        }
    }
}
