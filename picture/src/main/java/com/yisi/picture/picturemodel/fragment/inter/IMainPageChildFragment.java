package com.yisi.picture.picturemodel.fragment.inter;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yisi.picture.picturemodel.base.inter.IBaseView;
import com.yisi.picture.picturemodel.bean.YiSiImage;

import java.util.List;


/**
 * Created by roy on 2017/1/19.
 */

public interface IMainPageChildFragment extends IBaseView {
    void bindRecylerViewLoadMore(List<YiSiImage> yiSiImages);

    void bindRecylerViewRefresh(List<YiSiImage> yiSiImages);

    void onDataRunOut();

    void onNoLastestData();

    List<YiSiImage> getYiSiImages();

    boolean isLoadMoreOrRefresh();

    int getCurrentPage();

    void setCurrentPage(int currentPage);

    XRecyclerView getRecycleView();
}
