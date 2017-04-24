package com.yisi.picture.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.MenuItem;

import com.flyco.tablayout.CommonTabLayout;
import com.yisi.picture.R;
import com.yisi.picture.activity.inter.IMainAty;
import com.yisi.picture.base.BaseActivity;
import com.yisi.picture.fragment.AlbumFragment;
import com.yisi.picture.fragment.MainPageFragment;
import com.yisi.picture.fragment.PlantFragment;
import com.yisi.picture.presenter.MainAtyPreImpl;
import com.yisi.picture.utils.DirManager;

public class MainActivity extends BaseActivity implements IMainAty, NavigationView.OnNavigationItemSelectedListener {

    private CommonTabLayout mCommonTabLayout;
    private MainAtyPreImpl mMainAtyPre;
    private Fragment mMainFragment;
    private Fragment mAlbumFragment;
    private Fragment mPlansFragment;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DirManager.getInstance().init();
        initFragment();
    }

    public void setmCommonTabLayoutVisible(int Visible) {
        mCommonTabLayout.setVisibility(Visible);
    }

    public int getmCommonTabLayoutVisiablity() {
        return mCommonTabLayout.getVisibility();
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

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(Gravity.LEFT)) {
            mDrawer.closeDrawer(Gravity.LEFT);
            return;
        }
        //这些应该都移动到P层才对,赶时间 先不移动了吧
        MainPageFragment fragment = (MainPageFragment) getSupportFragmentManager().findFragmentByTag("main");
        if (fragment != null) {
            if (!fragment.onBackPressed())
                super.onBackPressed();
        }
    }

    private void initFragment() {
        mMainFragment = new MainPageFragment();
        mAlbumFragment = new AlbumFragment();
        mPlansFragment = new PlantFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_content, mMainFragment, "main")
                .add(R.id.main_content, mAlbumFragment, "album")
                .add(R.id.main_content, mPlansFragment, "plant")
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
        hideFragment(mAlbumFragment);
        hideFragment(mPlansFragment);
        showFragment(mMainFragment);
    }

    @Override
    public void showAlbumPage() {
        hideFragment(mMainFragment);
        hideFragment(mPlansFragment);
        showFragment(mAlbumFragment);
    }

    @Override
    public void showPlansPage() {
        hideFragment(mMainFragment);
        hideFragment(mAlbumFragment);
        showFragment(mPlansFragment);
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
        return false;
    }
}
