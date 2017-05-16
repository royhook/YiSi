package com.yisi.picture.picturemodel.fragment;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.view.View;

import com.daimajia.slider.library.SliderLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.base.BaseFragment;
import com.yisi.picture.picturemodel.fragment.inter.IPlansFragment;
import com.yisi.picture.picturemodel.presenter.PlantFragmentPreImpl;
import com.yisi.picture.picturemodel.presenter.inter.IPlantFragmentPre;

/**
 * Created by roy on 2017/1/14.
 */

public class PlantFragment extends BaseFragment implements IPlansFragment {

    XRecyclerView mXRecyclerView;
    IPlantFragmentPre plantFragmentPre;
    private SliderLayout mSliderLayout;
    private View mEveryDayView;
    private boolean mHideSlider = false;
    private AppBarLayout mAppBarLayout;
    private boolean mHadRemove = false;


    @Override
    protected void initViews() {
        mXRecyclerView = findview(R.id.plans_fragment_recycler);
        plantFragmentPre = new PlantFragmentPreImpl(this);
        mAppBarLayout = findview(R.id.main_appbar);
        mEveryDayView = findview(R.id.ll_everyday);
        mSliderLayout = findview(R.id.main_fragment_slider);
        mXRecyclerView.setPullRefreshEnabled(false);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(final AppBarLayout appBarLayout, int verticalOffset) {
//                if (getResources().getDimensionPixelOffset(R.dimen.px700) <= Math.abs(verticalOffset)) {
//                    mHideSlider = true;
//                    YiSiApplication.postDelay(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (mHideSlider) {
//                                appBarLayout.removeView(mSliderLayout);
//                                appBarLayout.removeView(mEveryDayView);
//                                mXRecyclerView.setPullRefreshEnabled(true);
//                                mHadRemove = true;
//                            }
//                        }
//                    }, 500);
//                } else {
//                    mHideSlider = false;
//                }
            }
        });
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
    public XRecyclerView getRecylerView() {
        return mXRecyclerView;
    }

    @Override
    public Context getViewContext() {
        return this.getContext();
    }

    @Override
    public void dataOut() {
        mXRecyclerView.setNoMore(true);
    }


//    public boolean onBackPressed() {
//        if (mHadRemove) {
//            if (mAppBarLayout != null) {
//                mAppBarLayout.addView(mSliderLayout, 0);//放在最上面
//                mAppBarLayout.addView(mEveryDayView, 0);
//            }
//            mXRecyclerView.setPullRefreshEnabled(false);
//            mHideSlider = false;
//            return true;
//        } else {
//            return false;
//        }
//    }
}
