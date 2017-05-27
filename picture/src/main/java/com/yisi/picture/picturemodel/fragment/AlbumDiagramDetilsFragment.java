package com.yisi.picture.picturemodel.fragment;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;

import com.yisi.picture.baselib.base.BaseFragment;
import com.yisi.picture.baselib.utils.ViewUtils;
import com.yisi.picture.picturemodel.R;


/**
 * Created by chenql on 2017/4/26.
 */

public class AlbumDiagramDetilsFragment extends BaseFragment {
    @Override
    protected void initViews() {
        Toolbar toolbar = findview(R.id.tl_fd_toolbar);
        toolbar.setTitle("爱Yisi");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitleMarginBottom(ViewUtils.getDimen(R.dimen.px100));
}

    @Override
    protected int getContentResouce() {
        return R.layout.fragment_details;
    }

    @Override
    protected void initData() {

    }
}
