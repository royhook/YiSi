package com.yisi.picture.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.flyco.tablayout.CommonTabLayout;
import com.yisi.picture.R;
import com.yisi.picture.activity.inter.IMainAty;
import com.yisi.picture.baselib.base.BaseActivity;
import com.yisi.picture.baselib.utils.EnvUtils;
import com.yisi.picture.baselib.utils.PreferencesUtils;
import com.yisi.picture.picturemodel.fragment.MainPageFragment;
import com.yisi.picture.presenter.MainAtyPreImpl;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class MainActivity extends BaseActivity implements IMainAty, NavigationView.OnNavigationItemSelectedListener {

    private CommonTabLayout mCommonTabLayout;
    private MainAtyPreImpl mMainAtyPre;
    private MainPageFragment mMainFragment;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawer;
    private Toolbar mToolbar;
    private int mClickTime = 0;
    private boolean mCanClick = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment();
    }

    @Override
    protected void initPresenter() {
        mMainAtyPre = new MainAtyPreImpl(this);
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_main);
        mCommonTabLayout = findView(R.id.main_commenTab);
        mNavigationView = findView(R.id.nav_view);
        mToolbar = findView(R.id.tl_fragment_main);
        mToolbar.setTitle("首页");
        mToolbar.setNavigationIcon(R.mipmap.category);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer();
            }
        });
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, R.string.open, R.string.close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_content, mMainFragment, "main")
                .commitAllowingStateLoss();
        showMainPage();
        mCommonTabLayout.setCurrentTab(0);
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

            case R.id.nav_send:

                break;

            case R.id.nav_version:
                if (!mCanClick)
                    return true;
                if (mClickTime != 10)
                    mClickTime++;
                else {
                    Toast.makeText(this, "Yisi mode 需彻底退出应用并重启", Toast.LENGTH_SHORT).show();
                    openYiSiMode();
                    mCanClick = false;
                }
                break;

            case R.id.nav_share:
                showShare();
                break;

            case R.id.nav_setting:
                Intent settingIntent = new Intent(this, SettingActivity.class);
                startActivity(settingIntent);
                break;
        }
        return true;
    }

    private void openYiSiMode() {
        if (EnvUtils.isYisiMode()) {
            PreferencesUtils.putString(this, PreferencesUtils.KEY.KEY_BMOB_ID, SplashAty.BMOB_APPID);
        } else {
            PreferencesUtils.putString(this, PreferencesUtils.KEY.KEY_BMOB_ID, SplashAty.BMOB_YISI_APPID);
        }
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("Yisi");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sj.qq.com/myapp/detail.htm?apkName=com.yisi.picture");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("不错的应用，大家快下载");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://bmob-cdn-8831.b0.upaiyun.com/2017/05/05/3298c7a440cf9337806d20ef12b8d116.png");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sj.qq.com/myapp/detail.htm?apkName=com.yisi.picture");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("不错的应用，大家快下载");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("Yisi");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sj.qq.com/myapp/detail.htm?apkName=com.yisi.picture");

// 启动分享GUI
        oks.show(this);
    }
}
