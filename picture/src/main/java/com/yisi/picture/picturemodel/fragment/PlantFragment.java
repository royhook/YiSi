package com.yisi.picture.picturemodel.fragment;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.yisi.picture.baselib.base.BaseFragment;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.activity.ImageChoseActivity;
import com.yisi.picture.picturemodel.bean.RecommandPlantImage;
import com.yisi.picture.picturemodel.fragment.inter.IPlansFragment;
import com.yisi.picture.picturemodel.presenter.PlantFragmentPreImpl;
import com.yisi.picture.picturemodel.presenter.inter.IPlantFragmentPre;

import java.util.List;

/**
 * Created by roy on 2017/1/14.
 */

public class PlantFragment extends BaseFragment implements IPlansFragment, BaseSliderView.OnSliderClickListener {

    RecyclerView mRecyclerView;
    IPlantFragmentPre plantFragmentPre;
    private AppBarLayout mAppBarLayout;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private SliderLayout mSliderLayout;


    @Override
    protected void initViews() {
        mRecyclerView = findview(R.id.plans_fragment_recycler);
        mSliderLayout = findview(R.id.main_fragment_slider);
        plantFragmentPre = new PlantFragmentPreImpl(this);
        mAppBarLayout = findview(R.id.main_appbar);
        mSwipeRefreshLayout = findview(R.id.sr_fragment_plans);
        mSwipeRefreshLayout.setProgressViewOffset(true, -20, 100);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {
                    mSwipeRefreshLayout.setEnabled(true);
                } else {
                    mSwipeRefreshLayout.setEnabled(false);
                }
            }
        });
    }


    public void bindBanner(List<RecommandPlantImage> list) {
        mSliderLayout.removeAllSliders();
        for (RecommandPlantImage plantImage : list) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView
                    .setImg_list(new Gson().toJson(plantImage.getImage_list()))
                    .description(plantImage.getName())
                    .image(plantImage.getImage_url());
            textSliderView.setOnSliderClickListener(this);
            mSliderLayout.addSlider(textSliderView);
        }
    }


    @Override
    protected int getContentResouce() {
        return R.layout.fragment_plans;
    }

    @Override
    protected void initData() {
        plantFragmentPre.request(true);
    }

    @Override
    public RecyclerView getRecylerView() {
        return mRecyclerView;
    }

    @Override
    public Context getViewContext() {
        return this.getContext();
    }

    @Override
    public void dataOut() {
    }

    @Override
    public SwipeRefreshLayout getSwipeRefresh() {
        return mSwipeRefreshLayout;
    }

    @Override
    public void onRefreshComlete() {
        super.onRefreshComlete();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        TextSliderView textSliderView = (TextSliderView) slider;
        startActivity(ImageChoseActivity.getDateIntent(textSliderView.getImg_list()));
    }
}
