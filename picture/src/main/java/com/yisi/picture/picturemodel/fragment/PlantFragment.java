package com.yisi.picture.picturemodel.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.yisi.picture.baselib.base.BaseFragment;
import com.yisi.picture.baselib.utils.IntentKey;
import com.yisi.picture.baselib.utils.LogUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.activity.ImageOperateActivity;
import com.yisi.picture.picturemodel.bean.PlantBrowse;
import com.yisi.picture.picturemodel.fragment.inter.IPlansFragment;
import com.yisi.picture.picturemodel.model.ImageOperaOperateModel;
import com.yisi.picture.picturemodel.net.BmobRequest;
import com.yisi.picture.picturemodel.presenter.PlantFragmentPreImpl;
import com.yisi.picture.picturemodel.presenter.inter.IPlantFragmentPre;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

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

    private void requestBanner() {
        new BmobRequest.Builder()
                .addEqualTo("page", -1)
                .setCacheTime(1000 * 60 * 5)
                .setReadCache(true)
                .build()
                .request(new FindListener<PlantBrowse>() {
                    @Override
                    public void done(List<PlantBrowse> list, BmobException e) {
                        if (e == null) {
                            bindBanner(list);
                        } else {
                            LogUtils.d("error");
                        }
                    }
                });
    }

    private void bindBanner(List<PlantBrowse> list) {
        for (PlantBrowse plantBrowse : list) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView
                    .plant_id(plantBrowse.getPlant_id())
                    .description(plantBrowse.getTitle())
                    .image(plantBrowse.getImg_url());
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
        requestBanner();
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
        Intent intent = new Intent(getContext(), ImageOperateActivity.class);
        intent.putExtra(IntentKey.KEY_PLANT_TYPE, textSliderView.getPlant_id());
        intent.putExtra(IntentKey.KEY_OPEN_TYPE, ImageOperaOperateModel.TYPE_REQUEST_SHOW);
        startActivity(intent);
    }
}
