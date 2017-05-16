package com.yisi.picture.presenter;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yisi.picture.R;
import com.yisi.picture.activity.inter.IMainAty;
import com.yisi.picture.baselib.base.BasePresenterImpl;
import com.yisi.picture.bean.MainTabEntity;
import com.yisi.picture.model.MainAtyModel;
import com.yisi.picture.model.inter.IMainAtyModel;
import com.yisi.picture.presenter.inter.IMainAtyPre;

import java.util.ArrayList;

/**
 * Created by roy on 2017/1/14.
 */

public class MainAtyPreImpl extends BasePresenterImpl<IMainAty, IMainAtyModel> implements IMainAtyPre {

    public MainAtyPreImpl(IMainAty baseView) {
        super(baseView);
    }

    private ArrayList<CustomTabEntity> buildMaintTab() {
        ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
        int[] titles = new int[]{R.string.main_page, R.string.album, R.string.plans, R.string.user};
        int[] selectedIcon = new int[]{R.mipmap.tab_home_select, R.mipmap.tab_image_select, R.mipmap.tab_all_select, R.mipmap.tab_contact_select};
        int[] unSelectedIcon = new int[]{R.mipmap.tab_home, R.mipmap.tab_image, R.mipmap.tab_all, R.mipmap.tab_contact_unselect};

        for (int i = 0; i < 1; i++) {
            MainTabEntity mainTabEntity = new MainTabEntity();
            mainTabEntity.setmTabTitle(mView.getMainContext().getResources().getString(titles[i]));
            mainTabEntity.setmSelectIcon(selectedIcon[i]);
            mainTabEntity.setmUnSelectIcon(unSelectedIcon[i]);
            tabEntities.add(mainTabEntity);
        }
        return tabEntities;
    }

    @Override
    public void initDatas() {
        mView.getCommonTab().setTabData(buildMaintTab());
        mView.getCommonTab().setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        mView.showMainPage();
                        break;

                    case 1:
                        mView.showAlbumPage();
                        break;

                    case 2:
                        mView.showPlansPage();
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    protected IMainAtyModel setModel() {
        return new MainAtyModel(this);
    }
}
