package com.yisi.picture.picturemodel.fragment;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.yisi.picture.baselib.base.BaseFragment;
import com.yisi.picture.baselib.utils.ViewUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.activity.ImageChoseActivity;
import com.yisi.picture.picturemodel.bean.Image;
import com.yisi.picture.picturemodel.bean.RecommandPlantImage;
import com.yisi.picture.picturemodel.fragment.inter.IPlansFragment;
import com.yisi.picture.picturemodel.presenter.PlantFragmentPreImpl;
import com.yisi.picture.picturemodel.presenter.inter.IPlantFragmentPre;
import com.yisi.picture.picturemodel.view.utils.GliderImageFragmentLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import yisi.adplugin.utils.CoinUtils;

/**
 * Created by roy on 2017/1/14.
 */

public class PlantFragment extends BaseFragment implements IPlansFragment {

    RecyclerView mRecyclerView;
    IPlantFragmentPre plantFragmentPre;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Banner mBanner;


    @Override
    protected void initViews() {
        mRecyclerView = findview(R.id.plans_fragment_recycler);
        plantFragmentPre = new PlantFragmentPreImpl(this);
        mSwipeRefreshLayout = findview(R.id.sr_fragment_plans);
        mSwipeRefreshLayout.setProgressViewOffset(true, -20, ViewUtils.getDimen(R.dimen.px200));
        mSwipeRefreshLayout.setProgressViewEndTarget(true, ViewUtils.getDimen(R.dimen.px200));
        mSwipeRefreshLayout.setDistanceToTriggerSync(ViewUtils.getDimen(R.dimen.px200));
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        mBanner = new Banner(getContext());
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewUtils.getDimen(R.dimen.px600));
        mBanner.setLayoutParams(params);
        mBanner.setImageLoader(new GliderImageFragmentLoader());
    }


    @Override
    public void bindBanner(final List<RecommandPlantImage> list) {
        List<String> title = new ArrayList<>();
        //遍历一下title
        for (RecommandPlantImage image : list) {
            title.add("a");
        }
        mBanner.setImages(list);
        mBanner.setBannerTitles(title);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(8000);
        mBanner.setIndicatorHeight(ViewUtils.getDimen(R.dimen.px20))
                .setIndicatorWidth(ViewUtils.getDimen(R.dimen.px20))
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                .setTitleHeight(ViewUtils.getDimen(R.dimen.px80))
                .setTitleTextSize(0);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                RecommandPlantImage image = list.get(position);
                List<Image> images = image.getImage_list();
                String imgList = new Gson().toJson(images);
                if (CoinUtils.canBuy(image.getId(), image.getCoin(), mBanner)) {
                    startActivity(ImageChoseActivity.getDateIntent(imgList, image.getId(), image.getName(), image.getImage_url()));
                }
            }
        });
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    @Override
    public View getBanerView() {
        return mBanner;
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
}
