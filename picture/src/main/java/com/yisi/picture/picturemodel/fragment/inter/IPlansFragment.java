package com.yisi.picture.picturemodel.fragment.inter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yisi.picture.baselib.base.inter.IBaseFragment;
import com.yisi.picture.picturemodel.bean.RecommandPlantImage;

import java.util.List;

/**
 * Created by roy on 2017/2/16.
 */

public interface IPlansFragment extends IBaseFragment {

    RecyclerView getRecylerView();

    Context getViewContext();

    void dataOut();

    SwipeRefreshLayout getSwipeRefresh();

    void bindBanner(List<RecommandPlantImage> list);


    View getBanerView();

}
