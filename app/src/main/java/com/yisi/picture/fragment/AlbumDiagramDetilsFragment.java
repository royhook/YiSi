package com.yisi.picture.fragment;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;

import com.yisi.picture.R;
import com.yisi.picture.base.BaseFragment;

/**
 * Created by chenql on 2017/4/26.
 */

public class AlbumDiagramDetilsFragment extends BaseFragment {
    @Override
    protected void initViews() {
        Toolbar toolbar = findview(R.id.tl_fd_toolbar);
        toolbar.setTitle("爱Yisi");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitleMarginBottom(com.yisi.picture.utils.ViewUtils.getDimen(R.dimen.px100));
}

    @Override
    protected int getContentResouce() {
        return R.layout.fragment_details;
    }

    @Override
    protected void initData() {

    }
}
