package com.yisi.picture.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by roy on 2017/1/14.
 */

public class MainTabEntity implements CustomTabEntity {
    private String mTabTitle;
    private int mSelectIcon;
    private int mUnSelectIcon;

    public void setmSelectIcon(int mSelectIcon) {
        this.mSelectIcon = mSelectIcon;
    }

    public void setmTabTitle(String mTabTitle) {
        this.mTabTitle = mTabTitle;
    }

    public void setmUnSelectIcon(int mUnSelectIcon) {
        this.mUnSelectIcon = mUnSelectIcon;
    }

    @Override
    public String getTabTitle() {
        return mTabTitle;
    }

    @Override
    public int getTabSelectedIcon() {
        return mSelectIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return mUnSelectIcon;
    }
}
