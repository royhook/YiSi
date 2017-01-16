package com.yisi.picture.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yisi.picture.R;
import com.yisi.picture.base.BaseActivity;
import com.yisi.picture.fragment.AlbumFragment;
import com.yisi.picture.fragment.MainPageFragment;
import com.yisi.picture.fragment.MineFragment;
import com.yisi.picture.fragment.PlansFragment;
import com.yisi.picture.presenter.MainAtyPreImpl;

public class MainActivity extends BaseActivity {

    private CommonTabLayout mCommonTabLayout;
    private MainAtyPreImpl mMainAtyPre;
    private Fragment mMainFragment;
    private Fragment mAlbumFragment;
    private Fragment mPlansFragment;
    private Fragment mMineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainAtyPre = new MainAtyPreImpl();
        initData();
        initFragment();

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_main);
        mCommonTabLayout = findView(R.id.main_commenTab);

    }

    private void initData() {
        mCommonTabLayout.setTabData(mMainAtyPre.buildMaintTab(this));
        mCommonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        showMainPage();
                        break;

                    case 1:
                        showAlbumPage();
                        break;

                    case 2:
                        showPlansPage();
                        break;

                    case 3:
                        showMinePage();
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void initFragment() {
        mMainFragment = new MainPageFragment();
        mAlbumFragment = new AlbumFragment();
        mPlansFragment = new PlansFragment();
        mMineFragment = new MineFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_content, mMainFragment)
                .add(R.id.main_content, mAlbumFragment)
                .add(R.id.main_content, mPlansFragment)
                .add(R.id.main_content, mMineFragment)
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

    private void showMainPage() {
        hideFragment(mAlbumFragment);
        hideFragment(mPlansFragment);
        hideFragment(mMineFragment);
        showFragment(mMainFragment);
    }

    private void showAlbumPage() {
        hideFragment(mMainFragment);
        hideFragment(mPlansFragment);
        hideFragment(mMineFragment);
        showFragment(mAlbumFragment);
    }

    private void showPlansPage() {
        hideFragment(mMainFragment);
        hideFragment(mAlbumFragment);
        hideFragment(mMineFragment);
        showFragment(mPlansFragment);

    }

    private void showMinePage() {
        hideFragment(mMainFragment);
        hideFragment(mAlbumFragment);
        hideFragment(mPlansFragment);
        showFragment(mMineFragment);
    }


}
