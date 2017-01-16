package com.yisi.picture.presenter;

import android.content.Context;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.yisi.picture.R;
import com.yisi.picture.bean.MainTabEntity;
import com.yisi.picture.presenter.inter.IMainAtyPre;

import java.util.ArrayList;

/**
 * Created by roy on 2017/1/14.
 */

public class MainAtyPreImpl implements IMainAtyPre {
    @Override
    public ArrayList<CustomTabEntity> buildMaintTab(Context context) {
        ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
        int[] titles = new int[]{R.string.main_page, R.string.album, R.string.plans, R.string.user};
        int[] selectedIcon = new int[]{R.mipmap.tab_home_select, R.mipmap.tab_more_select, R.mipmap.tab_speech_select, R.mipmap.tab_contact_select};
        int[] unSelectedIcon = new int[]{R.mipmap.tab_home_unselect, R.mipmap.tab_more_unselect, R.mipmap.tab_speech_unselect, R.mipmap.tab_contact_unselect};

        for (int i = 0; i < 4; i++) {
            MainTabEntity mainTabEntity = new MainTabEntity();
            mainTabEntity.setmTabTitle(context.getResources().getString(titles[i]));
            mainTabEntity.setmSelectIcon(selectedIcon[i]);
            mainTabEntity.setmUnSelectIcon(unSelectedIcon[i]);
            tabEntities.add(mainTabEntity);
        }
        return tabEntities;
    }
}
